/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools;

import cptools.httpserver.ClientManage;
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

    public static JTextArea jtx;
    InputStream is;
    String type;
    String clientId = null;

    public StreamGobblers(InputStream is, String type, String clientId) {
        this.is = is;
        this.type = type;
        this.clientId = clientId;
    }

    public static void setTextArea(JTextArea jt) {
        jtx = jt;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (this.clientId != null) {
                    ClientManage.pushMessage(clientId, "\n" + line);
                } else {
                    jtx.append("\n" + line);
                    jtx.setCaretPosition(jtx.getText().length());
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
