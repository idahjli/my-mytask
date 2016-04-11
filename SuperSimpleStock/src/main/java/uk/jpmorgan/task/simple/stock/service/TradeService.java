/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.service;

import java.util.List;
import uk.jpmorgan.task.simple.stock.vo.Trade;

/**
 * Trade service interface
 * 
 * @author idali
 */
public interface TradeService {

    /**
     * A trade with indicator buy
     *
     * @param stockSymbol the stock symbol for the given stock
     * @param quantity the quantity of this trade
     * @param price the price of this trade
     * 
     * @return List trades
     */
    public List<Trade> buy(String stockSymbol, int quantity, double price);

    /**
     * A trade with indicator sell
     *
     * @param stockSymbol the stock symbol for the given stock
     * @param quantity the quantity of this trade
     * @param price the price of this trade
     * 
     * @return List trades
     */
    public List<Trade> sell(String stockSymbol, int quantity, double price);
}
