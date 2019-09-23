package main;

/* https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 * similar to rod cutting 
 * reframe - sum can be cut into subsets
 * It is NP-Complete as k can be represented as 2^m digits make it exponential time algorithm
 */ 
// Won't handle -ve numbers
class Solution {
	
	//Time 0(N*K) Space 0(K)
	public boolean subsetsum(int []A, int k) {
		if(A == null || A.length == 0)
			return false;
		int n = A.length;
		boolean []dp = new boolean[k+1];
		dp[0] = true;
		for(int i = 0;i < n;i++) {
			/* Traversing backward will take care of case when j - A[i] < 0
			 * Moreover, there is no need to update when A[i] < j
			 */
			for(int j = k;j >= A[i];j--) {
				dp[j] = dp[j] || dp[j - A[i]];
			}
		}	
		return dp[n];
	}
	
	//Time 0(N*K) Space 0(K) Traverse forward more cumbersome code as need to 
	// take care of cases
	public boolean subsetsum1(int []A, int k) {
		if(A == null || A.length == 0)
			return false;
		int n = A.length;
		boolean []dp = new boolean[k+1];
		dp[0] = false;
		for(int i = 1;i <= n;i++) {
			for(int j = 1;j <= k;j++) {
				dp[j] = dp[j-1];
				if(j - A[i-1] == 0)
					dp[j] = true;
				if((j - A[i-1]) > 0)
					dp[j] = dp[j-1] || dp[j - A[i-1]];
			}
		}	
		return dp[n];
	}
	
	//Time 0(N*K) Space 0(N*K)
	public boolean subsetsum2(int []A, int k) {
		if(A == null || A.length == 0)
			return false;
		int n = A.length;
		boolean [][]dp = new boolean[A.length+1][k+1];
		for(int i = 0;i <= A.length;i++)
			dp[i][0] = true;
		
		for(int i = 1;i <= n;i++) {
			for(int j = 1;j <= k;j++) {
				dp[i][j] = dp[i-1][j];
				if(j - A[i-1] >= 0)
					dp[i][j] = dp[i][j] || dp[i-1][j - A[i-1]];
			}
		}
		return dp[n][k];
	}
}

public class Main {
	public static void main(String[] args) {
		/*int []A = {3, 34, 4, 12, 5, 2};
		int k = 9;*/
		
		int []A = {1, 8, 2, 5};
		int k = 4;
		System.out.println(new Solution().subsetsum(A, k));
	}
}