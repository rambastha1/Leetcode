package main;

// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/356057/Java-O(d-*-f-*-target)-dp-straightforward-and-fast
// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/355940/C%2B%2B-Coin-Change-2
class Solution {
	public int numRollsToTarget(int d, int f, int target) {
    	int []dp = new int[target+1];
    	dp[0] = 1;
    	for(int i = 1;i <= d;i++) {
    		int []temp = new int[target+1];
    		for(int j = 1;j <= target;j++) {
    			if(i*f < j)
    				continue;
				for(int k = 1;k <= f && k <= j;k++)
					temp[j] = (temp[j] + dp[j-k])%1000000007;
    		}
    		dp = temp;
    	}
    	return dp[target];
    }
}

public class Main {
	public static void main(String[] args) {
		//int d = 1, f = 6, target = 3;
		int d = 2, f = 6, target = 7;
		System.out.println(new Solution().numRollsToTarget(d, f, target));
	}
}