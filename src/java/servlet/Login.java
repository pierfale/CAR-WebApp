/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import exception.UnableToLoginException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;

/**
 *
 * @author Pierre
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends AbstractSessionServlet {
    
    @EJB
    private LoginService loginService;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(getUser() != null) {
            this.getServletContext().getRequestDispatcher("/error/UnauthorizedAccess.jsp").forward(request, response);
        }
        else {
                    
            request.setAttribute("title", "Login");
            request.setAttribute("user", null);

            this.getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(username != null && !username.equals("") && password != null && !password.equals("")) {
                try {
                    loginService.login(username, password);
                    getServletContext().setAttribute(loginService.LOGIN_SESION_KEY, username);
                    response.sendRedirect("ListBook");
                }
                catch(UnableToLoginException e) {
                    request.setAttribute("message", e.getMessage());
                    processRequest(request, response);
                }
            }
            else
                processRequest(request, response);
    }

}
