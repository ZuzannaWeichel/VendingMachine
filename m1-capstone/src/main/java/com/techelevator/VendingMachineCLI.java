package com.techelevator;

import java.io.IOException;
import java.util.ArrayList;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE };
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	
	private Menu menu;
	private Dispenser dispenser;
	private Stocker stocker;
	private Reporter reporter;
	private SalesInventory inventory;
	private Object[] bills;
	
	public VendingMachineCLI(Menu menu, Dispenser dispenser) throws IOException {
		this.menu = menu;
		this.dispenser = dispenser;
		this.stocker = new Stocker();
		this.reporter = new Reporter();
		this.inventory = new SalesInventory();
		this.bills = new Integer[] {1,2,5,10};
	}
	
	public void run() {
		while(true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			ArrayList<VendingItem> items = stocker.createItemsFromFile();
				
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				menu.displayVendingItems(items);
			
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while(true) {
					String pChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS,true);
				
					if (pChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						Object cash = menu.getChoiceFromOptions(bills, false, true);
						dispenser.addCash((int)cash);	
						reporter.feedMoney((int)cash, dispenser.getCurrentMoneyProvided());
					}else if (pChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						VendingItem item = menu.getChoiceFormItems(items);
						if (item == null) {
							continue;
						}
						if (dispenser.getCurrentMoneyProvided() < item.getPrice()) {
							menu.insufficientFunds();
							continue;
						}
						if (item.getQuantity() == 0 ) {
							menu.outOfStock();
							continue;
						}
						reporter.setCurrent(dispenser.getCurrentMoneyProvided());
						dispenser.purchase(item);
						reporter.monitorSale(item, dispenser.getCurrentMoneyProvided());
						inventory.recordItem(item);
						item.reduceQuantity();
					}else if (pChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						reporter.setCurrent(dispenser.getCurrentMoneyProvided());
						menu.giveChange();
						reporter.giveChange(dispenser.getCurrentMoneyProvided());
						menu.eatItems();
						inventory.writeMapToFile();
						break;
					}
				}		
			}
		}	
	}
	
	public static void main(String[] args) {
		Dispenser dispenser = new Dispenser();
		Menu menu = new Menu(System.in, System.out, dispenser);
		VendingMachineCLI cli;
		try {
			cli = new VendingMachineCLI(menu,dispenser);
			cli.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
