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

/**
 *
 * @author gaetan
 */
public class UserTest {
    
    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        
        System.out.println("getUsername");
        
        User instance = new User("usertest", "passtest", User.Rank.USER);
        String expResult = "usertest";
        
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        
        System.out.println("setUsername");
        
        User instance = new User("toto", "passtest", User.Rank.USER);

        instance.setUsername("usertest");
        String expResult = "usertest";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        
        System.out.println("getPassword");
        
        User instance = new User("usertest", "user", User.Rank.USER);
        
        String expResult = "12dea96fec20593566ab75692c9949596833adc9";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        
        System.out.println("setPassword");
        
        User instance = new User("usertest", "usertest", User.Rank.USER);
        
        instance.setPassword("user");
        String expResult = "12dea96fec20593566ab75692c9949596833adc9";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRank method, of class User.
     */
    @Test
    public void testGetRankUser() {
        
        System.out.println("getRank");
        
        User instance = new User("usertest", "passtest", User.Rank.USER);
        
        User.Rank expResult = User.Rank.USER;
        User.Rank result = instance.getRank();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getRank method, of class User.
     */
    @Test
    public void testGetRankAdmin() {
        
        System.out.println("getRank");
        
        User instance = new User("usertest", "passtest", User.Rank.ADMIN);
        
        User.Rank expResult = User.Rank.ADMIN;
        User.Rank result = instance.getRank();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setRank method, of class User.
     */
    @Test
    public void testSetRank() {
        
        System.out.println("setRank");
        
        User instance = new User("usertest", "passtest", User.Rank.ADMIN);
        
        instance.setRank(User.Rank.USER);
        User.Rank expResult = User.Rank.USER;
        User.Rank result = instance.getRank();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrders method, of class User.
     */
    @Test
    public void testGetOrders() {
        
        System.out.println("getOrders");
        
        User instance = new User("usertest", "passtest", User.Rank.USER);
        
        // Without setting, the expected result is null
        Collection<Order> expResult = null;
        Collection<Order> result = instance.getOrders();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrders method, of class User.
     */
    @Test
    public void testSetOrders() {
        
        System.out.println("setOrders");
        
        User instance = new User("usertest", "passtest", User.Rank.USER);
        
        Book candide = new Book("Candide", "Voltaire", 5.0f);
        Collection<Book> books = new ArrayList<Book>();
        books.add(candide);
        
        Collection<Order> orders = new ArrayList<Order>();
        orders.add(new Order(instance, books));
                
        instance.setOrders(orders);
        
        Collection<Order> result = instance.getOrders();

        assertEquals(1, result.size());

    }

    /**
     * Test of checkPassword method, of class User.
     */
    @Test
    public void testCheckPassword() {
        
        System.out.println("checkPassword");
        
        String password = "admin";
        User instance = new User("admin", "admin", User.Rank.ADMIN);
        
        assertTrue(instance.checkPassword(password));
        assertFalse(instance.checkPassword("pass"));
        
        
    }
    
}
