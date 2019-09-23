package main;

public class Main {

	public static void main(String[] args) {
		
	}
	
	public static int maxProfit(int[] prices) {
        
		int profit = 0;
		for(int i = 1;i < prices.length;i++)
			if(prices[i] > prices[i-1])
				profit += prices[i] - prices[i-1];
		return profit;
    }
}
