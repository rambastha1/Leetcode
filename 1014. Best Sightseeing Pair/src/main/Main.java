package main;

//https://leetcode.com/problems/best-sightseeing-pair/discuss/260850/JavaC%2B%2BPython-One-Pass

class Solution {
	public int maxScoreSightseeingPair(int[] A) {
		int ans = 0, curr = 0;
		for(int a : A) {
			// i + A[i]
			ans = Math.max(ans, curr + a);
			// -1 because we move forward (-distance part)
			curr = Math.max(curr, a) - 1;
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
