/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.logic;

import java.util.List;
import uk.jpmorgan.task.simple.stock.vo.Trade;
import uk.jpmorgan.task.simple.stock.vo.TradeType;

/**
 * Stock logic interface
 * 
 * @author idali
 */
public interface StockLogic {
    
    /**
     * Calculate the dividend yield for a given stock
     * 
     * @param stockSymbol the stock symbol for a corresponding stock
     * @param tickerPrice the ticker price for the stock
     * 
     * @return the dividend yield
     */
    public double calculateDividendYield(String stockSymbol, double tickerPrice);
    
    /**
     * Calculate the P/E Ratio for a given stock
     * 
     * @param stockSymbol the stock symbol for a corresponding stock
     * @param tickerPrice the ticker price for the stock
     * 
     * @return the P/E Ratio
     */
    public double calculatePERatio(String stockSymbol, double tickerPrice);
    
    /**
     * Record a trade, with timestamp, quantity of shares, buy or sell indicator and price
     *  
     * @param stockSymbol the stock symbol for the corresponding stock
     * @param quantity the quantity of this trade
     * @param price the trade price
     * @param tradeType the type of trade to indicate buy or sell
     * 
     * @return List of trades
     */
    public List<Trade> saveTrade(String stockSymbol, int quantity, double price, TradeType tradeType);
    
    /**
     * Calculate Stock Price based on trades recorded in past 15 minutes
     * 
     * @param stockSymbol the stock symbol
     * @return the stock price
     */
    public double calculateStockPrice(String stockSymbol);
    
    /**
     * Calculate the GBCE All Share Index 
     * 
     * @return double
     */
    public double calculateGBCEShareIndex();
    
}
