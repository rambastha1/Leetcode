package main;
import java.util.Arrays;

class Solution {
	/* dp[i] denotes - whether sum can be divided into 4 subsets of equal sum
	 * total[i] denotes total sum using that nums[i] less than target sum
	 * Now loop over entire power set of nums, set the jth bit using
	 * newset = i | (1<<j)
	 * and check whether this sum is less or equal to target sum.
	 * If yes, mark this index true and set its total.
	 * The last index of D tells whether whole array can be divided into 4 subsets of equal sum.
	 */
    public boolean makesquare(int[] nums) {
    	if(nums == null || nums.length < 4)
    		return false;
    	int n = nums.length;
    	boolean []dp = new boolean[1<<n];
    	dp[0] = true;
    	int []total = new int[1<<n];
    	int sum = 0;
    	for(int num : nums)
    		sum += num;
    	Arrays.sort(nums);
    	if(sum%4 != 0)
    		return false;
    	sum /= 4;
    	if(nums[n-1] > sum)
    		return false;
    	// Loop over power set
    	for(int i = 0;i < 1<<n;i++) {
    		if(dp[i]) {
    			for(int j = 0;j < n;j++) {
    				// Loop over each element to find subset
    				int temp = i | (1<<j);
    				if(temp != i) {
    					// if total sum is less than target store in dp and total array
    					if(nums[j] <= (sum - (total[i]%sum))) {
    						dp[temp] = true;
    						total[temp] = nums[j] + total[i];
    					} else 
    						break;
    				}
    			}
    		}
    	}
    	return dp[(1<<n) -1]; 
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,1,2,2,2};
		int []nums = {3,3,3,3,4};
		System.out.println(new Solution().makesquare(nums));
	}
}
