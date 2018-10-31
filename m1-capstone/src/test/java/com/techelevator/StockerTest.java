package com.techelevator;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockerTest {

	private Stocker stock;
	
	@Before
	public void setup() throws IOException {
		stock = new Stocker();
	}
	
	@Test
	public void verify_create_items_from_file() {
		Assert.assertEquals(16, stock.createItemsFromFile().size());
	}
	
	@Test
	public void verify_valid_slots() {
		stock.createItemsFromFile();
		Assert.assertEquals(16, stock.createValidSlots().size());
	}
}
