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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author gaetan
 */
public class OrderServiceTest {
    
    /**
     * Test of execute method, of class OrderService.
     * Normal case.
     */
    @Test
    public void testExecute(){

        EntityManager entityManager = mock(EntityManager.class);
        
        OrderService orderService = new OrderService();
        orderService.setEntityManager(entityManager);
        
        // This user not exists in the database,
        // therefore we simulate its existence using Mockito
        String username = "toto";
        String password = "password";
        
        User user = new User(username, password, User.Rank.USER);

        when( (User)entityManager.find(User.class, username) ).thenReturn(user);
        
        List<Book> cart = new ArrayList<Book>();
        cart.add(new Book("Candide", "Voltaire", 5.0f));
        
        orderService.execute(username, cart);
        
        // Verify if the persist method is call one time only
        verify(entityManager, Mockito.times(1)).persist(Mockito.any());
    }

    /**
     * Test of execute method, of class OrderService.
     * Exception case.
     */
    @Test(expected=UnableToOrderException.class)
    public void testExecuteException(){

        EntityManager entityManager = mock(EntityManager.class);
        OrderService orderService = new OrderService();
        orderService.setEntityManager(entityManager);
        
        
        // This user not exists in the database
        // and we don't add it inside the database
        String username = "toto";
        
        List<Book> cart = new ArrayList<Book>();
        cart.add(new Book("Candide", "Voltaire", 5.0f));
        

        orderService.execute(username, cart);
        
        List<Order> orders = orderService.getPrevious(username);
        
        for (Order order : orders) {
            System.out.println(order.getUser());
        }
    }
  
}
