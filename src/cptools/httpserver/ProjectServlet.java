/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.httpserver;

import cptools.ui.Utils;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class ProjectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        
        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.getWriter().write(Utils.getJsonProjects());
            response.getWriter().flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
