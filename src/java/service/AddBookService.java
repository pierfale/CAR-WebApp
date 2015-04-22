/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Book;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA stateless session bean service which allow to persist a book
 * @author Pierre
 */
@Stateless
public class AddBookService implements Serializable {

    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;

    /**
     * Fix the EntityManager instance with the new one in parameter.
     * @param entityManager the new instance.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Add a book in the JPA system
     * @param book to add
     */
    public void add(Book book) {
        entityManager.persist(book);
    }

    
}
