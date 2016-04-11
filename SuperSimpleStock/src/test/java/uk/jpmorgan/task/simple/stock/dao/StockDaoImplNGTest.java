/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.dao;

import java.time.LocalDateTime;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass; 
import org.testng.annotations.BeforeClass; 
import org.testng.annotations.Test;
import uk.jpmorgan.task.simple.stock.vo.Stock;
import uk.jpmorgan.task.simple.stock.vo.Trade;
import uk.jpmorgan.task.simple.stock.vo.TradeType;

/**
 *
 * @author idali
 */
public class StockDaoImplNGTest {
    
    
    private StockDao instance;
    private final Trade trade;
    
    public StockDaoImplNGTest() {
        instance = new StockDaoImpl();
        trade = new Trade("GIN", 100, 88, TradeType.Buy, LocalDateTime.now()); 
    }

    @BeforeClass
    public static void setUpClass() throws Exception { 
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        instance = null; 
    }
 
    /**
     * Test of getStockByStockSymbol method, of class StockDaoImpl.
     */
    @Test
    public void test1GetStockByStockSymbol() {
        
        System.out.println("getStockByStockSymbol");
        
        String stockSymbol = "GIN";  
        Stock result = instance.getStockByStockSymbol(stockSymbol);
        assertEquals(result.getFixedDividend(), 0.02);
    }

    /**
     * Test of saveTrade method, of class StockDaoImpl.
     */
    @Test
    public void test2SaveTrade() {
        
        System.out.println("saveTrade"); 
 
        List result = instance.saveTrade(trade);
        assertEquals(result.size(), 1); 
    }

    /**
     * Test of getAllTrades method, of class StockDaoImpl.
     */
    @Test
    public void test3GetAllTrades() {
        
        System.out.println("getAllTrades"); 
          
         
        List<Trade> result = instance.getAllTrades();
        assertEquals(result.size(), 1); 
        assertEquals(result.get(0).getPrice(), 88.0);
    }

    /**
     * Test of getRecentTradeByStock method, of class StockDaoImpl.
     */
    @Test
    public void test4GetRecentTradeByStock() {
        
        System.out.println("getRecentTradeByStock"); 
     
        
        Stock stock = instance.getStockByStockSymbol("GIN");  
        List<Trade> result = instance.getRecentTradeByStock(stock);
        assertEquals(result.size(), 1); 
        
    }

    /**
     * Test of getLastTradeFromEachStock method, of class StockDaoImpl.
     */
    @Test
    public void test5GetLastTradeFromEachStock() {
        System.out.println("getLastTradeFromEachStock");
           
        List result = instance.getLastTradeFromEachStock();
        assertEquals(result.size(), 1); 
    }

    /**
     * Test of getAllStock method, of class StockDaoImpl.
     */
    @Test
    public void test6GetAllStock() {
        System.out.println("getAllStock"); 
         
        List result = instance.getAllStock();
        assertEquals(result.size(), 5); 
    }
    
}
