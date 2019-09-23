package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/discuss/303897/Java-easy-solution-%2B-explanation

class Solution {
	
	//Time 0(N^2)
    public int maxEqualRowsAfterFlips(int[][] matrix) {
    	Map<String, Integer> map = new HashMap<>();
    	for(int []row : matrix) {
    		StringBuilder sb1 = new StringBuilder();
    		StringBuilder sb2 = new StringBuilder();
    		
    		for(int i : row) {
    			sb1.append(i);
    			sb2.append(1-i);
    		}
    		
    		String str1 = sb1.toString();
    		String str2 = sb2.toString();
    		
    		map.put(str1, map.getOrDefault(str1, 0) + 1);
    		map.put(str2, map.getOrDefault(str2, 0) + 1);
    	}
    	
    	int ans = 0;
    	for(int value : map.values())
    		ans = Math.max(ans, value);
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{1,0,0,1,0}, 
						  {1,0,0,1,0}, 
						  {1,0,1,1,1}, 
						  {0,1,1,0,1}, 
						  {1,0,0,1,1}};
		System.out.println(new Solution().maxEqualRowsAfterFlips(matrix));
	}
}