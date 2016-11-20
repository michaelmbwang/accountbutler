/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;



/**
 *
 * @author youhan
 */
public class Income extends Exchange {
   
    private boolean type;//false if expense
    private String incomeType;
    
   
    protected void setType(boolean type){
        this.type=false;
    }
    
    public String getIncomType(){
        return incomeType;
    }
    
    public void setIncomeType(){
        this.incomeType="Received";
    }
   
}