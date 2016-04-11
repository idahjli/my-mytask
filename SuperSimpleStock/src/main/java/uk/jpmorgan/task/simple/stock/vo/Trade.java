/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.vo;

import java.time.LocalDateTime;

/**
 *
 * @author idali
 */
public final class Trade {
    
    private final String stockSymbol;
    private final int quantity;
    private final double price;
    private final TradeType tradeType;
    private final LocalDateTime time;
    
    public Trade(final String stockSymbol, final int quantity, final double price, 
                    final TradeType tradeType, final LocalDateTime time) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
        this.time = time;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public LocalDateTime getTime() {
        return time;
    }
    
    public double getStockPrice() {
        return price * quantity;
    }
    
    @Override
    public String toString() {
        return "Trade [ stockSymbol = " + stockSymbol + ", quantity = " + quantity
                + ", price = " + price + ", tradeType = " + tradeType.name()
                + ", time = " + time + " ]";
    }
}
