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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ListBookService;

/**
 * Display list of all book order by title. if parameter "search" is specified, list only book which contains the search content in title
 * @author Pierre
 */
@WebServlet(name = "ListBook", urlPatterns = {"/ListBook"})
public class ListBook extends AbstractSessionServlet {
    
    @EJB
    private ListBookService  listBookService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Book List");
        
        request.setAttribute("cartSize", getCartService().getItems().size());  
        
        initializeRequest(request);
        
        String search = request.getParameter("search");

        if(search != null && !search.equals("")) {
                request.setAttribute("search", search);
                request.setAttribute("listBook", listBookService.search(search)); // get list of book, which title contains search pattern
        }
        else {
                request.setAttribute("search", "");
                request.setAttribute("listBook", listBookService.getByTitle()); // get all books
        }
        
        this.getServletContext().getRequestDispatcher("/ListBook.jsp").forward(request, response);
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
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
