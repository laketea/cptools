/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileClient;

import cptools.common.CONFIG;
import cptools.common.FileBean;
import cptools.common.Message;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class ClientMessageEncoder implements MessageEncoder<Message> {

    /**
     * 基本信息编码
     * */
    public void encode(IoSession session, Message message, ProtocolEncoderOutput outPut) throws Exception {
        // TODO Auto-generated method stub
        IoBuffer buffer = IoBuffer.allocate(1024 * 1024 * 50);
        buffer.putInt(message.getDataType());
        FileBean bean = (FileBean) message.getData();
        byte[] byteStr = bean.getFileName().getBytes(CONFIG.charset);
        buffer.putInt(byteStr.length);
        buffer.putInt(bean.getType());
        buffer.putInt(bean.getFileSize());
        buffer.put(byteStr);
        buffer.put(bean.getFileContent());
        buffer.flip();
        outPut.write(buffer);
        System.out.println("编码完成！");
    }
}
