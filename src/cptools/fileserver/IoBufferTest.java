/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.fileserver;

import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class IoBufferTest {
    
    public static void main(String[] args){
    
        IoBuffer  buf = IoBuffer.allocate(100);
        
        buf.putInt(1);
        buf.putInt(2);
        
        buf.putChar('a');
        
//        buf.put((Byte)12);
        
        buf.flip();
        
        Byte a = buf.get();
        
        
        System.out.println(new String(buf.buf().array()));
        
        System.out.println(buf.getInt());
        System.out.println(buf.getInt());
    
    
    
    }
    
}
