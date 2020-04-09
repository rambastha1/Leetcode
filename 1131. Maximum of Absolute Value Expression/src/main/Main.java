package main;
// https://leetcode.com/problems/maximum-of-absolute-value-expression/discuss/340075/c%2B%2B-beats-100-(both-time-and-memory)-with-algorithm-and-image
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
    	int max1 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE;
    	int max2 = Integer.MIN_VALUE, min2 = Integer.MAX_VALUE;
    	int max3 = Integer.MIN_VALUE, min3 = Integer.MAX_VALUE;
    	int max4 = Integer.MIN_VALUE, min4 = Integer.MAX_VALUE;
    	
    	int n = arr1.length, ans = Integer.MIN_VALUE;
    	for(int i = 0;i < n;i++) {
    		// case 1 scenario arr1[i] + arr2[i] + i
    		max1 = Math.max(max1, arr1[i] + arr2[i] + i);
    		min1 = Math.min(min1, arr1[i] + arr2[i] + i);
    		
    		// case 2 scenario arr1[i] + arr2[i] - i
    		max2 = Math.max(max2, arr1[i] + arr2[i] - i);
    		min2 = Math.min(min2, arr1[i] + arr2[i] - i);
    		
    		// case 3 scenario arr1[i] - arr2[i] - i
    		max3 = Math.max(max3, arr1[i] - arr2[i] - i);
    		min3 = Math.min(min3, arr1[i] - arr2[i] - i);
    		
    		// case 4 scenario arr1[i] - arr2[i] + i
    		max4 = Math.max(max4, arr1[i] - arr2[i] + i);
    		min4 = Math.min(min4, arr1[i] - arr2[i] + i);
    		
    		int diff1 = max1 - min1;
    		int diff2 = max2 - min2;
    		int diff3 = max3 - min3;
    		int diff4 = max4 - min4;
    		ans = Math.max(ans, Math.max(diff1, Math.max(diff2, Math.max(diff3, diff4))));
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr1 = {1,-2,-5,0,10}, arr2 = {0,-2,-1,-7,-4};
		System.out.println(new Solution().maxAbsValExpr(arr1, arr2));
	}
}
