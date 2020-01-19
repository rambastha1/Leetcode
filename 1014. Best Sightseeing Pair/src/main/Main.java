package main;

//https://leetcode.com/problems/best-sightseeing-pair/discuss/260850/JavaC%2B%2BPython-One-Pass
// https://leetcode.com/problems/best-sightseeing-pair/discuss/261067/Detailed-Explanation-using-DP-O(n)-Time-or-O(1)-Space
class Solution {
	public int maxScoreSightseeingPair(int[] A) {
		int ans = Integer.MIN_VALUE, n = A.length, endright = A[0] + 0;
		for(int i = 1;i < n;i++) {
			endright = Math.max(endright, A[i-1] + (i-1));
			ans = Math.max(ans, A[i] - i + endright);
		}
		return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {8,1,5,2,6};
		System.out.println(new Solution().maxScoreSightseeingPair(A));
	}
}
