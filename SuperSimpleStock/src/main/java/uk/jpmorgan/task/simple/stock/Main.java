/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock;

import java.io.Console; 
import java.util.List;
import uk.jpmorgan.task.simple.stock.exceptions.StockException;
import uk.jpmorgan.task.simple.stock.logic.StockLogic;
import uk.jpmorgan.task.simple.stock.logic.StockLogicImpl;
import uk.jpmorgan.task.simple.stock.utils.HelpClass;
import uk.jpmorgan.task.simple.stock.vo.Trade;
import uk.jpmorgan.task.simple.stock.vo.TradeType;

/**
 *
 * @author idali
 */
public class Main {
     
    public static void main(String[] args) {

        Console console = System.console();
        if (console == null) {
            System.out.println("No console: non-interactive mode!");
            System.exit(0);
        } 
        
        StockLogic logic = new StockLogicImpl();

        String stockSymbol;
        double tickerPrice;
        double price;
        int quantity;
        TradeType tradeType;
        boolean isContinue = true;
        
        double dividendYield;
        double peRatio;
        while(isContinue) {
            HelpClass.getInstance().initTaskOutput();

            int taskId = HelpClass.getInstance().convertToInt(console.readLine());
            switch (taskId) {
                case -1:
                    break;
                case 1:
                    stockSymbol = HelpClass.getInstance().stockSymbolInput(console); 
                    tickerPrice = HelpClass.getInstance().tickerPriceInput(console); 
                    try {
                        dividendYield = logic.calculateDividendYield(stockSymbol, tickerPrice);
                        HelpClass.getInstance().dividendYieldOutput(dividendYield);
                    } catch(StockException ex) {
                        HelpClass.getInstance().printErrorMessage(ex.getMessage());
                    } 
                    break;
                case 2:
                    stockSymbol = HelpClass.getInstance().stockSymbolInput(console);
                    tickerPrice = HelpClass.getInstance().tickerPriceInput(console); 
                    try {
                        peRatio = logic.calculatePERatio(stockSymbol, tickerPrice);
                        HelpClass.getInstance().peRatioOutput(peRatio);
                    } catch(StockException ex) {
                        HelpClass.getInstance().printErrorMessage(ex.getMessage());
                    }  
                    break;
                case 3:
                    stockSymbol = HelpClass.getInstance().stockSymbolInput(console);
                    quantity = HelpClass.getInstance().quantityInput(console);
                    price = HelpClass.getInstance().priceInput(console);
                    tradeType = HelpClass.getInstance().tradeTypeInput(console);
                    
                    try {
                        List<Trade> trades = logic.saveTrade(stockSymbol, quantity, price, tradeType);
                        HelpClass.getInstance().saveTradeOutput(trades);
                    } catch(StockException ex) {
                        HelpClass.getInstance().printErrorMessage(ex.getMessage());
                    }  
                    break;
                case 4:
                    stockSymbol = HelpClass.getInstance().stockSymbolInput(console);
                    
                    try {
                        double stockPrice = logic.calculateStockPrice(stockSymbol);
                        HelpClass.getInstance().stockPriceOutput(stockPrice);
                    } catch(StockException ex) {
                        HelpClass.getInstance().printErrorMessage(ex.getMessage());
                    }   
                    break;
                case 5:
                    try {
                        double gbce = logic.calculateGBCEShareIndex();
                        HelpClass.getInstance().gbceOutput(gbce);
                    } catch(StockException ex) {
                        HelpClass.getInstance().printErrorMessage(ex.getMessage());
                    }   
                    
                    break;
                case 6:
                    isContinue = false;
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Please select a number from 1 to 6.");
            }
        }

    } 
}
