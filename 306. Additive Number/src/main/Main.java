package main;

/* https://leetcode.com/problems/additive-number/discuss/75567/Java-Recursive-and-Iterative-Solutions
 * The first number is defined as num.substring(0, i)
 * Note that the first number, when added to a second number of any
 * length, must produce a sum that is at least the length of the first number.
 * 
 * Accordingly, the first number cannot be more than half the length
 * of the given String num.
 * 
 * The second number is defined as num.substring(i, i + j)
 * Note that the second number, when added to a first number of any
 * length, must produce a sum whose length is at least the maximum of either i or j
 * 
 * Accordingly, if n is the length of the given num, and i of those
 * digits belong to the first number, and j of those digits belong
 * to the second number, then (n - i - j) digits are left for the sum.
 * 
 * Since the length of the sum is at least max(i, j), we can stop iterating when max(i, j) exceeds n - i - j
 * 
 */

class Solution {
    public boolean isAdditiveNumber(String num) {
    	int n = num.length();
    	for(int i = 1;i <= n/2;i++) {
    		for(int j = 1;Math.max(i, j) <= n-i-j;j++) {
    			if(isvalid(num, i, j))
    				return true;
    		}
    	}
    	return false;
    }
    
    private boolean isvalid(String num, int i, int j) {
    	if((num.charAt(0) == '0' && i > 1) || (num.charAt(i) == '0' && j > 1))
    		return false;
    	String sum = "";
    	Long s1 = Long.parseLong(num.substring(0, i));
    	Long s2 = Long.parseLong(num.substring(i, i+j));
    	for(int k = i+j;k < num.length();k+= sum.length()) {
    		Long temp = s2;
    		s2 = s1 + s2;
    		s1 = temp;
    		sum = String.valueOf(s2);
    		if(!num.startsWith(sum, k))
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		//String num = "112358";
		String num = "199100199";
		System.out.println(new Solution().isAdditiveNumber(num));
	}
}
