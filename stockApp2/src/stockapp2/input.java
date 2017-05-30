/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringpermutation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import yahoofinance.YahooFinance;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class input
{
    public static void main (String[] args) throws IOException
    {
        //Instantiate scanner
        Scanner scan = new Scanner (System.in);
        
        //Variables
        double stockPrice;
        double movingav50day;
        double shortRatio;
        double EPS;
        boolean peRatio;
        int valueRating = 0;
        double week52high;
        double week52low;
        int volatilityRating = 0;
        String risk;
        double price;
        String securityRiskClass;
        
        //Intro screen
        JOptionPane.showMessageDialog(null, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + 
                                            "\nValue Investor Tool" +
                                            "\n*************************************************" + 
                                            "\nThis app is made for retail investors" +
                                            "\nWho'd like to seek unbias fundamentally " +  
                                            "\nbased investment reccomendations on " +  
                                            "\nspecific securities based on the " + 
                                            "\ninvestors appetite for risk and returns.");
         
        //Prompt user for name of company
        String stockName = JOptionPane.showInputDialog("Name of company?");
         
        //Establish needed values
        Stock userSelected = StockFetcher.getStock(stockName);
        EPS = userSelected.getEps();
        stockPrice = userSelected.getPrice();
        week52high = userSelected.getWeek52high();
        week52low = userSelected.getWeek52low();
        movingav50day = userSelected.getMovingav50day();
        shortRatio = userSelected.getShortRatio();
        price = userSelected.getPrice();
                
                //Value calculations
                
                    //P / E ratio valuation
                    if((stockPrice / EPS) > 6)
                    {
                        valueRating++;
                    }
                    if((stockPrice / EPS) > 11)
                    {
                        valueRating++;
                        valueRating++;
                    }
                    if((stockPrice / EPS) > 11)
                    { 
                        valueRating++;
                        valueRating++;
                        valueRating++;
                    }
                    
                    //P / E devaluation
                    if((stockPrice / EPS) < 3)
                    {
                        valueRating--;
                    }
                    if((stockPrice / EPS) < 2)
                    {
                        valueRating--;
                        valueRating--;
                    }
                    if((stockPrice / EPS) < 2)
                    {
                        valueRating--;
                        valueRating--;
                        valueRating--;
                    }

                    
                    //Moving day average valuation
                    if(movingav50day > week52high * .75)
                    {
                        valueRating++;
                    }
                    if(movingav50day > week52high * .80)
                    {
                        valueRating++;
                        valueRating++;
                    }
                    if(movingav50day > week52high * .85)
                    {
                        valueRating++;
                        valueRating++;
                        valueRating++;
                    }
                    
                    //Moving day average devaluation 
                    if(movingav50day < week52high * .70)
                    {
                        valueRating--;
                    }
                    if(movingav50day < week52high * .65)
                    {
                        valueRating--;
                        valueRating--;
                    }
                    if(movingav50day < week52high * .55)
                    {
                        valueRating--;
                        valueRating--;
                        valueRating--;
                    }
                    
                 
            //Short rate calculations
            if(shortRatio < 5)
            {
                valueRating++;
            }
            if(shortRatio > 8)
            {
                valueRating--;
            }
                
            //Volatility
            if(week52low < (week52high * .75))
            {
                volatilityRating++;
            }
            if(week52low < (week52high * .65))
            {
                volatilityRating++;
                volatilityRating++;
            }
            if(week52low < (week52high * .55))
            {
                volatilityRating++;
                volatilityRating++;
                volatilityRating++;
            }
            
            if(week52low > (week52high * .75))
            {
                volatilityRating--;
            }
            if(week52low > (week52high * .85))
            {
                volatilityRating--;
                volatilityRating--;
            }
            if(week52low > (week52high * .95))
            {
                volatilityRating--;
                volatilityRating--;
                volatilityRating--;
            }
            
            //Declare needed boolean values for reccomendation
            boolean conservativeBuy = false;
            boolean calculatedBuy = false;
            boolean riskyBuy = false;
            boolean speculative = false;
            //Determine if investment type is viable
            if(volatilityRating <= 4 && valueRating > 4)
            {
                
                    conservativeBuy = true;    
            }
            if(volatilityRating < 6 && valueRating > 6)
            {
                
                    calculatedBuy = true;    
            }
            if(volatilityRating < 8 && valueRating > 8)
            {
                
                    riskyBuy = true;    
            }
            if(volatilityRating < 10 && valueRating > 10)
            {
                
                    speculative = true;    
            }
            
          
        JOptionPane.showMessageDialog(null, "Value Rating: " + valueRating + "\n" + 
                                            "Volatility Rating: " + volatilityRating +
                                            "\n\nPurchase Reccomendations: " + 
                                            "\n\nFor conservative investors: " + conservativeBuy + 
                                            "\nFor calculated investors: " + calculatedBuy + 
                                            "\nFor risky investors: " + riskyBuy + 
                                            "\nFor speculators: " + speculative);
             
    } 
       
}
