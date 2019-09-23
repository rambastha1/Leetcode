package main;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/

class Solution {
	
	// Time 0(N) Space 0(N)
	public int count(int []A, int k) {
		if(A == null || A.length == 0)
			return 0;
		int n = A.length;
		// sum -> index
		Map<Integer, Integer> map = new HashMap<>();
		int currsum = 0, maxlen = 0;
		for(int i = 0;i < n;i++) {
			currsum += A[i];
			if(currsum == k)
				maxlen = Math.max(maxlen, i+1);
			if(map.containsKey(currsum-k))
				maxlen = Math.max(maxlen, i - map.get(currsum-k));
		}
		return maxlen;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {2, 3, 5, 7, 10, 15};
		int k = 10;
		System.out.println(new Solution().count(A, k));
	}
}
