package com.techelevator;

import java.util.ArrayList;

public class Dispenser {
	
	private double currentMoneyProvided = 0;
	private ArrayList<VendingItem> itemsPurchased = new ArrayList<>();
	
	public void purchase(VendingItem item) {
		itemsPurchased.add(item);	
		currentMoneyProvided = ((currentMoneyProvided * 100) - (item.getPrice() * 100)) /100;
	}
		
	public String returnChange() {	
		int [] coins = new int [] {25,10,5};
		int change = (int) Math.ceil(currentMoneyProvided * 100);
		currentMoneyProvided = 0;
		String toReturn = "";
		
		for ( int i = 0; i < coins.length ; i++) {
			double factor = change * 1.0 /coins[i];
			int numberOfCoins = (int)factor;
			if(numberOfCoins > 0 ) {
				toReturn += numberOfCoins+" "+ switchToCoins(coins[i]) +" ";
				change = ((change * 100) - (numberOfCoins * coins[i] * 100)) / 100;
			}	
		}
		return toReturn;
	}
	
	private String switchToCoins(int coin) {
		String coins = "";
		switch (coin) {
		case 25 : coins = "quarter(s)";
		break;
		case 10 : coins = "dime(s)";
		break;
		case 5 : coins = "nickel(s)";
		break;
		default : coins = "";
		}
		return coins;
	}
	
	public void addCash(int cash) {
		currentMoneyProvided += cash;
	}
	
	public double getCurrentMoneyProvided() {
		return currentMoneyProvided;
	}
	
	public ArrayList<VendingItem> getList() {
		return itemsPurchased;
	}
	
	public void clearList() {
		itemsPurchased.clear();
	}
}
