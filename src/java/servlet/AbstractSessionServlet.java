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
 *
 * @author Pierre
 */
public class AbstractSessionServlet extends HttpServlet {
    
    public final String CART_SESSION_KEY = "CART_KEY";
    
    @EJB
    private LoginService loginService;
    
    public void initializeRequest(HttpServletRequest request) {
        request.setAttribute("user", getUser());
        request.setAttribute("cartSize", getCartService().getItems().size());
    }
    
    public CartService getCartService() {
        CartService cartService;
        cartService = (CartService)getServletContext().getAttribute(CART_SESSION_KEY);
        
        if(cartService == null) {
            try {
                InitialContext ic = new InitialContext();
                cartService = (CartService)ic.lookup("java:global/BookSell/CartService");

            // put EJB in HTTP session for future servlet calls
                getServletContext().setAttribute(CART_SESSION_KEY, cartService);

          } catch (NamingException e) {
              return null;
          }
        }
        
        return cartService;
    }
    
    public User getUser() {
        return loginService.get((String)getServletContext().getAttribute(LoginService.LOGIN_SESION_KEY));
    }
    
}
