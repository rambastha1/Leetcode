package main;

import java.util.Arrays;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems

class Solution {
	//Only One Transaction k = 1
	public int maxProfit1(int[] prices) {
		int i10 = 0, i11 = Integer.MIN_VALUE;
		for(int price : prices) {
			i10 = Math.max(i10, i11 + price);
			// Max of -ve means minimum of +ve like 1 < 2 but -1 > -2
			// Thus i11 gives minimum price at which we should buy share
			i11 = Math.max(i11, -price);
		}
		return i10;
	}
	//Infinite Transactions k = Int.MAX
	public int maxProfit2(int[] prices) {
		int i10 = 0, i11 = Integer.MIN_VALUE;
		for(int price : prices) {
			i10 = Math.max(i10, i11 + price);
			i11 = Math.max(i11, i10-price);
		}
		return i10;
	}
	
	//Only Two Transactions k = 2
	public int maxProfit3(int[] prices) {
        int i10 = 0, i11 = Integer.MIN_VALUE;
        int i20 = 0, i21 = Integer.MIN_VALUE;
        for(int price : prices) {
        	i20 = Integer.max(i20, i21 + price);
        	i21 = Integer.max(i21, i10 - price);
        	i10 = Integer.max(i10, i11 + price);
        	i11 = Integer.max(i11, -price);
        }
        return i20;
    }
	
	//Part IV atmost k transactions
	public int maxProfit4(int k, int[] prices) {
		if(k >= prices.length >>> 1) {
			int i10 = 0, i11 = Integer.MIN_VALUE;
			for(int price : prices) {
				i10 = Math.max(i10, i11 + price);
				i11 = Math.max(i11, -price);
			}
			return i10;
		}
		
		int []sell = new int[k+1], buy = new int[k+1];
		Arrays.fill(buy, Integer.MIN_VALUE);
		for(int price : prices) {
			for(int i = k;i > 0;i--) {
				sell[i] = Math.max(sell[i], buy[i] + price);
				buy[i] = Math.max(buy[i], sell[i-1] - price);
			}
		}
		return sell[k];
	}
	
	/*
	 * cooldown variable is actingas selling at i-2
	 */
	public int maxProfitCooldown(int[] prices) {
        int i10 = 0, cooldown = 0, i11 = Integer.MIN_VALUE;
        for(int price : prices) {
        	int temp = i10;
        	i10 = Integer.max(i10, i11 + price);
        	i11 = Integer.max(i11, cooldown - price);
        	cooldown = temp;
        }
        return i10;
    }
	
	//with transaction fee
	public int maxProfit_fee(int[] prices, int fee) {
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
		
		//int []prices = {7, 6, 4, 3, 1};
		int []prices = {7, 1, 5, 3, 6, 4};
		//int []prices = {2, 4, 1};
		//System.out.println(maxProfit(prices));
		int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println(new Solution().maxProfit1(prices));
		System.out.println(new Solution().maxProfit2(prices));
		int []prices3 = {3,3,5,0,0,3,1,4};
		System.out.println(new Solution().maxProfit3(prices3));
 		//System.out.println(sum(a));
		int []prices4 = {3,2,6,5,0,3};
		int k = 2;
		System.out.println(new Solution().maxProfit4(k, prices4));
		
		int []prices_cool = {1,2,3,0,2};
		System.out.println(new Solution().maxProfitCooldown(prices_cool));
		
		int []prices_fee = {1, 3, 2, 8, 4, 9};
		int fee = 2;
		System.out.println(new Solution().maxProfit_fee(prices_fee, fee));
	}
	
	public static int maxProfit(int[] prices) {
    
		if(prices.length == 0 || prices.length == 1)
			return 0;
		
		int min = Integer.MAX_VALUE, max = 0;
		for(int i = 0;i < prices.length;i++) {
			
			if(prices[i] < min)
				min = prices[i];
			if(prices[i] - min > max)
				max = prices[i] - min;
		}
		return max;
    }
}
