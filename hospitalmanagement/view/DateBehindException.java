/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

/**
 *
 * @author Tanaka
 */
public class DateBehindException extends Exception {

    /**
     * Creates a new instance of <code>DateBehindException</code> without detail
     * message.
     */
    public DateBehindException() {
    }

    /**
     * Constructs an instance of <code>DateBehindException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DateBehindException(String msg) {
        super(msg);
    }
}
