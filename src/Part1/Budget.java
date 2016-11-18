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
public class Budget {
    //it refers to different budget types and their respective value
    private HashMap budgetTypes = new HashMap();
    private float notifyPercent;
    
    public Budget(ArrayList<String> budgetNames){
        //stored in format of (sub-budget type, value) in HashMap
        for (int i=0;i<budgetNames.size();i++){
            budgetTypes.put(budgetNames.get(i), 0);
        }
    }    
    
    //set notify threshold
    public void setNotifyValue(float percent){
        this.notifyPercent=percent;
    }
    
    //Use this method to set the value for budget of each kind 
    public void change(String typeName,float amount){
        float newValue = (float)budgetTypes.get(typeName);
        budgetTypes.put(typeName, newValue);
    }
    
    public HashMap getMap(){
        return budgetTypes;
    }
}