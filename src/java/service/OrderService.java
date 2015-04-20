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
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public List<Order> getPrevious(String username) {
        Query query = entityManager.createQuery("SELECT o FROM Order o WHERE o.user.username = :user");
        query.setParameter("user", username);
        return query.getResultList();
    }
}
