/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileserver;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
import cptools.common.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.handler.stream.StreamIoHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author rose
 * 文件传输服务器
 */
public class FileServer implements Runnable {

    private static final int PORT = 10010;
    private String serverlibPath = "";
    private NioSocketAcceptor acceptor = null;
    private String weblibPath = "";
    private String homePath = "";

    public FileServer(String sPath, String wPath,String hPath) {
        this.serverlibPath = sPath;
        this.weblibPath = wPath;
        this.homePath = hPath;
    }

    public void run() {
        acceptor = new NioSocketAcceptor();
//        acceptor.getSessionConfig().set
        acceptor.getSessionConfig().setReuseAddress(true);

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ServerProtocolCodecFactory()));
        FileServerHandler handler = new FileServerHandler();
        handler.setPath(serverlibPath, weblibPath,homePath);
        acceptor.setHandler(handler);
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        try {
            acceptor.bind(new InetSocketAddress(PORT));
        } catch (IOException ex) {
            ex.printStackTrace();
            Console.getSInstance().log(ex.getMessage());
            return;
        }
        Console.getSInstance().log("服务启动,绑定端口：" + PORT);

    }

    public void stop() {
        if (acceptor != null) {
            acceptor.dispose(true);
            Console.getSInstance().log("关闭服务器!");
        }
    }


    public String getServerlibPath() {
        return serverlibPath;
    }

    public void setServerlibPath(String serverlibPath) {
        this.serverlibPath = serverlibPath;
    }

    public String getWeblibPath() {
        return weblibPath;
    }

    public void setWeblibPath(String weblibPath) {
        this.weblibPath = weblibPath;
    }
}
