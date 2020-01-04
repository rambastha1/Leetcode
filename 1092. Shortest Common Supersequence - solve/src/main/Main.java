package main;

// https://leetcode.com/problems/shortest-common-supersequence/discuss/358990/O(mn)-Java-solution-Iterative-Dynamic-Programming
// https://leetcode.com/problems/shortest-common-supersequence/discuss/312702/Java-DP-Solution(Similiar-to-LCS)

/* Any way find LCS and then move backward
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/319439/Java-DP-bottom-up-(2D-matrix)
 * 
 * DP Recurrence:

Let str1[0..m - 1] and str2[0..n - 1] be two strings with lengths m and n .

if (m == 0) return n;
if (n == 0) return m;

// If last characters are same, then add 1 to result and recur for str1[]
if (str1.charAt(m - 1) == str2.charAt(n - 1))
return 1 + shortestCommonSupersequence(str1, str2, m - 1, n - 1);

// Else find shortest of following two
// a) Remove last character from str1 and recur
// b) Remove last character from str2 and recur
else return 1 + min( shortestCommonSupersequence(str1, str2, m - 1, n), shortestCommonSupersequence(str1, str2, m, n - 1) );
 */

class Solution {
	// Time and Space 0(m*n)
	public String shortestCommonSupersequence(String str1, String str2) {
        // find length of Shortest Common SuperSequence (SCS) for each i, j
        int m = str1.length();
        int n = str2.length();
        int[][] scs = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    scs[i][j] = j;
                } else if (j == 0) {
                    scs[i][j] = i;
                 // X, Y are 1-indexed in our definition, 0-indexed in code
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) { 
                    scs[i][j] = 1 + scs[i - 1][j - 1];
                } else {
                    scs[i][j] = 1 + Math.min(scs[i - 1][j], scs[i][j - 1]);
                }
            }
        }

        // reconstruct the actual String representing SCS
        StringBuffer sb = new StringBuffer();
        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.insert(0, str1.charAt(i - 1));
                i--;
                j--;
            }
            // Tricky. The smaller scs determines which character to insert.
            else if (scs[i - 1][j] < scs[i][j - 1]) {
                sb.insert(0, str1.charAt(i - 1));
                i--;
            } else {
                sb.insert(0, str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            sb.insert(0, str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.insert(0, str2.charAt(j - 1));
            j--;
        }

        return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		//String str1 = "bbbaaaba", str2 = "bbababbb";
		String str1 = "abac", str2 = "cab";
		System.out.println(new Solution().shortestCommonSupersequence(str1, str2));
	}
}