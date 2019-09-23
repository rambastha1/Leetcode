package main;

import java.util.HashMap;
import java.util.Map;
/* for exactly K  = atmostK - atmost(K-1)
 * will remove all cases of 1,2,3, upto k-1 remaining is exactly K 
 */
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
    	return subarraysWithKDistinctUtil(A, K) - subarraysWithKDistinctUtil(A, K-1);
    }
    
    public int subarraysWithKDistinctUtil(int[] A, int K) {
    	int ans = 0;
    	// number -> count
    	Map<Integer, Integer> map = new HashMap<>();
    	int start = 0, n = A.length;
    	
    	for(int end = 0;end < n;end++) {
    		map.put(A[end], map.getOrDefault(A[end], 0)+1);
    		while(map.size() > K) {
    			map.put(A[start], map.get(A[start])-1);
    			if(map.get(A[start]) == 0)
    				map.remove(A[start]);
    			start++;
    		}
    		ans += end - start;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,2,1,3,4}; 
		int K = 3;
		System.out.println(new Solution().subarraysWithKDistinct(A, K));
	}
}
