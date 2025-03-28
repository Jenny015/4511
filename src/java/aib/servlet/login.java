/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aib.servlet;

import aib.bean.Emp;
import aib.db.Database;
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
@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class login extends HttpServlet {
    private Database db;
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new Database(dbUrl, dbUser, dbPassword);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        doPost(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        String action = req.getParameter("action");
        if("login".equals(action)){
            doLogin(req, res);
        } else if("logout".equals(action)){
            doLogout(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
        
    }
    
    private void doLogin(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        out.println("doLogin function");
        String empID = req.getParameter("empid");
        String pwd = req.getParameter("pwd");
        String targetURL;
        targetURL = "/bakery/home.jsp"; //Temp
        if(db.doAuthenticate(empID, pwd)){
            HttpSession session = req.getSession(true);
            session.setAttribute("empID", empID);
            // TODO: function of Database.getEmp() and Emp.getRole()
            /*Emp emp = db.getEmp();
            switch (emp.getRole()){
                case "B":
                    targetURL = "/bakery/home.jsp";
                    break;
                case "M":
                    targetURL = "/management/home.jsp";
                    break;
                case "W":
                    targetURL = "/warehouse/home.jsp";
                    break;
                default:
                    // TODO: error page catching role error type
                    targetURL = "/loginError.jsp?type=roleError";
                    break;
            }*/
        } else {
            // TODO: error page catching role error type
            targetURL = "/loginError.jsp?type=loginFailed";
        }
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, res);
    }
    
    private void doLogout(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        HttpSession session = req.getSession(false);
        if(session != null){
            session.removeAttribute("empID");
            session.invalidate();
        }
        String targetURL = "index.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+targetURL);
        rd.forward(req, res);
    }
}
