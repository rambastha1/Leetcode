package main;
// https://leetcode.com/problems/string-to-integer-atoi/discuss/4654/My-simple-solution
class Solution {
    public int myAtoi(String str) {
    	int sign = 1, res = 0, index = 0;
    	while(index < str.length() && str.charAt(index) == ' ')
    		index++;
    	
    	if(index >= str.length())
    		return 0;
    	
    	if(str.charAt(index) == '+' || str.charAt(index) == '-') {
    		if(str.charAt(index++) == '-')
    			sign = -1;
    	}
    		
    	while(index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
    		// last digit of int_max is 7 base checks length as well
    		if(res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && str.charAt(index) > '7')
    			return sign==-1?Integer.MIN_VALUE:Integer.MAX_VALUE;
    		
    		res = res*10 + str.charAt(index)- '0';
    		index++;
    	}
    	return sign==-1?-res:res;
    }
}

public class Main {
	public static void main(String[] args) {
		//String str = "4193 with words";
		//String str = "42";
		//String str = "words and 987";
		String str = "-91283472332";
		System.out.println(new Solution().myAtoi(str));
	}
}
