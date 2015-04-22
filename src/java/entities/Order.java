/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Order JPA entity
 * @author Pierre
 */
@Entity
@Table(name="ORDERS")
public class Order implements Serializable {
    
    private int id;
    private User user;
    private Collection<Book> cart;
    
    public Order() {
        
    }
    
    public Order(User user, Collection<Book> cart) {
        this.user = user;
        this.cart = cart;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @ManyToOne
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @ManyToMany
    public Collection<Book> getCart() {
        return this.cart;
    }
    
    public void setCart(Collection<Book> cart) {
        this.cart = cart;
    }
    
}
