package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	// Using Map 0(N)
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		int maxdiff = 0;
    	for(int i = 0;i < difficulty.length;i++) {
    		maxdiff = Math.max(maxdiff, difficulty[i]);
    	}
    	
    	int []arr = new int[maxdiff+1];
    	for(int i = 0;i < profit.length;i++)
    		arr[difficulty[i]] = Math.max(arr[difficulty[i]], profit[i]);
    	
    	for(int i = 1;i <= maxdiff;i++)
    		arr[i] = Math.max(arr[i-1], arr[i]);
    	
    	int ans = 0;
    	for(int i = 0;i < worker.length;i++) {
    		ans += worker[i] > maxdiff?arr[maxdiff]:arr[worker[i]];
    	}
    	return ans;
	}
	
	// Sort and binary search Time 0(NlgN)
}

public class Main {
	public static void main(String[] args) {
		//int []difficulty = {2,4,6,8,10}, profit = {10,20,30,40,50}, worker = {4,5,6,7};
		int []difficulty = {13,37,58}, profit = {4,90,96}, worker = {34,73,45};
		System.out.println(new Solution().maxProfitAssignment(difficulty, profit, worker));
	}
}
