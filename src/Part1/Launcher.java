/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;
import java.util.*;
/*
 *
 * @author youhan
 *
 * invoke methods of this class every user open the application
 * Use this class to check the system state and apply necessary operations
 * 
 * 
 */
public class Launcher {
    //store date in this attribute, each time user invoke the application
    //methods will be invoked to check the date
    //but may not be used if we don't create database, just in case
    public static int currentDateOfMonth;
    
    public static void newDate(){
        Date currentDate=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTime(currentDate);
        int date = cal.get(Calendar.DATE);
        if (date==currentDateOfMonth)
            ;
        else
            checkUpdate(date);
    }
    
    public static void checkUpdate(int _date){
        currentDateOfMonth=_date;
        //Will be implemented if needed later
    }
}
