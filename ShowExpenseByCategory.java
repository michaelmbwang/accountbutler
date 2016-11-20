package Part3;

import Part1.*;
import Part2.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class ShowExpenseByCategory {
	
	private ArrayList<Assets> allAssets;
	private float totalExpenseByMonth;
	private ArrayList<Expense> totalExpenses;
	private HashMap<String, ExpenseCategory> categoryExpensesMap;
	private String[] names;
	private float[] values;
//	private String title;
	private String targetMonth;
	
	public ShowExpenseByCategory(ArrayList<Assets> assets, String month){
		this.values = new float[2];
	    this.names = new String[2];
//	    this.title="Expenses by category";
		this.allAssets = assets;
		this.targetMonth = month;
		this.totalExpenses = new ArrayList<>();
		this.totalExpenseByMonth = 0;
		this.categoryExpensesMap = new HashMap<>();
		for(Assets a: allAssets){
			for(Expense e: a.getExpense()){
				totalExpenses.add(e);
			}
		}
		for(Expense e: totalExpenses){
			if(!categoryExpensesMap.containsKey(e.getCategory())){
				categoryExpensesMap.put(e.getCategory(), new ExpenseCategory(e.getCategory()));
			}
			categoryExpensesMap.get(e.getCategory()).addExpense(e);
		}
	}
	
	public float getExpenseByMonth(){
		for(Expense e: totalExpenses){
			if(e.getMonth()==targetMonth)
				totalExpenseByMonth+=e.getAmount();				
		}
		return totalExpenseByMonth;
	}
	
	public void showMonthlyBalanceChart(String[] argv) {
		
		int i=0;
	    for(Map.Entry<String, ExpenseCategory> entry : categoryExpensesMap.entrySet()) {
	        names[i]=entry.getKey();
	        values[i]=entry.getValue().getTotalExpenseByCategory();
	       
	    }
		JFrame f = new JFrame();
	    f.getContentPane().add(new DrawPieChart(values, names));
	    f.setSize(400, 200);
	    f.setVisible(true);
	    WindowListener wndCloser = new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    };
	    f.addWindowListener(wndCloser);
	    f.setVisible(true);
	  }
}
