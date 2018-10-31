package com.techelevator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;


public class PlayGround {

	public static void main(String[] args) throws IOException {
	
//		
		System.out.println(LocalDate.now());
		//int [] coins = new int [] {25,10,5};
//		int change = (int)Math.ceil(2.55 * 100);
//	
//		System.out.println(change);
//		
//		String toReturn = "";
//		
//		for ( int i = 0; i < coins.length ; i++) {
//		int numberOfCoins = (change - (change % coins[i]))/coins[i];
//			if ( numberOfCoins > 0 ) {
//				toReturn += numberOfCoins+" "+switchToCoins(numberOfCoins);
//				change -= numberOfCoins * coins[i];
//			}
//		}		
//				
////		System.out.println(numberOfCoins+" <- num of C");
////		System.out.println(factor+" <- factor");
//		
//		System.out.println(toReturn);
		
		System.out.println(Arrays.toString(fizz3(5,10)) );
		
		
	}
	public static String switchToCoins(int coin) {
		String coins = "";
		switch (coin) {
		case 25 : coins = "quarter(s)";
		break;
		case 10 : coins = "dime(s)";
		break;
		case 5 : coins = "nickel(s)";
		break;
		default : coins = "";
		}
		return coins;
	}
	
	public static int[] fizz3(int start, int end) {
		int arrL = end-start;
		int[]toReturn = new int[arrL];
		for(int i = 0,j =start; i<arrL && j<end; i++, j++) {
			toReturn[i] = j;
		}
		return toReturn;
	}
}
