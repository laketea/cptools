/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.httpserver;

import cptools.Shell;
import cptools.model.Project;
import cptools.ui.Utils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class PullServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       
        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.getWriter().write(ClientManage.getMessage(request.getSession().getId()));
            response.getWriter().flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
