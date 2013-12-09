/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.common;

import javax.swing.JTextArea;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Console {

    private static Console sInstance = new Console();
    
    private static Console cInstance = new Console();
    
    private JTextArea jtx = null;

    public static Console getSInstance() {
        return sInstance;
    }
    
    public static Console getCInstance() {
        return cInstance;
    }

    private Console() {
    }

    public void setTextArea(JTextArea jtx) {
        this.jtx = jtx;
    }
    
    public void log(String info){
        this.jtx.append(info+"\n");
    
    }
}
