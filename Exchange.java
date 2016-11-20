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
import Part1.Assets;
import java.util.Date;

public class Exchange {
    private float amount;
    private boolean type;// true if expense,false if income
    private Assets assetsType;
    private Date date;// date set this exchange
    private String location;// location where user sets this exchange 
    private boolean applied;//true if exchange is applied
    private String comment;//comment user sets to this exchange
    private boolean notification; //true if need to notify
    private String notiMsg;//comment for notification
   
    //Elvis, please modify the following lines
    private String month;
    private String category;
    public String getMonth(){
    	String month="";
    	return month;
    }
    public String getCategory(){
    	return category;
    }
    ///////////
   public float getAmount(){
        return amount;
    }
    
    public boolean getType(){
        return type;
    }
    public Assets getAssetsType(){
        return assetsType;
    }
    
    public Date getTime(){
        return date;
    }
    
    public String getLocation(){
        return location;
    }
    
    public boolean getApplied(){
        return applied;
    }
    
    public String getComment(){
        return comment;
    }
    
    public boolean getNotification(){
        return notification;
    }
    public String getNotiMsg(){
        if(this.notification)
            return notiMsg;
        else
            return null;// notification is set to false, should not notify
    }
    
    public void setAmount(float amount){
        this.amount=amount;
    }
    
    protected void setType(boolean type){
        this.type=type;
    }
 
    public void setAssetsType(Assets assets){
        this.assetsType=assets;
    }
    
    public void setDate(Date date){
        this.date=date;
    }
    
    public void setLocation(String location){
        this.location=location;
    }
    
    public void setApplied(boolean applied){
        this.applied=applied;
    }
    
    public void setComment(String comment){
        this.comment=comment;
    }
    
    public void setNotification(boolean notification){
        this.notification=notification;
    }
    
    public void setNotiMsg(String  msg){
        this.notiMsg=msg;
    }
}
