package main;

class Solution {
	// just check the cases for values lies between 1 and 26
	// https://leetcode.com/problems/decode-ways-ii/discuss/105258/Java-O(N)-by-General-Solution-for-all-DP-problems
	// 0(N) time
	public int numDecodings(String s) {
        /* initial conditions */
        long[] dp = new long[s.length()+1];
        dp[0] = 1;
        if(s.charAt(0) == '0'){
            return 0;
        }
        dp[1] = (s.charAt(0) == '*') ? 9 : 1;

        /* bottom up method */
        for(int i = 2; i <= s.length(); i++){
            char first = s.charAt(i-2);
            char second = s.charAt(i-1);

            // For dp[i-1]
            if(second == '*'){
                dp[i] += 9*dp[i-1];
            }else if(second > '0'){
                dp[i] += dp[i-1];
            }
            
            // For dp[i-2]
            if(first == '*'){
                if(second == '*'){
                    dp[i] += 15*dp[i-2];
                }else if(second <= '6'){
                    dp[i] += 2*dp[i-2];
                }else{
                    dp[i] += dp[i-2];
                }
            }else if(first == '1' || first == '2'){
                if(second == '*'){
                    if(first == '1'){
                       dp[i] += 9*dp[i-2]; 
                    }else{ // first == '2'
                       dp[i] += 6*dp[i-2]; 
                    }
                }else if( ((first-'0')*10 + (second-'0')) <= 26 ){
                    dp[i] += dp[i-2];    
                }
            }

            dp[i] %= 1000000007;
        }
        /* Return */
        return (int)dp[s.length()];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "1*";
		System.out.println(new Solution().numDecodings(s));
	}
}
