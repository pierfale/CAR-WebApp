/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ListBookService;
import service.LoginService;

/**
 *
 * @author Pierre
 */
@WebServlet(name = "ListBook", urlPatterns = {"/ListBook"})
public class ListBook extends HttpServlet {
    
    @EJB
    private ListBookService  listBookService;


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
        
        request.setAttribute("title", "Book List");
        
        LoginService login = (LoginService)getServletContext().getAttribute(LoginService.LOGIN_SESION_KEY);
        request.setAttribute("user", login != null ? login.getUser() : null);
        
        request.setAttribute("bookList", listBookService.getAll());
        
        this.getServletContext().getRequestDispatcher("/ListBook.jsp").forward(request, response);
    }


}
