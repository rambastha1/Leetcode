package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    	if(A.length == 0 || B.length == 0 || C.length == 0 || D.length == 0)
    		return 0;
    	int n = A.length;
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < n;j++) {
    			map.put(A[i]+B[j], map.getOrDefault(A[i]+B[j], 0) + 1);
    		}    		
    	}
    	int ans = 0;
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < n;j++) {
    			if(map.containsKey(-(C[i] + D[j])))
    				ans+= map.get(-(C[i]+D[j]));
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,2}, B = {-2,-1}, C = {-1,2}, D = {0,2};
		System.out.println(new Solution().fourSumCount(A, B, C, D));
	}
}