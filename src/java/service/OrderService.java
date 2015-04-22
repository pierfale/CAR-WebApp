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
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * JPA stateless session bean service which allow manage order
 * @author Pierre
 */
@Stateless
public class OrderService implements Serializable {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Create an order associated to the username
     * @param username user of the order
     * @param cart list of book in the order
     * @throws UnableToOrderException if order can't be created
     */
    public void execute(String username, List<Book> cart) throws UnableToOrderException {
        User user = (User)entityManager.find(User.class, username);
        
        if(user != null) {
            Order order = new Order(user, cart);
            entityManager.persist(order);
        }
        else 
            throw new UnableToOrderException("User not found");
    }
    
    /**
     * Get all previous order of a user associated to username
     * @param username
     * @return list of order
     */
    public List<Order> getPrevious(String username) {
        Query query = entityManager.createQuery("SELECT o FROM Order o WHERE o.user.username = :user");
        query.setParameter("user", username);
        return query.getResultList();
    }
}
