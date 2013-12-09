/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileClient;

import cptools.common.CONFIG;
import cptools.common.FileBean;
import cptools.common.FileHelper;
import cptools.common.Message;
import java.io.File;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class ClientHandler extends IoHandlerAdapter {

    /**
     * 客户端接收到信息
     * */
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // TODO Auto-generated method stub
        super.messageReceived(session, message);

    }

    public void sessionOpened(IoSession session) {
//        Message baseMessage = new Message();
//        baseMessage.setDataType(CONFIG.UPLOAD_FILE);
//        FileBean bean = new FileBean();
//        File file = new File("e:\\log.log");
//        bean.setFileName(file.getName());
//        bean.setFileSize((int) file.length());
//        try {
//            FileHelper helper = new FileHelper();
//            bean.setFileContent(helper.getContent(file));
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        baseMessage.setData(bean);
//        session.write(baseMessage);
    }

    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionCreated(session);
    }
}
