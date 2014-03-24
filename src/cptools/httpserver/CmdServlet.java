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
public class CmdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getParameter("path");
        String cmd = request.getParameter("cmd");
        boolean available = false;

        if (Utils.projects != null && !Utils.projects.isEmpty()) {
            for (Project project : Utils.projects) {
                if (project.getDir().equals(path)) {
                    available = true;
                    HttpServerPnl.logger.logln("##"+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())+"##  "+request.getLocalAddr()+", "+cmd+","+path + " ; ");
                    cmd = "ant".equals(cmd) ? "1" : "0";
                    List<String[]> list = new ArrayList<String[]>();
                    list.add(new String[]{path, cmd});
                    if(cmd.equals("1")){
                        project.setLastBuild(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
                    }else{
                        project.setLastUpdate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
                    }
                    
                    new Thread(new Shell(list,request.getSession().getId())).start();
                }
            }
        }
        
        
        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.getWriter().write(available?ClientManage.getMessage(request.getSession().getId()):"cmd is not available!");
            response.getWriter().flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
