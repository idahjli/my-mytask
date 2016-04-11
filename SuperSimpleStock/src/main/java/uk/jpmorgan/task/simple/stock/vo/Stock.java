/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.vo;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class Stock implements Serializable {
    
    private final String stockSymbol;
    private final StockType stockType;
    private final double lastDividend;
    private final double fixedDividend;
    private final double parValue;
    
    public Stock(String stockSymbol, StockType stockType, double lastDividend, 
                        double fixedDividend, double parValue) {
        this.stockSymbol = stockSymbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public double getParValue() {
        return parValue;
    }
    
    @Override
    public String toString() {
        return "Stock [ stockSymbol = " + stockSymbol + ", stockType = " + stockType
                    + ", lastDividend = " + lastDividend + ", fixedDividend = " + fixedDividend
                    + ", parValue = " + parValue;
    }
}
