package my.eight.gs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainClass {

	public static void main(String[] args) {

		//		System.out.println(getMaxGroceryItems(50, 3, 10, "22,12,1,2,5,4,8,6,14,18,7"));
		System.out.println(noOfWaysToDrawTheGame(6));

	}

	static String getMaxGroceryItems(int salary, int itemsToBuyForCashback, int cashback, String prices) {
		if (salary < 1)
			return "0 " + salary;

		TreeSet<Integer> pricesList = new TreeSet<>();
		int remainingAmount = salary;
		int currentItemPrice = 0;
		int itemsbought = 0;
		int itemsForCashback = itemsToBuyForCashback;
		boolean discountCouponAdded = false;
		for (String s : prices.split(",")) {
			try {
				pricesList.add(Integer.parseInt(s));
			} catch (NumberFormatException e) {
			}
		}

		Iterator<Integer> iterator = pricesList.iterator();
		while (iterator.hasNext() && (currentItemPrice = iterator.next()) < remainingAmount) {
			remainingAmount -= currentItemPrice;
			++itemsbought;
			--itemsForCashback;
			if (itemsForCashback == 0) {
				remainingAmount += cashback;
				itemsForCashback = itemsToBuyForCashback;
			}
			if (!discountCouponAdded && itemsbought >= 5) {
				discountCouponAdded = true;
				remainingAmount += 10;
			}
		}

		return "" + itemsbought + " " + remainingAmount;
	}

	static int noOfWaysToDrawTheGame(int totalScore) {
		int remaining = totalScore;
		int numberOfSixes = remaining / 6;
		remaining = remaining % 6;
		if (remaining > 2)
			return numberOfSixes * 4 + 2;
		else if (remaining == 2)
			return numberOfSixes * 4 + 2;
		return numberOfSixes * 4;

	}
	
	static int noOfWaysToDrawTheGame2(int totalScore) {
		int remaining = totalScore;
		int numberOfSixes = remaining / 6;
		remaining = remaining % 6;
		//		if (remaining > 0) {
		//			numberofFours = remaining / 4;
		//			remaining = remaining % 4;
		//			if (remaining > 0)
		//				numberOfTwos = remaining / 2;
		//		}
		if (remaining > 2)
			return (int) (Math.pow(4, numberOfSixes) * 2);
		else if (remaining == 2)
			return (int) (Math.pow(4, numberOfSixes) * 1);
		return (int) (Math.pow(4, numberOfSixes));

	}
}
