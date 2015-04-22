/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author gaetan
 */
public class OrderTest {
    
    private User user;
    private Collection<Book> books;
    
    @Before
    public void setUp(){
        
        user = new User("usertest", "passtest", User.Rank.USER);
        books = new ArrayList<Book>();
        books.add(new Book("Candide", "Voltaire", 5.0f));
        books.add(new Book("One Punch-man", "Yusuke Murata", 2.0f));
                
    }
    
    /**
     * Test of setId method, of class Order.
     */
    @Test
    public void testSetId() {
        
        System.out.println("setId");
       
        Order instance = new Order(user, books);

        instance.setId(42);
        assertEquals(42, instance.getId());
    }

    /**
     * Test of getUser method, of class Order.
     */
    @Test
    public void testGetUser() {
        
        System.out.println("getUser");
        
        Order instance = new Order(user, books);
        
        User expResult = user;
        User result = instance.getUser();
        
        assertEquals(expResult, result);
        assertEquals("usertest", result.getUsername());
    }

    /**
     * Test of setUser method, of class Order.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");

        User user2 = new User("toto", "mdp", User.Rank.USER);
        
        Order instance = new Order(user, books);
        instance.setUser(user2);
        
        User expResult = user2;
        User result = instance.getUser();
        
        assertEquals(expResult, result);
        assertEquals("toto", result.getUsername());
    }

    /**
     * Test of getCart method, of class Order.
     */
    @Test
    public void testGetCart() {
        
        System.out.println("getCart");
        
        Order instance = new Order(user, books);
        
        Collection<Book> expResult = books;
        Collection<Book> result = instance.getCart();
        
        assertEquals(2, result.size());
        assertEquals(expResult.toArray()[0], result.toArray()[0]);
    }

    /**
     * Test of setCart method, of class Order.
     */
    @Test
    public void testSetCart() {
        
        System.out.println("setCart");
        
        Collection<Book> cart = new ArrayList<Book>();
        cart.add(new Book("KANJI & KANA", "Wolfgang Hadamitzky", 40.0f));
        
        Order instance = new Order(user, cart);
        instance.setCart(cart);
        
        Collection<Book> result = instance.getCart();
        
        assertEquals(1, result.size());
        assertEquals(cart.toArray()[0], result.toArray()[0]);
        assertNotSame(books.toArray()[0], result.toArray()[0]);
    }
    
}
