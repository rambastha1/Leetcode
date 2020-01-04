package main;

import java.util.Arrays;

/* https://leetcode.com/problems/orderly-queue/discuss/165907/C%2B%2B-Solution-SortRotate
 * When k > 1 it becomes bubble sort
 */

class Solution {
	// Time 0(NlgN)
    public String orderlyQueue(String S, int K) {
    	if(K > 1) {
    		char []ch = S.toCharArray();
    		Arrays.sort(ch);
    		return new String(ch);
    	}
    	String ans = S;
    	// If concatenation step is 0(N) then time = 0(N^2)
    	for(int i = 1;i < S.length();i++) {
    		String temp = S.substring(i) + S.substring(0, i);
    		if(temp.compareTo(ans) < 0)
    			ans = temp;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "cba";
		int K = 1;
		System.out.println(new Solution().orderlyQueue(S, K));
	}
}
