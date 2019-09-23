package main;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/discuss/interview-question/375262/Twitter-or-OA-2019-or-Partitioning-array
	
class Solution {
	public String solve(int []A, int k) {
		int n = A.length;
		if(n%k!= 0)
			return "No";
		// number -> count
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : A) {
			map.put(a, map.getOrDefault(a, 0) + 1);
			if(map.get(a) > (int)(n/k))
				return "No";
		}
		return "Yes";
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,2,3,4};
		int k = 4;
		System.out.println(new Solution().solve(A, k));
	}
}
