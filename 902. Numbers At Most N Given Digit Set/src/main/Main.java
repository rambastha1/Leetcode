package main;
/* https://leetcode.com/problems/numbers-at-most-n-given-digit-set/discuss/168439/C%2B%2B-O(logN)-Clear-code-with-explanation
 */
class Solution {
    public int atMostNGivenDigitSet(String[] D, int N) {
    	String str = String.valueOf(N);
    	int n = str.length(), ans = 0;
    	for(int i = 1;i < n;i++) {
    		ans += (int)Math.pow(D.length, i);
    	}
    	
    	for(int i = 0;i < n;i++) {
    		boolean samedigit = false;
    		for(String s : D) {
    			if(s.charAt(0) < str.charAt(i))
    				ans += (int)Math.pow(D.length, n-i-1);
    			else if(s.charAt(0) == str.charAt(i))
    				samedigit = true;
    		}
    		if(!samedigit)
    			return ans;
    	}
    	return ans + 1;
    }
}

public class Main {
	public static void main(String[] args) {
		String []D = {"1","3","5","7"};
		int N = 100;
		System.out.println(new Solution().atMostNGivenDigitSet(D, N));
	}
}
