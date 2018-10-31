package com.techelevator;

public class VendingItem {
	private String slot;
	private String name;
	private double price;
	private int quantity;
	
	
	public VendingItem(String slot, String name, double price) {
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.quantity = 5;
	}

	@Override
	public String toString() {
		 return name +" "+ quantity;
	}

	public String makeSound() {
		return "";
	}

	public String getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void reduceQuantity() {
		this.quantity --;
	}
}
