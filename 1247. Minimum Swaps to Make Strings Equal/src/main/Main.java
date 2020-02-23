package main;
// https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/discuss/419351/Java-Solution-with-detailed-comments
class Solution {
    public int minimumSwap(String s1, String s2) {
    	int n = s1.length();
    	int x1 = 0; // number of 'x' in s1 (skip equal chars at same index)
		int y1 = 0; // number of 'y' in s1 (skip equal chars at same index)
		int x2 = 0; // number of 'x' in s2 (skip equal chars at same index)
		int y2 = 0; // number of 'y' in s2 (skip equal chars at same index)
		
    	for(int i = 0;i < n;i++) {
    		char c1 = s1.charAt(i), c2 = s2.charAt(i);
    		// skip chars that are equal at the same index in s1 and s2
    		if(c1 == c2)
    			continue;
    		if(c1 == 'x')
    			x1++;
    		else
    			y1++;
    		
    		if(c2 == 'x')
    			x2++;
    		else
    			y2++;
    	}
    	// After skip "c1 == c2", check the number of  'x' and 'y' left in s1 and s2.
    	// if number of 'x' or 'y' is odd, we can not make s1 equals to s2
    	if((x1+x2)%2 != 0 || (y1+y2)%2 != 0)
    		return -1;
    	// Cases to do 1 swap:
        // "xx" => x1 / 2 => how many pairs of 'x' we have ?
        // "yy" => y1 / 2 => how many pairs of 'y' we have ?
        // 
        // Cases to do 2 swaps:
        // "xy" or "yx" =>  x1 % 2
    	int ans = x1/2 + y1/2 + (x1%2)*2;
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx";
		System.out.println(new Solution().minimumSwap(s1, s2));
	}
}
