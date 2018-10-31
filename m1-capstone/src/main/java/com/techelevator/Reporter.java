package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporter {
	private File file;
	private FileWriter writer;
	private SimpleDateFormat dateFormat;
	private double currentBefore;
    private String f;
    private Date now;
	
	public Reporter() throws IOException {
		this.file = new File("Log.txt");
		this.writer = new FileWriter(file, true);
		this.dateFormat = new SimpleDateFormat(" MM/dd/yyy hh:mm:ss a ");
		this.f = "%1$-47s %2$10s %3$10s";
		
	}
	
	public void feedMoney(int cash, double current) {
		try {
			if (! file.exists()) {		
				file.createNewFile();
			}
			now = new Date();
			String currentF = String.format("%.2f",current);
			writer.write(String.format(f, dateFormat.format(now)+" FEED MONEY:", "$"+cash+".00","$"+currentF)+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void monitorSale(VendingItem item, double current) {
		now = new Date();
		String name = item.getName()+" "+item.getSlot();
		String cash = "$"+String.format("%.2f",currentBefore);
		String currentF = "$"+String.format("%.2f",current);
		try {
			writer.write(String.format(f, dateFormat.format(now)+" "+name, cash, currentF)+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void giveChange( double current) {
		now = new Date();
		String cash = "$"+String.format("%.2f",currentBefore);
		String currentF = "$"+String.format("%.2f",current);
		try {
			writer.write(String.format(f, dateFormat.format(now)+" GIVE CHANGE:",cash, currentF)+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setCurrent(double current) {
		this.currentBefore = current;
	}
	
}
