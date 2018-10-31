package com.techelevator;

public class Gum extends VendingItem {
			
	public Gum(String slot, String name, double price) {
		super(slot, name, price);
	}

	@Override
	public String makeSound() {
		 return "Chew Chew, Yum!";
	}
	 
}
