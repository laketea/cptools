/**
 * @(#)XhrPollingServlet.java
 * 
 * Copyright (c) 2013 Youngfriend Inc.
 * All rights reserved.
 */
package cptools.httpserver;

import cptools.common.ServerUtils;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author zhangwei
 * @version 1.0 2013-8-19
 */
public class XhrPollingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String cmd = request.getParameter("cmd");
        String sessionId = request.getSession().getId();

        if (!ServerUtils.nullBank(cmd)) {
            HttpServerPnl.logger.logln(request.getLocalAddr()+":"+cmd);
            // new Thread(new Shell(list)).start();
        }


        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.getWriter().write(ClientManage.getMessage(sessionId));
            response.getWriter().flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }








    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String parser(HttpServletRequest request) throws IOException {

        byte[] bytes = new byte[1024 * 1024];
        InputStream is = request.getInputStream();

        int nRead = 1;
        int nTotalRead = 0;
        while (nRead > 0) {
            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
            if (nRead > 0) {
                nTotalRead = nTotalRead + nRead;
            }
        }
        String str = new String(bytes, 0, nTotalRead, "utf-8");
        return str;
    }
}
