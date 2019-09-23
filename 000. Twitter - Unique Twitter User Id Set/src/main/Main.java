package main;

import java.util.Arrays;

// https://leetcode.com/discuss/interview-question/376581/Twitter-or-OA-2019-or-Unique-Twitter-User-Id-Set

class Solution {
	public int minsum(int []A) {
		Arrays.sort(A);
		int ans = 0, low = 0;
		for(int a : A) {
			ans += (low = Math.max(low+1, a));
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		//int []A = {3,2,1,2,7};
		int []A = {5,6,9,8,7};
		System.out.println(new Solution().minsum(A));
	}
}
