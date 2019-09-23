package main;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems

class Solution {
    public int maxProfit(int[] prices, int fee) {
    	int i10 = 0, i11 = Integer.MIN_VALUE;
    	for(int price : prices) {
    		i10 = Math.max(i10, i11 + price);
    		i11 = Math.max(i11, i10 - price - fee);
    	}
    	return i10;
    }
}

public class Main {
	public static void main(String[] args) {
		int []prices = {1,3,2,8,4,9};
		int fee = 2;
		System.out.println(new Solution().maxProfit(prices, fee));
	}
}