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
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA statelful session bean service which manage the user cart. This last one contains a list of book which are currently in the cart
 * @author Pierre
 */
@Stateful
public class CartService {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    private List<Book> cart = new ArrayList<Book>();
    
    /**
     * Add a the book which matches with the title in the cart
     * @param bookTitle title of a book in the JPA system
     */
    public void addBook(String bookTitle) {
        Book book =(Book) entityManager.find(Book.class, bookTitle);
        
        if(book != null && !cart.contains(book)) {
            cart.add(book);
        }
    }
    
    /**
     * Rrtuen the content of the cart
     * @return list of book ine the cart
     */
    public Collection<Book> getItems() {
        return cart;
    }

    /**
     * Remove a book of the cart. The title need to matche with a book of the JPA system and be in the cart else, nothing will append
     * @param bookTitle title of a book in the JPA system
     */
    public void removeBook(String bookTitle) {
        Book book =(Book) entityManager.find(Book.class, bookTitle);
        
        if(book != null) {
            cart.remove(book);
        }
    } 
}
