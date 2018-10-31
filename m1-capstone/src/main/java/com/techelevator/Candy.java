package com.techelevator;

public class Candy extends VendingItem {

	public Candy(String slot, String name, double price) {
		super(slot, name, price);
	}

	@Override
	public String makeSound() {
		 return "Munch Munch, Yum!";
	} 
	
}
