/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;

/**
 *
 * @author elvis
 */
import Part1.Assets;
import Part2.Exchange;
import Part2.Income;
import Part2.Expense;

public class Transfer {
    private Assets fromAssets;
    private Assets toAssets;
    private Expense expense=new Expense(){};
    private Income income=new Income(){};
    
    public void setFromAssets(Assets assets){
        fromAssets=assets;
    }
    
    public void setToAssets(Assets assets){
        toAssets=assets;
    }
    
    public void setAmount(float amount){
        income.setAmount(amount);
        expense.setAmount(amount*-1);
    }
    
    public void setAssetsType(Assets fromAssetsType,Assets toAssetsType){
        income.setAssetsType(toAssets);
        expense.setAssetsType(fromAssetsType);
    }
    
    public void applyTransfer(){
        fromAssets.applyExchange(expense);
        toAssets.applyExchange(income);
    }
}
