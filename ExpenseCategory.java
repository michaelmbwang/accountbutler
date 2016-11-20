package Part3;

import Part2.*;
import java.util.*;

public class ExpenseCategory {
	
	private ArrayList<Expense> expensesByCategory;
	private String expenseCategory;
	private float totalExpensesByCategory;
	
	public ExpenseCategory(String category){
		this.expenseCategory = category;
		expensesByCategory = new ArrayList<>();
		this.totalExpensesByCategory = 0;
	}
	
	public void addExpense(Expense e){
		expensesByCategory.add(e);
	}
	
	public String getCategory(){
		return expenseCategory;
	}
	
	public float getTotalExpenseByCategory(){
		for(Expense e: expensesByCategory){
			totalExpensesByCategory+=e.getAmount();
		}
		return totalExpensesByCategory;
	}
}
