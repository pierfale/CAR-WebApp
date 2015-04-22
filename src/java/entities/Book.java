/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;


/**
 * Book JPA Entity. A book contains a title (his primary key), an author and a price
 * It also have the list of order which contains itself
 * @author Pierre
 */
@Entity
@Table(name="BOOKS")
public class Book implements Serializable {
    
    private String title;
    private String author;
    private float price;
    private Collection<Order> ordersContainer;
    
    public Book() {
        
    }
    
    public Book(String title, String author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    @Id
    @Column(name="TITLE")
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="AUTHOR")
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Column(name="PRICE")
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    @ManyToMany(mappedBy = "cart")
    public Collection<Order> getOrdersContainer() {
        return this.ordersContainer;
    }
    
    public void setOrdersContainer(Collection<Order> ordersContainer) {
        this.ordersContainer = ordersContainer;
    }
    
    /**
     * Two book are equals when it's title are equals
     * @param o, an Object
     * @return true if the book are the same
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Book && ((Book)o).getTitle().equals(this.title);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.title);
        return hash;
    }

}
