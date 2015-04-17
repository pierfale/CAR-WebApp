/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Pierre
 */
@ApplicationException
public class UnableToCreateUserException extends RuntimeException {
    
    public UnableToCreateUserException(String message) {
        super(message);
    }
}
