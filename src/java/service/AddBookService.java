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
 *
 * @author Pierre
 */
@Stateless
public class AddBookService implements Serializable {

    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    public void add(Book book) {
        System.err.println("add->"+entityManager);
        entityManager.persist(book);
    }

    
}
