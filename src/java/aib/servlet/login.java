/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aib.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 230268178
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    private Database db;
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new Database(dbUrl, dbUser, dbPassword);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        String action = req.getParameter("action");
        if(!isAuthenticated(req) && !("authenticate".equals(action))){
            doLogin(req, res);
            return;
        }
        if("login".equals(action)){
            doAuthenticate(req, res);
        } else if("logout".equals(action)){
            doLogout(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }
    
    private void doAuthenticate(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String targetURL;
        
        if(db.isValidUser(username, password)){
            HttpSession session = req.getSession(true);
            UserInfo bean = new UserInfo();
            bean.setUsername(username);
            bean.setPassword(password);
            session.setAttribute("userInfo", bean);
            targetURL = "/welcome.jsp";
        } else {
            targetURL = "/loginError.jsp";
        }
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, res);
    }
}
