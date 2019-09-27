package main;

// https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/discuss/376950/Golang%3A-sliding-window-for-all-typical-atmostkdistinct-problems.

class Solution {
	// Time 0(N)
    public int countLetters(String S) {
    	int start = 0, end = 0, ans = 0;
    	while(start <= end && end < S.length()) {
    		if(S.charAt(start) != S.charAt(end)) {
    			start = end;
    			continue;
    		}
    		ans += end-start+1;
    		end++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "aaaba";
		System.out.println(new Solution().countLetters(S));
	}
}