package main;

/* https://www.geeksforgeeks.org/sum-of-subsets-of-all-the-subsets-of-an-array-o3n/
 * https://www.geeksforgeeks.org/sum-of-subsets-of-all-the-subsets-of-an-array-o2n/
 * https://www.geeksforgeeks.org/sum-of-subsets-of-all-the-subsets-of-an-array-on/
 */
//??
class Solution {
	
	int []fact = new int[10];
	private int ncr(int n, int r) {
		return fact[n]/(fact[r]*fact[n-r]);
	}
	
	public int subsetsum(int []A) {
		fact[0] = 1;
		int n = A.length;
		
		for(int i = 1;i < n;i++) {
			fact[i] = i*fact[i-1];
		}
		
		int mul = 0;
		for(int i = 0;i < n;i++) {
			mul += (int)Math.pow(2, i) * ncr(n-1, i);
		}
		
		int ans = 0;
		for(int i = 0;i < n;i++)
			ans += A[i]*mul;
		
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,1};
		System.out.println(new Solution().subsetsum(A));
	}
}
