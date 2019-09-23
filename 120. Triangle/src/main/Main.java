package main;

import java.util.Arrays;
import java.util.List;

class Solution {
	// If not list can be done in 0(1) space paint house problem
	// Time 0(N) Size 0(N)
    public int minimumTotal(List<List<Integer>> triangle) {
    	if(triangle == null || triangle.size() == 0)
    		return 0;
    	int n = triangle.size();
    	int []dp = new int[triangle.get(n-1).size()];
    	for(int i = 0;i < n;i++)
    		dp[i] = triangle.get(n-1).get(i);
    	
    	for(int i = triangle.size()-2;i >= 0;i--) {
    		for(int j = 0;j < triangle.get(i).size();j++) {
    			dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
    		}
    	}
    	return dp[0];
    }
}

public class Main {
	public static void main(String[] args) {
		/*List<Integer> l1 = Arrays.asList(2);
		List<Integer> l2 = Arrays.asList(3,4);
		List<Integer> l3 = Arrays.asList(6,5,7);
		List<Integer> l4 = Arrays.asList(4,1,8,3);
		List<List<Integer>> triangle = Arrays.asList(l1,l2,l3,l4);*/
		
		List<Integer> l1 = Arrays.asList(2);
		List<Integer> l2 = Arrays.asList(3,4);
		List<Integer> l3 = Arrays.asList(6,5,7);
		List<Integer> l4 = Arrays.asList(4,1,8,3);
		List<List<Integer>> triangle = Arrays.asList(l1,l2,l3,l4);
		System.out.println(new Solution().minimumTotal(triangle));
	}
}
