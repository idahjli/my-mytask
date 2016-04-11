/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.function.Predicate;
import java.util.stream.Collectors;
import uk.jpmorgan.task.simple.stock.vo.Stock;
import uk.jpmorgan.task.simple.stock.vo.StockType;
import uk.jpmorgan.task.simple.stock.vo.Trade;

/**
 *  Data source to keep data in memory
 * 
 * @author idali
 */
class DataStore {
     
    private static final Map<String, Stock> stockMap = new HashMap();
    static { 
        // stockMap to cache stock data in a map.
        stockMap.put("TEA", new Stock("TEA", StockType.Common, 0, 0, 100));
        stockMap.put("POP", new Stock("POP", StockType.Common, 8, 0, 100));
        stockMap.put("ALE", new Stock("ALE", StockType.Common, 23, 0, 60));
        stockMap.put("GIN", new Stock("GIN", StockType.Preferred, 8, 0.02, 100));
        stockMap.put("JOE", new Stock("JOE", StockType.Common, 13, 0, 250));
    }
    
    private final Map<Stock, List<Trade>> tradeMap = new HashMap<>();
    private final static int PAST_MINUTES = 16;
    private LocalDateTime fromTime;
    
    private static DataStore instance = null;

    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
    
    /**
     * Retrieves a stock by a given stock symbol
     * 
     * @param stockSymbol
     * @return the stock
     */
    public Stock getStockByStockSymbol(String stockSymbol) {
        return stockMap.get(stockSymbol);
    }
    
    /**
     * To save a trade into tradeMap
     * 
     * @param trade the trade to save
     * @param stock the stock which the trade made
     * 
     * @return List of trade
     */
    public List<Trade> addTradeIntoTradMap(Trade trade, Stock stock) {
        
        List<Trade> trades = tradeMap.get(stock);
        if(trades == null) {
            trades = new ArrayList<>(); 
        }
        trades.add(trade);
        tradeMap.put(stock, trades);
        return getAllTrades();
    }

    /**
     * Retrieves all the trade made within past fifteen minutes for a given stock
     * 
     * @param stock the given stock
     * @return List trades
     */
    public List<Trade> getTradesByStockWithinPastFifteenMunites(Stock stock) {
        fromTime = LocalDateTime.now().minusMinutes(PAST_MINUTES);
        List<Trade> trades = tradeMap.get(stock);
        if (trades == null) {
            return null;
        }
        return trades.stream()
                    .filter(isRecentTrade())
                    .collect(Collectors.toList());
    }

    /**
     * A predicate for checking if the trade made within last past fifteen minutes
     * @return Predicate of Trade
     */
    private Predicate<Trade> isRecentTrade() {
        return t -> t.getTime().isAfter(fromTime);
    }

    /**
     * Retrieves all the last trades from each stock
     * 
     * @return List of trades
     */
    public List<Trade> getLastTradeForEachStock() {
        List<Trade> trades = new ArrayList<>();

        tradeMap.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList())
                .stream()
                .forEach(s -> {
                    trades.add(s.get(s.size() - 1));
                });
        return trades;
    }
 
    /**
     * Retrieves all the trades in data source
     * 
     * @return List of trades
     */
    public List<Trade> getAllTrades() {
        return tradeMap.entrySet()
                .stream() 
                .flatMap(p -> p.getValue().stream())
                .collect(Collectors.toList());  
    }
    
    /**
     * Retrieves all the stocks in data source
     * 
     * @return List of stocks
     */
    public List<Stock> getAllStock() {
        return stockMap.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
