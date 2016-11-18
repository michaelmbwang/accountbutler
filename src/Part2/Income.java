/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;

import Part1.*;
import java.util.*;

/**
 *
 * @author youhan
 */
public class Income extends Exchange {
    private boolean type=false;
    
    //youhan:   this is used to store the date of receive money,
    //          it can be null if receive money instantly        
    private Date transferDate; 
    
    //youhan:   it's used to represent whether the income is periodic or not
    private boolean periodic; 
    
    
    
    
}