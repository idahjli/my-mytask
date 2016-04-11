/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.jpmorgan.task.simple.stock.exceptions.StockException;
import uk.jpmorgan.task.simple.stock.vo.Stock;
import uk.jpmorgan.task.simple.stock.vo.Trade;

/**
 *
 * @author idali
 */
public class StockDaoImpl implements StockDao {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Stock getStockByStockSymbol(String stockSymbol) {
        
        logger.debug("getStockByStockSymbol: stockSymbol = {}", stockSymbol);
        
        return DataStore.getInstance().getStockByStockSymbol(stockSymbol.toUpperCase()); 
    }

    @Override
    public List<Trade> getAllTrades() {
        
        logger.debug("getAllTrades");
        
        return DataStore.getInstance().getAllTrades();
    }

    @Override
    public List<Trade> saveTrade(Trade trade) {
        
        logger.debug("saveTrade: trade = {}", trade);
        
        Stock stock = DataStore.getInstance().getStockByStockSymbol(trade.getStockSymbol().toUpperCase()); 
        if(stock == null) {
            throw new StockException("Unknown stock symbol: " + trade.getStockSymbol());
        }
        return DataStore.getInstance().addTradeIntoTradMap(trade, stock);
    }
    
    @Override
    public List<Trade> getRecentTradeByStock(Stock stock) {
        return DataStore.getInstance().getTradesByStockWithinPastFifteenMunites(stock);
    }

    @Override
    public List<Trade> getLastTradeFromEachStock() {
        
        logger.debug("getLastTradeFromEachStock");
        
        return DataStore.getInstance().getLastTradeForEachStock();
    }

    @Override
    public List<Stock> getAllStock() {
        logger.debug("getAllStock");
        return DataStore.getInstance().getAllStock();
    }

}
