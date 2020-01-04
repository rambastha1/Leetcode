package main;

import java.util.Arrays;

/* https://leetcode.com/problems/unique-letter-string/discuss/128952/One-pass-O(N)-Straight-Forward
 * Time 0(n)
 * Think about string "XAXAXXAX" and focus on making the second "A" a unique character.
We can take "XA(XAXX)AX" and between "()" is our substring.
We can see here, to make the second "A" counted as a uniq character, we need to:

insert "(" somewhere between the first and second A
insert ")" somewhere between the second and third A
For step 1 we have "A(XA" and "AX(A", 2 possibility.
For step 2 we have "A)XXA", "AX)XA" and "AXX)A", 3 possibilities.

number of possibilities  = number of char between them
 */
class Solution {
    public int uniqueLetterString(String S) {
    	int n = S.length();
    	if(n == 0)
    		return 0;
    	
    	// store last two seen indices
    	int [][]dp = new int[26][2];
    	for(int i = 0;i < 26;i++)
    		Arrays.fill(dp[i], -1);
    	
    	long res = 0, mod = (int)1e9+7;
    	for(int i = 0;i < n;i++) {
    		int index = S.charAt(i) - 'A';
    		res = (res + ((i-dp[index][1])*(dp[index][1] - dp[index][0]))%mod)%mod;
    		dp[index] = new int[] {dp[index][1], i};
    	}
    	
    	for(int i = 0;i < 26;i++) {
    		res = (res + ((n-dp[i][1])*(dp[i][1] - dp[i][0]))%mod)%mod;
    	}
    	return (int)(res%mod);
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "ABC";
		System.out.println(new Solution().uniqueLetterString(S));
	}
}
