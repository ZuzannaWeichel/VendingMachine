package com.techelevator;

public class Drink extends VendingItem {

	public Drink(String slot, String name, double price) {
		super(slot, name, price);
	}
	
	@Override
	 public String makeSound() {
		 return "Glug Glug, Yum!";
	 }

}
