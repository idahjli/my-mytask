/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.jpmorgan.task.simple.stock.utils;

import java.io.Console;
import java.util.List;
import uk.jpmorgan.task.simple.stock.exceptions.StockException;
import uk.jpmorgan.task.simple.stock.vo.Trade;
import uk.jpmorgan.task.simple.stock.vo.TradeType;

/**
 * A singleton class
 *
 * @author idali
 */
public class HelpClass {

    private static HelpClass instance = null;

    public static synchronized HelpClass getInstance() {
        if (instance == null) {
            instance = new HelpClass();
        }
        return instance;
    }
    
    public void initTaskOutput() {
        System.out.println();
        System.out.println("*-------------------------------------------------------------------------------------------*");
        System.out.println("* Please select number from 1 to 6 to start a task                                          *");
        System.out.println("* 1: calculate the dividend yield for a given stock                                         *");
        System.out.println("* 2: calculate the P/E Ratio for a given stock                                              *");
        System.out.println("* 3: record a trade, with timestamp, quantity of shares, buy or sell indicator and price    *");
        System.out.println("* 4: calculate Stock Price based on trades recorded in past 15 minutes                      *");
        System.out.println("* 5: calculate the GBCE All Share Index using the geometric mean of prices for all stocks   *");
        System.out.println("* 6: exit                                                                                   *");
        System.out.println("*-------------------------------------------------------------------------------------------*");
        System.out.println();
    }
    
    public void dividendYieldOutput(double dividendYield) {
        System.out.println("*"); 
        System.out.println("* dividend yield = " + dividendYield); 
        System.out.println("*------------------------------------------------------------------*");
        System.out.println();
    }

    public void peRatioOutput(double peRatio) {
        System.out.println("*");
        System.out.println("* P/E Ratio = " + peRatio);
        System.out.println("*------------------------------------------------------------------*");
        System.out.println();
    }

    public void gbceOutput(double gbce) {
        System.out.println("*");
        System.out.println("* GBCE All Share Index = " + gbce);
        System.out.println("*------------------------------------------------------------------*");
        System.out.println();
    }

    public void saveTradeOutput(List<Trade> trades) {
        System.out.println("*");
        System.out.println("* Trades [");
        trades.stream()
                .forEach(t -> {
                    System.out.println(t);
                });
        System.out.println("]");
        System.out.println("*------------------------------------------------------------------*");
        System.out.println();
    }
    
    public void stockPriceOutput(double stockPrice) {
        System.out.println("*"); 
        System.out.println("* stock price = " + stockPrice); 
        System.out.println("*------------------------------------------------------------------*");
        System.out.println();
    }

    public String stockSymbolInput(Console console) {
        System.out.println("*------------------------------------------------------------------*");
        System.out.print("* Enter stock symbol: ");
        return console.readLine();
    }
    
    public TradeType tradeTypeInput(Console console) { 
        System.out.print("* Enter type of trade (Buy or Sell): "); 
        String type = console.readLine();
        return type.toLowerCase().equals("buy") ? TradeType.Buy : TradeType.Sell;
    }
    
    public int quantityInput(Console console) { 
        System.out.print("* Entity quantity: ");

        int value = 0;
        while (value <= 0) {
            value = convertToInt(console.readLine());
            if (value <= 0) {
                System.out.println("Quantity can not <= 0 ");
                System.out.print("* Enter quantity: ");
            }
        }
        return value;
    }

    public double tickerPriceInput(Console console) { 
        
        System.out.print("* Enter ticker price: ");

        double value = 0;
        boolean isInvalidInput = true;
        while (isInvalidInput) {
            try {
                value = convertToDouble(console.readLine());
                isInvalidInput = false;
                if(value <= 0) {
                    System.out.println("Ticker price can not <= 0, ");
                    System.out.print("* Enter ticker price: ");
                    isInvalidInput = true;
                } 
            } catch (StockException ex) {
                System.out.println("Please enter a number for ticker price");
                System.out.print("* Enter ticker price: ");
            }
        }
        return value;
    }
    
    public double priceInput(Console console) { 
        
        System.out.print("* Enter price: ");

        double value = 0;
        boolean isInvalidInput = true;
        while (isInvalidInput) {
            try {
                value = convertToDouble(console.readLine());
                isInvalidInput = false;
                if(value <= 0) {
                    System.out.println("Price can not <= 0, ");
                    System.out.print("* Enter price: ");
                    isInvalidInput = true;
                } 
            } catch (StockException ex) {
                System.out.println("Please enter a number for  price");
                System.out.print("* Enter price: ");
            }
        }
        return value;
    }

    public double doubleValueInput(Console console) {

        double value = 0;
        boolean inValidInput = true;
        while (inValidInput) {
            try {
                value = HelpClass.getInstance().convertToDouble(console.readLine());
                inValidInput = false;
            } catch (StockException ex) {
                System.out.println("Please enter a number");
                System.out.print("* Enter ticker price: ");
            }
        }
        return value;
    }

    public int convertToInt(String strInt) {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a number");
            return -1;
        } 
    }
    
    public double convertToDouble(String strDouble) {
        try {
            return Integer.parseInt(strDouble);
        } catch(NumberFormatException ex) { 
            throw new StockException();
        } 
    }
    
    public void printErrorMessage(String errorMsg) {
        System.out.println("*------------------------------------------------------------------*");
        System.out.println("*  " + errorMsg); 
        System.out.println("*------------------------------------------------------------------*");
    }

}
