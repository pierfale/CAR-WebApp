/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Exception throw when there is an issue in the order system
 * @author Pierre
 */
public class UnableToOrderException extends RuntimeException {
    
    public UnableToOrderException(String message) {
        super(message);
    }
}
