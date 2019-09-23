package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/partition-set-k-subsets-equal-sum/
// https://www.hackerearth.com/practice/algorithms/dynamic-programming/bit-masking/tutorial/

/* dp[i] denotes - whether sum can be divided into k subsets of equal sum
 * total[i] denotes total sum using that nums[i] less than target sum
 * Now loop over entire power set of nums, set the jth bit using
 * newset = i | (1<<j) <="" code=""> 
 * and check whether this sum is less or equal to target sum. 
 * If yes, mark this index true and set its total.
 * The last index of D tells whether whole array can be divided into k subsets of equal sum.
 */

class Solution {
    // Dp with Bit Masking 
	// Time 0(N*2^N) Space 0(2^N)
	public boolean canPartitionKSubsets(int[] nums, int k) {
		if(nums == null || nums.length == 0)
			return false;
		
		int n = nums.length;
		//result array
		boolean dp[] = new boolean[1<<n];
		int total[] = new int[1<<n];
		dp[0] = true;
		
		int sum = 0;
		for(int num : nums)
			sum += num;
		Arrays.sort(nums);
		
		if(sum%k != 0) 
			return false;
		sum /= k;
		if(nums[n-1] > sum)
			return false;
		// Loop over power set
		for(int i = 0;i < (1<<n);i++) {
			if(dp[i]) {
				// Loop over each element to find subset
				for(int j = 0;j < n;j++) {
					// set the jth bit 
					int temp = i | (1 << j);
					if(temp != i) {
						// if total sum is less than target store in dp and total array
						if(nums[j] <= (sum- (total[i]%sum))) {
							dp[temp] = true;
							total[temp] = nums[j] + total[i];
						} else
							break;
					}
				}
			}
		}
		return dp[(1<<n) - 1];
	}
	
	/* DFS solution 
	 * Time 0 (N^K) though it seems linear cause of use of visited, this problem is NPC
	 */
	public boolean canPartitionKSubsets1(int[] nums, int k) {
    	if(nums == null || nums.length == 0)
    		return false;
    	int sum = 0;
    	for(int num : nums)
    		sum += num;
    	if(sum % k != 0)
    		return false;
    	sum /= k;
    	boolean []visited = new boolean[nums.length];
    	Arrays.sort(nums);
    	return dfs(nums, sum, nums.length-1, visited, 0, k);
    }
    
    boolean dfs(int []nums, int sum, int index, boolean []visited, int curr, int k) {
    	if(k == 0)
    		return true;
    	if(curr == sum) {
    		if(dfs(nums, sum, nums.length-1, visited, 0, k-1))
    			return true;
    	}
    	
    	for(int i = index;i >= 0;i--) {
    		if(!visited[i] && curr + nums[i] <= sum) {
    			visited[i] = true;
    			if(dfs(nums, sum, i-1, visited, curr+nums[i], k))
    				return true;
    			visited[i] = false;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {4, 3, 2, 3, 5, 2, 1};
		int k = 4;*/
		
		/*int []nums = {1,1,1,1,1,1,1,1,1,1};
		int k = 5;*/
		
		int []nums = {10,10,10,7,7,7,7,7,7,6,6,6};
		int k = 3;
		
		System.out.println(new Solution().canPartitionKSubsets(nums, k));
	}
}
