/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import exception.UnableToLoginException;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pierre
 */

/**
 *  JPA stateless session bean service which allow user to login
 * @author Pierre
 */
@Stateless
public class LoginService implements Serializable {
    
    @PersistenceContext(unitName="BookSellPU")
    private EntityManager entityManager;
    
    public static final String LOGIN_SESION_KEY = "user";

    /**
     * Fix the EntityManager instance with the new one in parameter.
     * @param entityManager the new instance.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Check if username exist and password is correct
     * @param username of user account
     * @param password of user account
     * @throws UnableToLoginException if information are not correct
     */
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
    }
    
    /**
     * get User associated to username or null if no account found
     * @param username
     * @return 
     */
    public User get(String username) {
        return username != null ? entityManager.find(User.class, username) : null;
    }
    
}
