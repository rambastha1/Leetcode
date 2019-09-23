package main;

import java.util.ArrayList;
import java.util.List;

// https://www.geeksforgeeks.org/split-the-array-into-equal-sum-parts-according-to-given-conditions/
class Solution {
	public boolean partition(int []A) {
		if(A == null || A.length == 0) return false;
		int sum = 0;
		for(int a : A) sum += a;
		if(sum%2!=0) return false;
		return dfs(A, 0, 0, 0);
	}
	
	boolean dfs(int []A, int lsum, int rsum, int index) {
		if(index == A.length) 
			return lsum == rsum;
		
		if(A[index]%5==0)
			lsum += A[index];
		else if(A[index]%3==0)
			rsum += A[index];
		else {
			return dfs(A, lsum+A[index], rsum, index+1) || dfs(A, lsum, rsum+A[index], index+1);
		}
		return dfs(A, lsum, rsum, index+1);
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,4,3};
		System.out.println(new Solution().partition(A));
	}
}
