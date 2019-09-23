package main;

// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
// https://leetcode.com/problems/last-stone-weight-ii/discuss/295325/Why-DP-is-applicable-here

/* why it is difference between two subsets?
 * If you subtract them in the following order: b-c, then d-b-c. Then it is the same as doing d-(b+c).
 * Then doing [d-(b+c)]-a is the same as -a+d-(b+c), which is d-a-(b+c), which is d-[a+(b+c)], 
 * which is d-(a+b+c).
 * 
 * the ordering of subtraction can lead to multiple answers. from above it can be seen as dividing into
 * two subsets and taking difference. For smallest number, the difference between two subsets should be minimum
 * and it can be happen when each sum is closest to half of total sum  
 */

class Solution {
	// Time 0(sum*N) Spcae 0(sum)
	public int lastStoneWeightII(int[] stones) {
		if(stones == null || stones.length == 0)
    		return 0;
	    int sum = 0;
	    for(int stone : stones)
	    	sum += stone;
	    boolean []dp = new boolean[sum+1];
	    dp[0] = true;
	    
	    for(int stone : stones) {
	    	for(int j = sum;j >= stone;j--) {
	    		dp[j] |= dp[j-stone];
	    	}
	    }
	    
	    int diff = Integer.MAX_VALUE;
	    for(int j = sum/2;j >= 0;j--) {
	    	if(dp[j]) {
	    		diff = sum - 2*j;
	    		break;
	    	}
	    }
		return diff;
	}
	
	// MCM won't work
    /*public int lastStoneWeightII(int[] stones) {
    	if(stones == null || stones.length == 0)
    		return 0;
    	int n = stones.length;
    	int [][]dp = new int[n][n];
    	for(int gap = 0;gap < n;gap++) {
    		for(int i = 0,j = gap;j < n;i++,j++) {
    			if(j < i+1)
    				dp[i][i] = 0;
    			else if(gap == 1) {
    				dp[i][j] = (int)Math.abs(stones[i]-stones[j]);
    			} else {
    				dp[i][j] = Integer.MAX_VALUE;
    				int a = Math.abs(stones[j] - dp[i][j-1]);
					int b = Math.abs(stones[i] - dp[i+1][j]);
					dp[i][j] = Math.min(dp[i][j], Math.min(a, b));
    			}
    		}
    	}
    	return dp[0][n-1];
    }*/
}

public class Main {
	public static void main(String[] args) {
		//int []stones = {2,7,4,1,8,1};
		int []stones = {3,2,1,7,3,7};
		System.out.println(new Solution().lastStoneWeightII(stones));
	}
}