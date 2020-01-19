package main;
/* Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
Example 4:

Input: s = "g"
Output: 0
Example 5:

Input: s = "no"
Output: 1
 

Constraints:

1 <= s.length <= 500
All characters of s are lower case English letters.
 * 
 */
// https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        if(n == 1)
            return 0;
        int [][]dp = new int[n][n];
        for (int gap = 1; gap < n; ++gap) {
            for (int i = 0, j = gap; j < n; ++i, ++j) {
            	// s[i,j]if end characters s[i]==s[j] are same, check inner s[i+1,j-1]
                if((s.charAt(i) == s.charAt(j))) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                	// minimum of s[i+1][j] and s[i, j-1]
                    dp[i][j] = 1 + Math.min(dp[i][j-1],  dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "mbadm";
		System.out.println(new Solution().minInsertions(s));
	}
}
