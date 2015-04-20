/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Book;
import entities.Order;
import entities.User;
import exception.UnableToOrderException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pierre
 */

@Stateless
public class OrderService implements Serializable {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    public void execute(String username) throws UnableToOrderException {
        User user = (User)entityManager.find(User.class, username);
        
        if(user != null) {
            Order order = new Order(user, new ArrayList<Book>(user.getCart()));
            entityManager.persist(order);
            user.clearCart();
        }
        else 
            throw new UnableToOrderException("User not found");
    }
}
