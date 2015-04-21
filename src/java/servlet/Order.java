/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.User;
import exception.UnableToOrderException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;
import service.OrderService;

/**
 *
 * @author Pierre
 */
@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends AbstractSessionServlet {
    
    @EJB
    private OrderService orderService;
        
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
        request.setAttribute("title", "Command");
        
        initializeRequest(request);
        
        User user = getUser();
        
        if(user == null)
            this.getServletContext().getRequestDispatcher("/error/UnauthorizedAccess.jsp").forward(request, response);
        else {
            try {
                request.setAttribute("nbBook", getCartService().getItems().size());
                orderService.execute(user.getUsername(), new ArrayList<>(getCartService().getItems()));
                getCartService().getItems().clear();
                
                //reload user
                initializeRequest(request);
                
                 this.getServletContext().getRequestDispatcher("/Order.jsp").forward(request, response);
            }
            catch(UnableToOrderException e) {
                request.setAttribute("message", e.getMessage());
                 this.getServletContext().getRequestDispatcher("/ListCart.jsp").forward(request, response);
            }
           
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
