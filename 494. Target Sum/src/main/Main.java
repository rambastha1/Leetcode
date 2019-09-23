package main;



class Solution {
	
/* https://leetcode.com/problems/target-sum/discuss/97335/Short-Java-DP-Solution-with-Explanation
 * Since subset sum is actually knapsack0-1
 * knapsack 0-1 is most generic
 * 
 * this is a classic knapsack problem
 * in knapsack, we decide whether we choose this element or not
 * In this question, we decide whether we add this element or minus it
 * 
 * So start with a two dimensional array dp[i][j] which means the number of ways for 
 * first i-th element to reach a sum j.
 * we can easily observe that dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i],
 * Another part which is quite confusing is return value, here we return dp[sum+S], why is that?
 * because dp's range starts from -sum --> 0 --> +sum
 * so we need to add sum first, then the total starts from 0, then we add S

 * Actually most of Sum problems can be treated as knapsack problem, hope it helps 
 * most of Sum problems can be treated as knapsack problem as most is subset sum 
 */
	
	// Time 0(N*Sum) Space 0(N*sum)
	public int findTargetSumWays(int[] nums, int S) {
		if(nums == null || nums.length == 0)
    		return 0;
		int sum = 0;
    	for(int num : nums)
    		sum += num;
    	// for +ve sum and -ve sum
    	if(sum < S || S < -sum) return 0;
    	int n = nums.length;
    	int [][]dp = new int[n+1][sum*2+1];
    	dp[0][0+sum] = 1; // 0 + sum means 0, 0 means -sum check graph in link
    	for(int i = 1;i <= n;i++) {
    		for(int j = 0;j < 2*sum+1;j++) {
    			if(j + nums[i-1] < 2*sum+1)
    				dp[i][j] += dp[i-1][j+nums[i-1]];
    			if(j - nums[i-1] >= 0)
    				dp[i][j] += dp[i-1][j-nums[i-1]]; 
    		}
    	}
    	return dp[n][S+sum];
	}
	
	
	
	/* Convert this into subset sum problem
	 * https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
	 * Time 0(N*S) Space 0(S)
	 */ 
    public int findTargetSumWays1(int[] nums, int S) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	int sum = 0;
    	for(int num : nums)
    		sum += num;
    	if(sum < S) return 0;
    	sum += S; 
    	if(sum%2!=0) return 0;
    	sum /= 2; 
    	
    	int []dp = new int[sum+1];
    	dp[0] = 1;
    	for(int num : nums) {
    		for(int j = sum;j >= num;j--)
    			dp[j] += dp[j- num];
    			
    	}
    	return dp[sum];
    }
    
    void print(int []nums) {
    	for(int a : nums)
    		System.out.print(a + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,1,1,1,1};
		int S = 3;
		System.out.println(new Solution().findTargetSumWays(nums, S));
		System.out.println(new Solution().findTargetSumWays1(nums, S));
	}
}