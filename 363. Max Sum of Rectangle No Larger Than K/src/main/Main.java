package main;

import java.util.TreeSet;
/* https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/83599/Accepted-C%2B%2B-codes-with-explanation-and-references
 * find maximum sub-array with sum <= k
 * First thing to note is that sum of sub-array (𝑖,𝑗] is just the sum of the first 𝑗 elements less the sum of the first 𝑖 elements. 
 * Store these cumulative sums in the array cum. Then the problem reduces to finding  𝑖,𝑗 such that 𝑖<𝑗 and 𝑐𝑢𝑚[𝑗]−𝑐𝑢𝑚[𝑖] is as close to 𝑘 
 * but lower than it.
 * 
 * To solve this, scan from left to right. Put the 𝑐𝑢𝑚[𝑖] values that you have encountered till now into a set. 
 * When you are processing 𝑐𝑢𝑚[𝑗] what you need to retrieve from the set is the smallest number in the set such which is bigger 
 * than 𝑐𝑢𝑚[𝑗]−𝑘. This lookup can be done in 𝑂(log𝑛) using upper_bound. Hence the overall complexity is 𝑂(𝑛log(𝑛)).
 * 
 * if sliding window is used, it skips sub-array that start from s and end at e whenever sum < 0
 */

class Solution {
	// Time 0(min(m,n)^2 * max(m,n) * lg(max(m,n))
	// space 0(max(m,n))
    public int maxSumSubmatrix(int[][] matrix, int k) {
    	int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
    	// consider every group of columns -> create row prefix sum and apply kadane's
    	for(int col_left = 0;col_left < n;col_left++) {
    		// size = row asusming row > col
    		int []prefix = new int[m];
    		// for every row it will create prefix sum of columns
    		for(int col_right = col_left;col_right < n;col_right++) {
    			for(int i = 0;i < m;i++) {
    				prefix[i] += matrix[i][col_right];
    			}
    			
    			// find maximum sum sub-array <= k i.e [i,j] i.e pre[j] - pre[i] <= k -> pre[j] - k <= pre[i]
    			// sum is pre[j]
    			TreeSet<Integer> set = new TreeSet<>();
    			int sum = 0;
    			set.add(0);
    			for(int p : prefix) {
    				sum += p;
    				// we need to find smallest left that is larger than sum - k
    				Integer high = set.ceiling(sum - k);
    				if(high != null) {
    					// sum - high is pre[j] - pre[i] that gives highest value <= k 
    					ans = Math.max(ans, sum - high);
    				}
    				set.add(sum);
    			}
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{1,0,1},{0,-2,3}};
		int k = 2;
		System.out.println(new Solution().maxSumSubmatrix(matrix, k));
	}
}
