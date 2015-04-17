/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Pierre
 */

@Entity
@Table(name="BOOKS")
public class Book implements Serializable {
    
    private String title;
    private String author;
    
    public Book() {
        
    }
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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
}
