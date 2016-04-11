/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.logic;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.jpmorgan.task.simple.stock.dao.StockDao;
import uk.jpmorgan.task.simple.stock.dao.StockDaoImpl;
import uk.jpmorgan.task.simple.stock.exceptions.StockException;
import uk.jpmorgan.task.simple.stock.service.TradeService;
import uk.jpmorgan.task.simple.stock.service.TradeServiceImpl;
import uk.jpmorgan.task.simple.stock.vo.Stock;
import uk.jpmorgan.task.simple.stock.vo.StockType;
import uk.jpmorgan.task.simple.stock.vo.Trade;
import uk.jpmorgan.task.simple.stock.vo.TradeType;

/**
 *
 * @author idali
 */
public class StockLogicImpl implements StockLogic {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final StockDao dao;
    private final TradeService service;
    
    public StockLogicImpl() {
        dao = new StockDaoImpl();
        service = new TradeServiceImpl();
    }

    @Override
    public double calculateDividendYield(String stockSymbol, double tickerPrice) {
        
        logger.debug("calculateDividendYield: stockSymbol = {}, tickerPrice = {}", stockSymbol, tickerPrice);
        
        Stock stock = dao.getStockByStockSymbol(stockSymbol);
        if(stock == null) {
            throw new StockException("Unknown stock symbol: " + stockSymbol);
        } 
        return stock.getStockType() == StockType.Common ? 
                stock.getLastDividend() / tickerPrice : stock.getFixedDividend() * stock.getParValue() / tickerPrice;
    }

    @Override
    public double calculatePERatio(String stockSymbol, double tickerPrice) {
        
        logger.debug("calculatePERatio: stockSymbol = {}, tickerPrice = {}", stockSymbol, tickerPrice);
        
        Stock stock = dao.getStockByStockSymbol(stockSymbol);
        if(stock == null) {
            throw new StockException("Unknown stock symbol: " + stockSymbol);
        }  
        return stock.getLastDividend() == 0 ? 0 : tickerPrice/stock.getLastDividend();
    }

    @Override
    public List<Trade> saveTrade(String stockSymbol, int quantity, double price, TradeType tradeType) {

        logger.debug("saveTrade: stockSymbol = {}, price = {}", stockSymbol, price);

        try {
            switch (tradeType) {
                case Buy:
                    return service.buy(stockSymbol, quantity, price);
                case Sell:
                    return service.sell(stockSymbol, quantity, price);
                default:
                    return dao.getAllTrades();
            }
        } catch (StockException ex) {
            throw  ex;
        } 
    }

    @Override
    public double calculateStockPrice(String stockSymbol) {
        Stock stock = dao.getStockByStockSymbol(stockSymbol);
        if(stock == null) {
            throw new StockException("Unknown stock symbol: " + stockSymbol);
        }  
        
        List<Trade> trades = dao.getRecentTradeByStock(stock);
        if(trades == null || trades.isEmpty()) {
            throw new StockException("There is no trade made with this stock during the past 15 minutes");
        }
        double totalPrice = trades.stream()
                            .mapToDouble(Trade::getStockPrice)
                            .sum();
        
        int totalQuantity = trades.stream()
                            .mapToInt(Trade::getQuantity)
                            .sum();
        
        return totalPrice / totalQuantity;
    }

    @Override
    public double calculateGBCEShareIndex() {
        List<Stock> stocks = dao.getAllStock();
        
        double totalParValue = stocks.stream()
                .mapToDouble(Stock::getParValue)
                .sum();
        
        List<Trade> trades = dao.getLastTradeFromEachStock();
        if (trades != null && !trades.isEmpty()) {

            double mulTradePrice = trades.stream()
                                    .mapToDouble(Trade::getPrice)
                                    .reduce(1,
                                            (a, b) -> a * b);

            double geometricMean = Math.pow(mulTradePrice, 1.0 / (double) trades.size()); 
            return geometricMean / totalParValue;
        } else {
            throw new StockException("There is no trade in data source");
        } 
    }

}
