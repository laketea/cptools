/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileClient;

import cptools.common.CONFIG;
import cptools.common.Console;
import cptools.common.FileBean;
import cptools.common.FileHelper;
import cptools.common.Message;
import java.io.File;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Client implements Runnable {

    private String url = "127.0.0.1";
    private int port = 10010;
    private IoSession session = null;
    private IoConnector connector = null;

    public Client(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public void run() {
        this.creatClient();
    }

    public void creatClient() {

        if (this.connector != null) {
            this.close();
        }
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ClientProtocolCodecFactory()));
        connector.setHandler(new ClientHandler());
        ConnectFuture future = connector.connect(new InetSocketAddress(url, 10010));
        // 等待是否连接成功，相当于是转异步执行为同步执行。 
        future.awaitUninterruptibly();
        // 连接成功后获取会话对象。 如果没有上面的等待， 由于connect()方法是异步的， session可能会无法获取。 
        try {
            session = future.getSession();
        } catch (Exception e) {
            e.printStackTrace();
            Console.getCInstance().log("连接失败!");
            Console.getCInstance().log(e.getMessage());
            return;
        }
        Console.getCInstance().log("连接成功!");

    }
    
    public void reconnect(){
        if(connector==null){
            return;
        }
        this.creatClient();
    }

    public void send(String path,int type) {
        if(session==null  ){
            return;
        }
        if(!session.isConnected()){
            this.reconnect();
        }
        Console.getCInstance().log("开始发送文件:"+path);
        Message baseMessage = new Message();
        baseMessage.setDataType(CONFIG.UPLOAD_FILE);
        FileBean bean = new FileBean();
        File file = new File(path);
        bean.setFileName(file.getName());
        bean.setFileSize((int) file.length());
        bean.setType(type);
        
        try {
            FileHelper helper = new FileHelper();
            bean.setFileContent(helper.getContent(file));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        baseMessage.setData(bean);
        session.write(baseMessage);
        Console.getCInstance().log("文件发送成功:"+path);
    }

    public void close() {
        if (session != null) {
//            session.getCloseFuture().awaitUninterruptibly();
        }
        connector.dispose(true);
        Console.getCInstance().log("断开连接!");
    }

    public IoSession getSession() {
        return session;
    }
}
