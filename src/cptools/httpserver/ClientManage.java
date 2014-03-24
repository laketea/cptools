/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.httpserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class ClientManage {

    private static HashMap<String, Queue<String>> msgMap = new HashMap<String, Queue<String>>();

    public static void pushMessage(String id, String str) {
        if (!msgMap.containsKey(id)) {
            msgMap.put(id, new LinkedList<String>());
        }
        msgMap.get(id).offer(str);
    }

    public static String getMessage(String id) {
        StringBuffer msg = new StringBuffer();
        if (msgMap.containsKey(id)) {
            Queue<String> msgs = msgMap.get(id);
            while (msgs!=null && msgs.size() > 0) {
                msg.append(msgs.poll());
            }
        }
        return msg.toString();
    }
}
