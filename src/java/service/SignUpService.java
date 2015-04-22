/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  JPA stateless session bean service which allow user to create new account
 * @author Pierre
 */
@Stateless
public class SignUpService {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Create in the jpa system the new user
     * @param user 
     */
    public void create(User user) {
        entityManager.persist(user);
    }
}
