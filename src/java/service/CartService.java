/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Book;
import entities.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pierre
 */

@Stateless
public class CartService {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    public void addBook(String username, String bookTitle) {
        User user =(User) entityManager.find(User.class, username);
        Book book =(Book) entityManager.find(Book.class, bookTitle);
        
        if(book != null && user != null && !user.getCart().contains(book)) {
            user.addBookToCart(book);
        }
    }
    
    public Collection<Book> getItems(String username) {
        User user =(User) entityManager.find(User.class, username);
        return user != null ? user.getCart() : new ArrayList<Book>();
    }

    public void removeBook(String username, String bookTitle) {
        User user =(User) entityManager.find(User.class, username);
        Book book =(Book) entityManager.find(Book.class, bookTitle);
        
        if(book != null && user != null) {
            user.removeBookToCart(book);
        }
    }
}
