/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import entities.Book;
import entities.User;
import exception.UnableToLoginException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AddBookService;
import service.ListBookService;
import service.LoginService;

/**
 * Add new book into JPA system. check if user has admin status, otherwise redirect to an error page
 * @author Pierre
 */
@WebServlet(name = "AddBook", urlPatterns = {"/AddBook"})
public class AddBook extends HttpServlet {
    
    @EJB
    private AddBookService  addBookService;
    
    @EJB
    private LoginService loginService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Book List");
        
        User user = loginService.get((String)getServletContext().getAttribute(LoginService.LOGIN_SESION_KEY));
        
        if(user == null || user.getRank() != User.Rank.ADMIN)
            response.sendRedirect("error/UnauthorizedAccess.jsp");
        else {
            request.setAttribute("user", user);
            this.getServletContext().getRequestDispatcher("/AddBook.jsp").forward(request, response);
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
        
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        if(title != null && author != null && price != null) {
            addBookService.add(new Book(title, author, Float.parseFloat(price)));
        } 

        processRequest(request, response);

    }

}
