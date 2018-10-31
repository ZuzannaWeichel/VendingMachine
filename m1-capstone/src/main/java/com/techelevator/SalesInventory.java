package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesInventory {

	private Map<String, Integer> inventory;
	private File file;
	private Scanner reader;
	private double total = 0;
	
	public SalesInventory() {
		inventory = new HashMap<>();
		file = new File("SalesReport.txt");
		try {
			makeFile();
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void makeFile() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void recordItem(VendingItem item) {	
		
		readFromMap();
		addNewItem(item);
		
	}
	 
	private void readFromMap() {
		while (reader.hasNext()) {
			String next = reader.nextLine();
			if (next.contains("TOTAL SALES")) {
				String[]lastLine = next.split("\\$");
				total = Double.parseDouble(lastLine[1]);
			}
			String[] line = next.split("\\|");
			if(line.length == 2) {
				addItemsToMap(line[0], line[1]);
			}
		}
	}
	
	private void addNewItem(VendingItem item) {
		inventory.putIfAbsent(item.getName(), 0);
		inventory.put(item.getName(), inventory.get(item.getName())+1);
		total = ((total * 100) + (item.getPrice() * 100)) /100;
	}
	
	private void addItemsToMap (String name, String quantity) {
		int value = Integer.parseInt(quantity);	
		inventory.put(name, value);
	}
	
	public void writeMapToFile() {
		try (FileWriter writer = new FileWriter(file)){
			for (String name : inventory.keySet()) {
			String quantity = String.valueOf(inventory.get(name));
			writer.write(name+"|"+quantity+"\n");
			writer.flush();
			}
			writer.write("\n**TOTAL SALES** $"+String.format("%.2f",total));
			writer.flush();
		}catch (IOException e) {
				e.printStackTrace();
			
		}
	}
}
