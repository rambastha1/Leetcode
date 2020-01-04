package main;

import java.util.TreeMap;

// https://leetcode.com/articles/odd-even-jump/
class Solution {
	// Time 0(NlgN)
    public int oddEvenJumps(int[] A) {
    	int n = A.length;
    	boolean []even = new boolean[n], odd = new boolean[n];
    	even[n-1] = odd[n-1] = true;
    	// number -> index
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	map.put(A[n-1], n-1);
    	/* from every index calculate whether its possible to reach the end
    	 * if odd[i] is to be set, map.get(A[i]) will tell next location, and jump will become even thus even[map.get(A[i])] is checked
    	 * similarly for even[i]
    	 */
    	int ans = 1;
    	for(int i = n-2;i >= 0;i--) {
    		if(map.containsKey(A[i])) {
    			odd[i] = even[map.get(A[i])];
    			even[i] = odd[map.get(A[i])];
    		} else {
    			Integer l = map.lowerKey(A[i]);
    			Integer r = map.higherKey(A[i]);
    			// l means even jump r means odd jump given in question
    			if(l != null) {
    				even[i] = odd[map.get(l)];
    			} 
    			
    			if(r != null) {
    				odd[i] = even[map.get(r)];
    			}
    		}
    		map.put(A[i], i);
    		if(odd[i])
    			ans++;
    	}
    	
    	// Since each element start fresh, their jump is 1st thus odd
    	/*int ans =  0;
    	for(boolean b : odd)
    		ans += b?1:0;*/
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {10,13,12,14,15};
		System.out.println(new Solution().oddEvenJumps(A));
	}
}
