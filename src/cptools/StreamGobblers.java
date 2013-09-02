/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class StreamGobblers extends Thread {
    
    private static JTextArea jtx ;

    InputStream is;
    String type;

    StreamGobblers(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }
    
    public static void setTextArea(JTextArea jt){
        jtx = jt;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
               jtx.append("\n"+line);
               jtx.setCaretPosition(jtx.getText().length());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
