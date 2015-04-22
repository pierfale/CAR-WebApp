/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
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
public class CartServiceTest {
    

    /**
     * Test of addBook method, of class CartService.
     * 
     * Normal case.
     */
    @Test
    public void testAddBookNormal() throws Exception {
        
        EntityManager entityManager = mock(EntityManager.class);
        List<Book> cart = mock(List.class);
        
        CartService cartService = new CartService();
        cartService.setEntityManager(entityManager);
        cartService.setCart(cart);
        
        
        String bookTitle = "Candide";
        Book book = new Book("Candide", "Voltaire", 5.0f);
        
        when((Book) entityManager.find(Book.class, bookTitle)).thenReturn(book);
        when(cart.contains(book)).thenReturn(false);
        
        cartService.addBook(bookTitle);
        
        verify(cart, Mockito.times(1)).add(book);
    }
    
    /**
     * Test of addBook method, of class CartService.
     * 
     * Null case.
     */
    @Test
    public void testAddBookNull() throws Exception {
        
        EntityManager entityManager = mock(EntityManager.class);
        List<Book> cart = mock(List.class);
        
        CartService cartService = new CartService();
        cartService.setEntityManager(entityManager);
        cartService.setCart(cart);
        
        
        String bookTitle = "Candide";
        Book book = new Book("Candide", "Voltaire", 5.0f);
        
        when((Book) entityManager.find(Book.class, bookTitle)).thenReturn(null);
        when(cart.contains(book)).thenReturn(false);
        
        cartService.addBook(bookTitle);
        
        verify(cart, Mockito.times(0)).add(book);
    }
    
    /**
     * Test of addBook method, of class CartService.
     * 
     * Already in.
     */
    @Test
    public void testAddBookAlreadyIn() throws Exception {
        
        EntityManager entityManager = mock(EntityManager.class);
        List<Book> cart = mock(List.class);
        
        CartService cartService = new CartService();
        cartService.setEntityManager(entityManager);
        cartService.setCart(cart);
        
        
        String bookTitle = "Candide";
        Book book = new Book("Candide", "Voltaire", 5.0f);
        
        when((Book) entityManager.find(Book.class, bookTitle)).thenReturn(null);
        when(cart.contains(book)).thenReturn(true);
        
        cartService.addBook(bookTitle);
        
        verify(cart, Mockito.times(0)).add(book);
    }

    /**
     * Test of getItems method, of class CartService.
     */
    @Test
    public void testGetItems() throws Exception {
        
        List<Book> cart = new ArrayList<Book>();
        cart.add(new Book("Candide", "Voltaire", 5.0f));
        
        CartService cartService = new CartService();
        cartService.setCart(cart);
        
        Collection<Book> result = cartService.getItems();
        
        assertEquals(1, result.size());
        assertEquals(cart, result);
    }

    /**
     * Test of removeBook method, of class CartService.
     */
    @Test
    public void testRemoveBook() throws Exception {
        
        EntityManager entityManager = mock(EntityManager.class);
        List<Book> cart = mock(List.class);
        
        CartService cartService = new CartService();
        cartService.setEntityManager(entityManager);
        cartService.setCart(cart);
        
        String bookTitle = "Candide";
        Book book = new Book("Candide", "Voltaire", 5.0f);
        
        when((Book) entityManager.find(Book.class, bookTitle)).thenReturn(book);
        
        cartService.removeBook(bookTitle);
        
        verify(cart, Mockito.times(1)).remove(book);
    }
    
}
