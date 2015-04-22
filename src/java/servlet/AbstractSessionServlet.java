/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.User;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import service.CartService;
import service.LoginService;

/**
 * Abstrat servlet which create session attribute for manage user and cart
 * @author Pierre
 */
public class AbstractSessionServlet extends HttpServlet {
    
    public final String CART_SESSION_KEY = "CART_KEY";
    
    @EJB
    private LoginService loginService;
    
    /**
     * fill attribute user and cartSize
     * @param request 
     */
    public void initializeRequest(HttpServletRequest request) {
        request.setAttribute("user", getUser());
        request.setAttribute("cartSize", getCartService().getItems().size());
    }
    
    /**
     * Return current session cart service or null otherwise
     * @return 
     */
    public CartService getCartService() {
        CartService cartService;
        cartService = (CartService)getServletContext().getAttribute(CART_SESSION_KEY); // get cart session

        if(cartService == null) {
                try {
                InitialContext ic = new InitialContext();
                cartService = (CartService)ic.lookup("java:global/BookSell/CartService"); // get service

                getServletContext().setAttribute(CART_SESSION_KEY, cartService);

            } catch (NamingException e) {
                return null;
            }
         }
        
        return cartService;
    }
    
    /**
     * return the current session user
     * @return 
     */
    public User getUser() {
        return loginService.get((String)getServletContext().getAttribute(LoginService.LOGIN_SESION_KEY));
    }
    
}
