/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import exception.UnableToCreateUserException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Formatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pierre
 */
@Entity
@Table(name="USERS")
public class User implements Serializable {
    
    private String username;
    private String password;
    private Collection<Book> cart;
    
    public User() {

    }
    
    public User(String username, String password) throws UnableToCreateUserException {
        this.setUsername(username);
        this.setPassword(password);
    }
    
    @Id
    @Column(name="USERNAME")
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="PASSWORD")
    public String getPassword() {
        return this.password;
    } 
    
    public void setPassword(String password) throws UnableToCreateUserException {
        try {
            this.password = hash(password);
        } catch (UnsupportedEncodingException ex) {
            throw new UnableToCreateUserException("Unsupported Encoding");
        } catch (NoSuchAlgorithmException ex) {
           throw new UnableToCreateUserException("Unsupported Hash");
        }
    }
    
    @OneToMany
    public Collection<Book> getCart() {
        return this.cart;
    }
    
    public void setCart(Collection<Book> cart) {
        this.cart = cart;
    }
    

    
    private String hash(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(str.getBytes("UTF-8"));
        Formatter formatter = new Formatter();
        for (byte b : crypt.digest())
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        
        return result;
    }

    public boolean checkPassword(String password) {
        try {
            System.out.println(password+" > "+hash(password)+" - "+this.password+" ("+this.username+")");
            return hash(password).equals(this.password);
        } catch (UnsupportedEncodingException ex) {
            return false;
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
    }
    
    
    
}
