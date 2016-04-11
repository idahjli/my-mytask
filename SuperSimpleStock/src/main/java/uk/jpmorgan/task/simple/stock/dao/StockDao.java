/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.dao;

import java.util.List;
import uk.jpmorgan.task.simple.stock.vo.Stock;
import uk.jpmorgan.task.simple.stock.vo.Trade;

/**
 * An interface to perform communication with data source
 * 
 * @author idali
 */
public interface StockDao {
    
    /**
     * Retrieves a stock corresponding with a given stockSymbol
     * 
     * @param stockSymbol
     * @return Stock
     */
    public Stock getStockByStockSymbol(String stockSymbol);
    
    /**
     * Retrieve all the stocks in data source
     * 
     * @return list of stocks
     */
    public List<Stock> getAllStock();
    
    /**
     * Save a trade into data source
     * 
     * @param trade the trade to be saved into data source
     * @return List of trades
     */
    public List<Trade> saveTrade(Trade trade);
    
    /**
     * Retrieves all the trades in data source
     * 
     * @return List of trades
     */
    public List<Trade> getAllTrades();
    
    /**
     * Retrieve the last trade of each stock from data source
     * 
     * @return List of trades
     */
    public List<Trade> getLastTradeFromEachStock();
    
    /**
     * Get recent trades by a given stock
     * 
     * @param stock the given stock 
     * @return List of trades
     */
    public List<Trade> getRecentTradeByStock(Stock stock);
}
