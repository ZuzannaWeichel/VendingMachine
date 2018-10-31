package com.techelevator;

public class Chips extends VendingItem{

	public Chips(String slot, String name, double price) {
		super(slot, name, price);
	}
	
	@Override
	public String makeSound() {
		 return "Crunch Crunch, Yum!";
	}
}
