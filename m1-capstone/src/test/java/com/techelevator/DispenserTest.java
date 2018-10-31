package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DispenserTest {
	
	private Dispenser disp;

	@Before
	public void setup() {
		disp = new Dispenser();
		
	}
	
	@Test
	public void purchase_test() {
		disp.purchase(new Chips("D6", "ruffles", 78.23));
		Assert.assertEquals(1, disp.getList().size());
		disp.purchase(new Chips("A2", "curly", 1.25));
		Assert.assertEquals(2, disp.getList().size());
		disp.purchase(new Drink("D1", "tea", 4.25));
		Assert.assertEquals(3, disp.getList().size());
	}
	
	@Test
	public void add_cash_test() {
		disp.addCash(10);
		Assert.assertEquals(10, disp.getCurrentMoneyProvided(), 0.001);
		disp.addCash(2);
		Assert.assertEquals(12, disp.getCurrentMoneyProvided(), 0.001);
		disp.addCash(5);
		Assert.assertEquals(17, disp.getCurrentMoneyProvided(), 0.001);
	}

	@Test
	public void current_money_provided_test() {
		disp.addCash(1);
		Assert.assertEquals(1, disp.getCurrentMoneyProvided(), 0.001);
	}
	
	@Test
	public void return_quarters_test() {
		disp.addCash(10);
		disp.purchase(new Gum("D1", "gum", 1.5));
		Assert.assertEquals("34 quarter(s) ", disp.returnChange());
	}
	
	@Test
	public void return_dimes_test() {
		disp.addCash(1);
		disp.purchase(new Gum("D2", "gum", 0.80));
		Assert.assertEquals("2 dime(s) ", disp.returnChange());
	}
	
	@Test
	public void return_nickels_test() {
		disp.addCash(1);
		disp.purchase(new Gum("D2", "gum", 0.95));
		Assert.assertEquals("1 nickel(s) ", disp.returnChange());
	}
	@Test
	public void return_quarters_dimes_and_nickels() {
		disp.addCash(5);
		disp.purchase(new Gum("D2", "gum", 3.60));
		Assert.assertEquals("5 quarter(s) 1 dime(s) 1 nickel(s) ", disp.returnChange());
	}
	
	@Test
	public void return_quarters_and_nickles() {
		disp.addCash(5);
		disp.purchase(new Gum ("D1", "gum", 2.45));
		Assert.assertEquals("10 quarter(s) 1 nickel(s) ", disp.returnChange());
		disp.addCash(5);
		disp.purchase(new Gum ("D1", "gum", 2.70));
		Assert.assertEquals("9 quarter(s) 1 nickel(s) ", disp.returnChange());
	}
}
