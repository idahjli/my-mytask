/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.service;

import java.time.LocalDateTime;
import java.util.List;
import uk.jpmorgan.task.simple.stock.dao.StockDao;
import uk.jpmorgan.task.simple.stock.dao.StockDaoImpl;
import uk.jpmorgan.task.simple.stock.exceptions.StockException;
import uk.jpmorgan.task.simple.stock.vo.Trade;
import uk.jpmorgan.task.simple.stock.vo.TradeType;

/**
 *
 * @author idali
 */
public class TradeServiceImpl implements TradeService {
    
    private final StockDao dao;
    
    public TradeServiceImpl() {
        dao = new StockDaoImpl();
    }

    @Override
    public List<Trade> buy(String stockSymbol, int quantity, double price) {
        LocalDateTime time = LocalDateTime.now();
        Trade trade = new Trade(stockSymbol, quantity, price, TradeType.Buy, time);
        try {
            return dao.saveTrade(trade); 
        } catch (StockException ex) {
            throw ex;
        } 
    }

    @Override
    public List<Trade> sell(String stockSymbol, int quantity, double price) {
        LocalDateTime time = LocalDateTime.now();
        Trade trade = new Trade(stockSymbol, quantity, price, TradeType.Sell, time);
        try {
            return dao.saveTrade(trade); 
        } catch (StockException ex) {
            throw ex;
        } 
    }
    
}
