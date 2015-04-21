/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.User.Rank;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gaetan
 */
public class BookTest {
    
    /**
     * Test of getTitle method, of class Book.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Book book = new Book("Candide", "Voltaire", 5.00f);
        String expResult = "Candide";
        String result = book.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Book.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "L'Optimisme";
        Book book = new Book("Candide", "Voltaire", 5.00f);
        book.setTitle(title);
        
        String expResult = "L'Optimisme";
        String result = book.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthor method, of class Book.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
        Book book = new Book("Candide", "Voltaire", 5.00f);
        String expResult = "Voltaire";
        String result = book.getAuthor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthor method, of class Book.
     */
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        String author = "Voltaire";
        Book book = new Book("Candide", "Toto", 5.00f);
        book.setAuthor(author);
        
        String expResult = "Voltaire";
        String result = book.getAuthor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Book.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Book instance = new Book("Candide", "Toto", 5.00f);
        float expResult = 5.0f;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of setPrice method, of class Book.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        float price = 4.2f;
        Book instance = new Book("Candide", "Toto", 5.00f);
        instance.setPrice(price);

        float expResult = 4.2f;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of getOrdersContainer method, of class Book.
     */
    @Test
    public void testGetOrdersContainer() {
        System.out.println("getOrdersContainer");
        Book book = new Book();
        Collection<Order> expResult = null;
        Collection<Order> result = book.getOrdersContainer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrdersContainer method, of class Book.
     */
    @Test
    public void testSetOrdersContainer() {
        System.out.println("setOrdersContainer");
        
        User user = new User("toto", "mdp", Rank.USER);
        
        Collection<Order> expResult = new ArrayList<Order>();
        expResult.add(new Order(user, null));
        
        Book book = new Book();
        book.setOrdersContainer(expResult);

        Collection<Order> result = book.getOrdersContainer();
        
        assertEquals(expResult.size(), result.size());
        
        Object[] expArray = expResult.toArray();
        Object[] resArray = result.toArray();
             
        String expUsername = ((Order)expArray[0]).getUser().getUsername();
        String resUsername = ((Order)resArray[0]).getUser().getUsername();
        
        assertEquals(expUsername, resUsername);     
    }

    /**
     * Test of equals method, of class Book.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals");

        Book book = new Book("Candide", "Toto", 5.00f);
        boolean result = book.equals(book);
        
        assertTrue(result);
    }
    
    /**
     * Test of equals method, of class Book.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");

        Book book1 = new Book("Candide", "Toto", 5.00f);
        Book book2 = new Book("Candide", "Toto", 5.00f);
        boolean result = book1.equals(book2);
        
        assertTrue(result);
    }
    
    /**
     * Test of equals method, of class Book.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");

        Book book1 = new Book("Candide", "Toto", 5.00f);
        Book book2 = new Book("L'Optimisme", "Toto", 5.00f);
        boolean result = book1.equals(book2);
        
        assertFalse(result);
    }
    
    /**
     * Test of equals method, of class Book.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals");

        Book book1 = new Book("Candide", "Toto", 5.00f);
        Book book2 = new Book("Candide", "Toto", 5.10f);
        boolean result = book1.equals(book2);
        
        assertFalse(result);
    }
    
}
