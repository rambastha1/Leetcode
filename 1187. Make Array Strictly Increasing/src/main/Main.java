package main;
import java.util.Arrays;
import java.util.TreeSet;

class Solution {
	// time 0(N^2 lgN) Space 0(N^2)
	/* dp[i][j] is minimum value by making i swaps from 0 to j index of arr1
	 * whenever arr1[i] > arr1[i+1], we want minimum that is greater than arr1[i] so that rest of array follow increasing order 
	 * this is higher function is used, could have used treemap higherkey
	 * exception to this is when arr1[0] needs to change
	 * 
	 * when j reaches length and there is a minimum value to dp[i][j], it means increase array is achieved, we need to return minimum
	 * i i.e minimum swaps thus return when first reach
	 * duplicates are avoided as higher gives strictly higher value and we are storing minimum value  
	 * https://leetcode.com/problems/make-array-strictly-increasing/discuss/377680/Simple-Java-DP-Solution-%2B-TreeSet-with-Explanation-beats-100
	 */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
    	// number -> index of arr2
    	TreeSet<Integer> set = new TreeSet<>();
    	for(int i : arr2)
    		set.add(i);
    	int n = arr1.length;
    	int [][]dp = new int[n+1][n+1];
    	for(int i = 0;i <= n;i++)
    		Arrays.fill(dp[i], Integer.MAX_VALUE);
    	dp[0][0] = Integer.MIN_VALUE;
    	for(int j = 1;j <= n;j++) {
    		for(int i = 0;i <= j;i++) {
    			// when this element is higher than previous no need to do anything
    			if(arr1[j-1] > dp[i][j-1])
    				dp[i][j] = arr1[j-1];
				// dp[i-1][j-1] is chosen because it meets requirement
    			// dp[i][j] doesn't
    			Integer high = (i > 0 && dp[i-1][j-1] != Integer.MAX_VALUE)?set.higher(dp[i-1][j-1]):null;
				if(high != null)
					dp[i][j] = Math.min(dp[i][j], set.higher(dp[i-1][j-1]));
    			
				if(j == n && dp[i][j] != Integer.MAX_VALUE)
    				return i;
    		}
    	}
    	return -1;
    }
}
public class Main {
	public static void main(String[] args) {
		int []arr1 = {5,16,19,2,1,12,7,14,5,16}, arr2 = {6,17,4,3,6,13,4,3,18,17,16,7,14,1,16};
		System.out.println(new Solution().makeArrayIncreasing(arr1, arr2));
	}
}
