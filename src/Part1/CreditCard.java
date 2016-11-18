/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;

import Part2.Expense;
import java.util.*;

/**
 *
 * @author youhan
 * 
 * this class is optional, for your preference,
 * if you have time, it's better to implement the GUI for this class,
 * but should cancel the other functions for credit card,
 * just preserve necessary functions.
 */
public class CreditCard {
    //link it to one Assets and pay from assets
    private Assets associatedAccount;
    
    //maintain lists of all expenses
    private ArrayList<Expense> unPaidExpenses;

    private ArrayList<Expense> pastExpenses;
    
    //end Date of every month, from 1 to 30
    private int endDate;
    
    //the date to pay, from 1 to 30, will require some adjustment for February
    private int payDate;
    
    //the limit amount for one period
    private float limit;
    
    //card type, "Visa" for example, will be passed from GUI side
    private String type;
    
    private float totalAmount;
    
    public ArrayList<Expense> getUnPaidExpenses(){return unPaidExpenses;}
    public ArrayList<Expense> getPastExpenses(){return pastExpenses;}
    public int getPayDate(){return payDate;}
    public float getLimit(){return limit;}
            
    public CreditCard(Assets associatedAccount, int endDate, int payDate, float limit, String type){
        this.associatedAccount=associatedAccount;
        this.endDate=endDate;
        this.payDate=payDate;
        this.limit=limit;
        this.type=type;
        this.totalAmount=0;
    }
    
    public void addNewExpense(Expense e){
        if (totalAmount+e.getAmount()<=limit){
            unPaidExpenses.add(e);
            totalAmount+=e.getAmount();
        }else
            reachLimit();
    }
    
    public void reachLimit(){
        //to handle and output message in GUI
    }
    //move to New period
    public void moveToNew(){
        Expense expense=new Expense();
        associatedAccount.applyExchange(expense);
        totalAmount=0;
    }
    
}
