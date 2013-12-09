/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileserver;

import cptools.common.Console;
import cptools.common.FileBean;
import cptools.common.Message;
import java.io.FileOutputStream;
import java.util.Date;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class FileServerHandler extends IoHandlerAdapter {
    
    private String sPath = "";
    
    private String wPath = "";
    
    private String hPath = "";
    
    public void setPath(String sPath,String wPath ,String hPath){
        this.sPath = sPath;
        this.wPath = wPath;
        this.hPath = hPath;
    }
    
    public String getServrPath(int type){
        String path  = sPath;
        switch (type){
            case 1:
                path= this.hPath;
                break;
            case 2:
                path= this.sPath;
                break;
            case 3:
                path= this.wPath;
                break;
        }
        return path;
    }
    
    public void sessionCreated(IoSession session) throws Exception {
        //compiled code
        Console.getSInstance().log("sessionCreated");
    }

    public void sessionOpened(IoSession session) throws Exception {
        //compiled code
        Console.getSInstance().log("sessionOpened");
    }
    

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        Console.getSInstance().log("异常:"+cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        Console.getSInstance().log("\n============================");
        String path = "";
        Message _message = (Message) message;
        FileBean bean = (FileBean) _message.getData();
        System.out.println(bean.getFileName());
        path = this.getServrPath(bean.getType());
        FileOutputStream os = new FileOutputStream(path+"/" + bean.getFileName());
        Console.getSInstance().log("接收到"+session.getRemoteAddress()+"发来的文件:"+bean.getFileName());
        Console.getSInstance().log("保存文件至:"+path+"/"+bean.getFileName());
        os.write(bean.getFileContent());
        os.close();
        session.close(false);
        Console.getSInstance().log("============================\n");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//        System.out.println("IDLE " + session.getIdleCount(status));
    }
}
