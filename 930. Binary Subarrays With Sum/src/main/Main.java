package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
    	if(A == null || A.length == 0)
    		return 0;
    	if(A.length == 1)
    		return A[0]==S?1:0;
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	int sum = 0, ans = 0;
    	for(int i = 0;i < A.length;i++) {
    		sum += A[i];
    		if(sum == S)
    			ans++;
    		if(map.containsKey(sum -S))
    			ans += map.get(sum-S).size();
    		if(!map.containsKey(sum))
    			map.put(sum, new ArrayList<>());
    		map.get(sum).add(i);
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,0,1,0,1};
		int S = 2;
		System.out.println(new Solution().numSubarraysWithSum(A, S));
	}
}
