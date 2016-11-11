/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;

/**
 *
 * @author duyue_000
 */
public class UserName {
    private static String userName = null;
    
    public static String getUserName(){
        return userName;
    }
    
    public static void setUserName(String user){
        userName = user;
    }
}
