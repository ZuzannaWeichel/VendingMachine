package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.techelevator.Dispenser;
import com.techelevator.VendingItem;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private Dispenser dispenser;
	
	public Menu(InputStream input, OutputStream output, Dispenser disp) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		this.dispenser = disp;
	}

// Main Menu OPTIONS -> display menu && make choice  
	public Object getChoiceFromOptions(Object[] options) {
		return getChoiceFromOptions(options, false);
	}
// Purchase Menu options -> display && make choice		
	public Object getChoiceFromOptions(Object[] options, boolean showAmount) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options, showAmount);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromOptions(Object[] options, boolean showAmount, boolean dollar) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options, showAmount, dollar);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
// Get Choice component	
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
	
// Display Menu component
	private void displayMenuOptions(Object[] options, boolean showAmount) {
		displayMenuOptions(options, showAmount, false);
	}
	private void displayMenuOptions(Object[] options,boolean showAmount, boolean dollar) {
		out.println();
		if(dollar) {
			for(int i = 0; i < options.length; i++) {
				int optionNum = i+1;
				out.println(optionNum+")  $ "+options[i]+".00");
			}
		}else {
			for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		}
		if(showAmount) {
			out.print("Current Money Provided: $"+ String.format("%.2f",dispenser.getCurrentMoneyProvided())+"\n");
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

//	Select Product ->  display && make choice component
	public VendingItem getChoiceFormItems(ArrayList<VendingItem> items) {
		VendingItem choice = null;
		displayVendingItems(items);
		out.print("\nPlease choose an option >>> ");
		out.flush();	
		
		choice = getItemFromUserInput(items);
		return choice;
	}	

	// SELECT PRODUCT ->  make choice	
	private VendingItem getItemFromUserInput(ArrayList<VendingItem> items) {
		VendingItem choice = null;
		String userInput = in.nextLine();

		for(VendingItem item : items) {
			if(item.getSlot().equals(userInput)) {
				choice = item;
			}
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}	
		return choice;
	}

	
// Display item
	public void displayVendingItems( ArrayList<VendingItem> items) {
		String f = "%1$-3s %2$-18s %3$10.2f %4$5s";
	out.println();
	for(VendingItem item : items) {
		out.println(String.format(f,item.getSlot(), item.getName() ,item.getPrice(), item.getQuantity() +"/5" ));
		}
}

	public void insufficientFunds() {
		out.println("\n*** Insufficient funds provided ***");	
	}

	public void eatItems() {
		for(VendingItem item : dispenser.getList()) {
			out.println(" ~ "+item.makeSound()+" ~ ");
		}
		dispenser.clearList();
	}
	
	 public void giveChange() {
		 out.println("\n *** Here is your change ***\n *** "+dispenser.returnChange()+"***\n");
	 }

	public void outOfStock() {
		out.println("\n *** Item Sold Out ***");
	}
}
