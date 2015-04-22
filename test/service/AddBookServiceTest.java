/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Book;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author gaetan
 */
public class AddBookServiceTest {


    /**
     * Test of add method, of class AddBookService.
     */
    @Test
    public void testAdd() {
        
        EntityManager entityManager = mock(EntityManager.class);
        
        AddBookService addBookService = new AddBookService();
        addBookService.setEntityManager(entityManager);
        
        Book book = new Book("Candide", "Voltaire", 5.0f);
        
        addBookService.add(book);
        
        verify(entityManager, Mockito.times(1)).persist(book);
    }
    
}
