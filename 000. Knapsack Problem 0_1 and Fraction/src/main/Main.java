package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
	// subset sum is knapsack
	public int knapsack0_1(int []val, int []w, int W) {
		if(val == null || w == null || W == 0 || val.length == 0 || w.length == 0)
			return 0;
		int []dp = new int[W+1];
		for(int i = 0;i < val.length;i++) {
			for(int j = W;j >= w[i];j--) {
				dp[j] = Math.max(dp[j-1], val[i]+dp[j-w[i]]);
			}
		}
		return dp[W];
	}
	
	
	public double knapsack_fraction(int []val, int []w, int W) {
		int n = val.length;
		Item []items = new Item[val.length];
		for(int i = 0;i < n;i++) {
			items[i] = new Item(val[i], w[i]);
		}
		
		Arrays.sort(items, new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				return o2.cost.compareTo(o1.cost);
			}
		});
		
		double ans = 0;
		for(Item item : items) {
			int weight = item.w;
			int value = item.val;
			if(weight <= W) {
				W -= weight; 
				ans += value;
			} else {
				// Change W can be double so casting is must
				//double fraction = W/weight;
				double fraction = (double)W/(double)weight;
				ans += value*fraction;
				W -= (int)weight*fraction;
				break;
			}
					
		}
		return ans;
	}
	
	class Item {
		int val, w;
		Double cost;
		public Item(int val, int w) {
			this.val = val;
			this.w = w;
			this.cost = new Double(val/w);
			
		}
	}
}


public class Main {
	public static void main(String[] args) {
		int []val = {60,100,120}, w = {10,20,30};
		int W = 50;
		
		int[] wt = {10, 40, 20, 30}; 
        int[] value = {60, 40, 100, 120}; 
        int capacity = 50; 
		
		System.out.println(new Solution().knapsack0_1(val, w, W));
		System.out.println(new Solution().knapsack_fraction(value, wt, capacity));
	}
}
