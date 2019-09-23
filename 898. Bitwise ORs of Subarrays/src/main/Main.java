package main;

import java.util.HashSet;
import java.util.Set;

class Solution {
	// Time 0(32N)
    public int subarrayBitwiseORs(int[] A) {
    	Set<Integer> ans = new HashSet<>(), curr = new HashSet<>();
    	for(int a : A) {
    		Set<Integer> next = new HashSet<>();
    		next.add(a);
    		/* size pf curr is at max 32 as 32 bits in integer
    		 */
    		for(int c : curr) {
    			next.add(a|c);
    		}
    		ans.addAll(next);
    		curr = next;
    	}
    	return ans.size();
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,2,4};
		System.out.println(new Solution().subarrayBitwiseORs(A));
	}
}
