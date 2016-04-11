/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.exceptions;

/**
 * Exception handler for handle exceptions throw with SuperSimpleStock application
 * 
 * @author idali
 */
public class StockException extends RuntimeException {

    public StockException() {
    }

    public StockException(String s) {
        super(s);
    }
     
    public StockException(Throwable t) {
        super(t);
    }
    
    public StockException(String s, Throwable t) {
        super(s, t);
    }
}
