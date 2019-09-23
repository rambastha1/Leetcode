package main;

import java.util.ArrayList;
import java.util.List;
// Same problem as 40. Combination Sum II 
// https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/
class Solution {
	
	// Time and Space 0(N*K) 	
	void printsubset(int []A, int k, boolean [][]dp, List<Integer> list, int i) {
		
		if(i == 1 && k != 0 && dp[1][k]) {
			list.add(A[i-1]);
			System.out.println(list);
			list.clear();
			return;
		}
		
		if(i == 1 && k == 0) {
			System.out.println(list);
			list.clear();
			return;
		}
		
		if(dp[i][k]) {
			List<Integer> temp = new ArrayList<>();
			temp.addAll(list);
			printsubset(A, k, dp, temp, i-1);
		}
		
		if(k >= A[i-1] && k-A[i-1] >= 0 && dp[i-1][k-A[i-1]]) {
			list.add(A[i-1]);
			printsubset(A, k-A[i-1], dp, list, i-1);
		}
	}
	
	// Time and Space 0(N*K)
	public void subsetsum(int []A, int k) {
		if(A == null || A.length == 0)
			return ;
		int n = A.length;
		boolean [][]dp = new boolean[A.length+1][k+1];
		for(int i = 0;i <= A.length;i++)
			dp[i][0] = true;
		
		for(int i = 1;i <= n;i++) {
			for(int j = 1;j <= k;j++) {
				dp[i][j] = dp[i-1][j];
				if(j - A[i-1] >= 0)
					dp[i][j] = dp[i][j] || dp[i-1][j - A[i-1]];
			}
		}
		if(!dp[n][k]) return;
		
		List<Integer> list = new ArrayList<>();
		printsubset(A, k, dp, list, n);
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1, 2, 3, 4, 5};
		int k = 10;
		new Solution().subsetsum(A, k);
	}
}
