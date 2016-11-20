package Part3;
import Part1.*;
import Part2.*;

import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class ShowBalance {
	
	private ArrayList<Assets> allAssets;
	private float totalExpenseByMonth;
	private ArrayList<Expense> totalExpenses;
	private float totalIncomeByMonth;
	private ArrayList<Income> totalIncomes;
	private float[] values;
	private String[] names;
	private String title;
	private String targetMonth;
	
	public ShowBalance(ArrayList<Assets> assets, String month){
		this.values = new float[2];
	    this.names = new String[2];
	    this.title="Monthly Balance";
		this.allAssets = assets;
		this.targetMonth = month;
		this.totalExpenses = new ArrayList<>();
		this.totalIncomes = new ArrayList<>();
		this.totalExpenseByMonth = 0;
		this.totalIncomeByMonth = 0;
		for(Assets a: allAssets){
			for(Expense e: a.getExpense()){
				totalExpenses.add(e);
			}
			for(Income i: a.getIncome()){
				totalIncomes.add(i);
			}
		}
	}
	
	public float getIncomeByMonth(){
		for(Income e: totalIncomes){
			if(e.getMonth()==targetMonth)
				totalIncomeByMonth+=e.getAmount();				
		}
		return totalIncomeByMonth;
	}
	
	public float getExpenseByMonth(){
		for(Expense e: totalExpenses){
			if(e.getMonth()==targetMonth)
				totalExpenseByMonth+=e.getAmount();				
		}
		return totalExpenseByMonth;
	}
	
	public void showMonthlyBalanceChart(String[] argv) {
	    JFrame f = new JFrame();
	    f.setSize(400, 300);
	    values[0] = getIncomeByMonth();
	    names[0] = "Income";
	    values[1] = getExpenseByMonth();
	    names[1] = "Expense";
	    f.getContentPane().add(new DrawBarChart(values, names, title));

	    WindowListener wndCloser = new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    };
	    f.addWindowListener(wndCloser);
	    f.setVisible(true);
	  }
}
