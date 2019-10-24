package main;

// https://leetcode.com/discuss/interview-question/374440/Twitter-or-OA-2019-or-Weird-Faculty
class Solution {
	public int exam(int []A) {
		int sum = 0, n = A.length;
		for(int i = 0;i < n;i++)
			sum += A[i]==1?1:-1;
		
		if(sum < 0)
			return 0;
		int curr = 0;
		for(int i = 0;i < n;i++) {
			if(curr > sum)
				return i;
			curr += A[i]==1?1:-1;
			sum -= A[i]==1?1:-1;
		}
		return A.length;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1, 0, 0, 1, 0};
		//int []A = {1, 0, 0, 1, 1};
		//int []A = {1, 1, 1, 0, 1};
		System.out.println(new Solution().exam(A));
	}
}