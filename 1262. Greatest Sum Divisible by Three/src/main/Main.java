package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
	/* dp[] = maximum with mod {0,1,2}
	 * for(int i = 0;i < 3;i++) 
	 * a+i -> loops through all mods 0,1,2
	 * temp[(i + a)%3] = Math.max(dp[(i + a)%3], a + dp[i])
	 * will put into allocated mod like if A[i]%3 == 0 then i = 1 + a => dp[1]
	 * ifA[i]%3 == 0
	 * temp[0] = max(dp[0], a + dp[0])
	 * temp[1] = max(dp[1], a + dp[2]) remainder are additive
	 * https://leetcode.com/problems/greatest-sum-divisible-by-three/discuss/431077/JavaC%2B%2BPython-One-Pass-O(1)-space
	 */
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int []dp = new int[] {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int a : nums) {
        	int []temp = new int[3];
        	for(int i = 0;i < 3;i++) {
        		temp[(i + a)%3] = Math.max(dp[(i + a)%3], a + dp[i]);
        	}
        	dp = temp;
        }
        return dp[0];
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {3,6,5,1,8};
		System.out.println(new Solution().maxSumDivThree(nums));
	}
}
