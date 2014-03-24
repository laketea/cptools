/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.httpserver;

import javax.swing.JTextArea;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Log {
    
    private JTextArea jtx;
    
    public Log(JTextArea jtx){
        this.jtx = jtx;
    }
    
    public void logln(String jtx){
        this.jtx.append(""+jtx+"\n");
        this.jtx.setCaretPosition(this.jtx.getText().length());
    };
    
    public void log(String msg){
        this.jtx.append(msg);
        this.jtx.setCaretPosition(this.jtx.getText().length());
    };
    

    
    
}
