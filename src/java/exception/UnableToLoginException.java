/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javax.ejb.ApplicationException;

/**
 * Exception throw when there is an issue in the user login system
 * @author Pierre
 */
@ApplicationException
public class UnableToLoginException extends RuntimeException {
    
    public UnableToLoginException(String message) {
        super(message);
    }
}
