/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;
import Part2.*;
import Part3.*;
import java.util.*;

/**
 *
 * @author youhan
 */
public class Assets {
    private String[] assetType = {"Checking account","Cash","Savings","Investment","Others"};
    protected ArrayList<Income> allIncomes;
    protected ArrayList<Expense> allExpenses;
    protected ArrayList<CreditCard> allCards;
    
    protected float balance;
    protected String name;
    protected int color;
    
    
    public Assets(float initialBalance, String name, String color, String type){
        this.allIncomes = new ArrayList<>();
        this.allExpenses = new ArrayList<>();
        this.name=name;
        this.color=getColor(color);
    }
    
    public float getBalance(){
        return balance;
    }
    
    //this method will get return color index according to user's input
    public int getColor(String color){
        switch(color){
            case "red":     return 1;
            case "green":   return 2;
            case "purple":  return 3;
            case "orange":  return 4;
            case "grey":    return 5;
            case "brown":   return 6;
            case "blue":    return 7;
            case "yellow":  return 8;
            case "black":   return 9;
            case "pink":    return 10;
            case "ching":   return 11;
            default:        return 0;
        }
    }
    
    //this method will apply the exchange of asset
    //and modify parameters accordingly
    public void applyExchange(Exchange e){
        if (e.getType()){
            changeBalance(e.getAmount()*-1);
            allExpenses.add((Expense)e);
        }
        else{
            changeBalance(e.getAmount());
            allIncomes.add((Income)e);
        }
    }
    
    public int numOfExpenses(){
        return allExpenses.size();
    }
    
    public int numOfIncomes(){
        return allIncomes.size();
    }
    
    public void changeBalance(Float f){
        balance+=f;
    }
    
    //add new credit card
    public void addCreditCard(CreditCard c){
        allCards.add(c);
    }
    
    
}