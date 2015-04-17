/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import exception.UnableToLoginException;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pierre
 */

@Stateful
public class LoginService implements Serializable {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    private User user;
    
    public User getUser() {
        return this.user;
    }
    
    public void login(String username, String password) throws UnableToLoginException {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        User user = null;
        try {
            user = (User)query.getSingleResult();
        }
        catch(NoResultException e) {
            throw new UnableToLoginException("User not found");
        }
        
        if(!user.checkPassword(password))
            throw new UnableToLoginException("Inccorect password");
        
        this.user = user;
    }
    
    public void logout() {
        this.user = null;
    }
    
}