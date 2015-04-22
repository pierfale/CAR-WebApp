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
 * User JPA Entity
 * An user is identified by his username. 
 * It's also have a password (encrypted with SHA-1), a rank (USER or ADMIN) and the list of it orders
 * @author Pierre
 */
@Entity
@Table(name="USERS")
public class User implements Serializable {
    
    public enum Rank {
        USER,
        ADMIN
    }

    private String username;
    private String password;
    private Rank rank;
    private Collection<Order> orders;
    
    public User() {

    }
    
    public User(String username, String password, Rank rank) throws UnableToCreateUserException {
        try {
            this.username = username;
            this.password = hash(password);
            this.rank = rank;
        } catch (UnsupportedEncodingException ex) {
            throw new UnableToCreateUserException("Unsupported Encoding");
        } catch (NoSuchAlgorithmException ex) {
           throw new UnableToCreateUserException("Unsupported Hash");
        }
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
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="RANK")
    public Rank getRank() {
        return this.rank;
    }
    
    public void setRank(Rank rank) {
        this.rank = rank;
    }
    
    @OneToMany(mappedBy="user")
    public Collection<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    private String hash(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));

            Formatter formatter = new Formatter();
            for (byte b : crypt.digest()) { // Transform each byte in hexa format string
                    formatter.format("%02x", b);
            }
        String result = formatter.toString();
        formatter.close();

        return result;
    }

    /**
     * Return true if the password of current user equals the password in parameter, false otherwise
     * @param password
     * @return 
     */
    public boolean checkPassword(String password) {
        try {
            return hash(password).equals(this.password);
        } catch (UnsupportedEncodingException ex) {
            return false;
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
    }
    
    
    
}
