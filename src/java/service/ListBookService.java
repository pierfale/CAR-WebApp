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
 *
 * @author Pierre
 */
@Stateless
public class ListBookService implements Serializable {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    public List<Book> getByTitle() {
        Query query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title");
        return query.getResultList();
    }
    
    public List<Book> search(String name) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE lower(b.title) LIKE :pattern");
        query.setParameter("pattern", "%"+name.toLowerCase()+"%");
        return query.getResultList();
    }
    
    public Book get(String name) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.title = :name");
        query.setParameter("name", name);
        return (Book)query.getSingleResult();  
    }
    
    public List<Book> getByAuthor() {
        Query query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.author");
        return query.getResultList();
    }
    
}
