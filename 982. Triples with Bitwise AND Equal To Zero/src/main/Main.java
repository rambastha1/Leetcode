package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	// Time 0(N^2) Space 
    public int countTriplets(int[] A) {
    	Map<Integer, Integer> map = new HashMap<>();
    	int n = A.length;
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < n;j++) {
    			int num = A[i]&A[j];
    			map.put(num, map.getOrDefault(num, 0) + 1);
    		}
    	}
    	int ans = 0;
    	for(int i = 0;i < n;i++) {
    		for(int key : map.keySet()) {
    			if((A[i]&key) == 0) {
    				ans+= map.get(key);
    			}
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,1,3};
		System.out.println(new Solution().countTriplets(A));
	}
}
