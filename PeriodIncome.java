/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;
import Part2.Income;
import java.util.Calendar;
/**
 *
 * @author elvis
 */
public class PeriodIncome {
    private int year;
    private int month;
    private int day;
    private int amount;
    private boolean periodInMonth;// set to true if is an income paid by month on certain date,such as salary.
   
    public int getYear(){
        return year;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getDay(){
        return day;
    }
    
  public int getAmount(){
      return amount;
  }
    
    
    public boolean getPeriodInMonth(){
        return periodInMonth;
    }
    
    public void setYear(int year){
        this.year=year;
    }
    
    public void setMonth(int month){
        this.month=month;
    }
    
    public void setDay(int day){
        this.day=day;
    }
    
    public void setAmount(int amount){
        this.amount=amount;
    }
    
    public void setPeriodInMonth(boolean b){
        this.periodInMonth=b;
    }
    public int getEndOfMonth(int month){
       if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
            return 31;
       else if(month==4||month==6||month==9||month==11)
           return 30;
       else if(isLeapYear(year))
           return 29;
       else 
           return 28;
   }
   
   public boolean isEndOfYear(){
       if(month==12)
           return true;
       else
           
       return false;
   }
   
   public boolean isLeapYear(int year){
       if(year%400==0)
           return true;
       else if(year%100==0)
           return false;
       else if(year%4==0)
           return true;
       else
           return false;
   }
   public void nextIncomeDate(){//to calculate next day
     if(isEndOfYear()){
         year++;
         month=1;
         day=day;
     }
      else{
         int nextIncomeMonth=month+1;
       
       
      if(getEndOfMonth(nextIncomeMonth)<getEndOfMonth(month)){
          day=getEndOfMonth(nextIncomeMonth);
      }
      else{
          day=day;
      }
       month++;
        year=year;
    }
   }
   
   public void applyPeriodIncome(){// if system date equals to income date, create an new income
       int y,m,d;   
    Calendar cal=Calendar.getInstance();   
    y=cal.get(Calendar.YEAR);   
    m=cal.get(Calendar.MONTH);   
    d=cal.get(Calendar.DATE);   
    if(y==year&&m==month&&d==day){
        Income i=new Income();
        i.setAmount(this.amount);
        this.nextIncomeDate();
      
    }
   }
}
