package main;
import java.util.Arrays;

// Similar to all dp problems

/* since its dp find out which indices could lead to present index
 * a = e + i + u
e = a + i
i = e + o
o = i
u = i + o

 */
class Solution {
    public int countVowelPermutation(int n) {
    	if(n == 1)
    		return 5;
    	int mod = (int)1e9+7;
    	long []dp = {1,1,1,1,1};
    	
    	for(int i = 2;i <= n;i++) {
    		long []next = new long[5];
    		next[0] = ((dp[1] + dp[2])%mod + dp[4])%mod;
    		next[1] = (dp[0] + dp[2])%mod;
    		next[2] = (dp[1] + dp[3])%mod;
    		next[3] = dp[2];
    		next[4] = (dp[2] + dp[3])%mod;
    		dp = next;
    	}
    	long sum = 0;
    	for(long d : dp)
    		sum = (sum + d)%mod;
    	return (int)sum;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 50;
		System.out.println(new Solution().countVowelPermutation(n));
	}
}
