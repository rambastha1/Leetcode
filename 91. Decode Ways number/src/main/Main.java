package main;
// need to check last two values
class Solution {
	public int numDecodings(String s) {
    	if(s == null || s.length() == 0)
    		return 0;
        if(s.length() == 1 && s.charAt(0)!='0')
            return 1;
    	int n = s.length();
    	int []dp = new int[n+1];
    	dp[0] = 1;
    	dp[1] = s.charAt(0)!='0'?1:0;
    	
    	for(int i = 2;i <= n;i++) {
    		String s1 = s.substring(i-1, i);
    		String s2 = s.substring(i-2, i);
    		int num1 = Integer.parseInt(s1);
    		int num2 = Integer.parseInt(s2);
    		if(num1 >= 1 && num1 <= 9)
    			dp[i] += dp[i-1];
    		if(num2 >= 10 && num2 <= 26)
    			dp[i] += dp[i-2];
    	}
    	return dp[n];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "226";
		System.out.println(new Solution().numDecodings(s));
	}
}
