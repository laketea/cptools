/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.httpserver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class HttpServer implements Runnable {
    
    private Server server ;

    public void run()  {
        try {
            Server server = new Server(3838);
            
            this.server = server;

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setDirectoriesListed(false);
            resource_handler.setWelcomeFiles(new String[]{"index.html"});
            resource_handler.setResourceBase("./resources/tmp/result");
            
            ServletContextHandler context_handler = new ServletContextHandler();
            context_handler.addServlet(CmdServlet.class, "/exe");
            context_handler.addServlet(ProjectServlet.class, "/projects");
            context_handler.addServlet(PullServlet.class, "/pull");
            
            SessionManager sm = new HashSessionManager();
            context_handler.setSessionHandler(new SessionHandler(sm));
            
    //        context_handler.addEventListener(new SessionCounter());
            

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler,context_handler, new DefaultHandler()});
            server.setHandler(handlers);
            
            HttpServerPnl.logger.logln("启动服务!");
            
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
    }
    
    public void stop() throws Exception{
        if(this.server!=null){
            this.server.stop();
            HttpServerPnl.logger.logln("关闭服务!");
        }
    
    }
    
    public static void main(String[] args){
        try {
            new Thread(new HttpServer()).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
