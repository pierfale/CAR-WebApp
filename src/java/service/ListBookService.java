/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Book;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *  JPA stateless session bean service which allow to get list of book of the JPA system
 * @author Pierre
 */
@Stateless
public class ListBookService implements Serializable {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    /**
     * Return the list of all book of the JPA system order by title 
     * @return list of book
     */
    public List<Book> getByTitle() {
        Query query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title");
        return query.getResultList();
    }
    
    /**
     * Return the list of book which title contains the parameter string (case insensitive)
     * @return list of book
     */
    public List<Book> search(String name) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE lower(b.title) LIKE :pattern");
        query.setParameter("pattern", "%"+name.toLowerCase()+"%");
        return query.getResultList();
    }
    
        /**
     * Return the list of all book of the JPA system order by author name
     * @return list of book
     */
    public List<Book> getByAuthor() {
        Query query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.author");
        return query.getResultList();
    }
    
}
