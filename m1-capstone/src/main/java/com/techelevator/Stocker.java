package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Stocker {

	private File file;
	private Scanner reader;
	private ArrayList <VendingItem> items;
	private ArrayList <String> validSlots;
	
	public Stocker() throws IOException {
		this.file = new File("vendingmachine.csv");
		this.reader = new Scanner(file);
		this.items = new ArrayList<VendingItem>();
		this.validSlots = new ArrayList<String>();
	}
	
	public ArrayList<VendingItem> createItemsFromFile() {
		
		while (reader.hasNext()) {
			String line = reader.nextLine();
			String[] itemInfo = line.split("\\|");
			
			if (itemInfo[1].equals("CHIPS")) {
				VendingItem item = new Chips(itemInfo[0], itemInfo[2], Double.parseDouble(itemInfo[3]));
				items.add(item);
			}	
			if (itemInfo[1].equals("DRINK")) {
				VendingItem item = new Drink(itemInfo[0], itemInfo[2], Double.parseDouble(itemInfo[3]));
				items.add(item);
			}	
			if (itemInfo[1].equals("CANDY")) {
				VendingItem item = new Candy(itemInfo[0], itemInfo[2], Double.parseDouble(itemInfo[3]));
				items.add(item);
			}	
			if (itemInfo[1].equals("GUM")) {
				VendingItem item = new Gum(itemInfo[0], itemInfo[2], Double.parseDouble(itemInfo[3]));
				items.add(item);
			}	
		}
		return items;
	}
	
	public ArrayList<String> createValidSlots() {
		for (VendingItem item : items) {
			validSlots.add(item.getSlot());
		}
		return validSlots;
	}
}
