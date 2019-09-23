package main;

// https://leetcode.com/discuss/interview-question/363036/Twitter-or-OA-2019-or-Activate-Fountain

class Solution {
	public int numFountains(int []A) {
		int n = A.length;
		int []arr = new int[n];
		// create an array where a[i] represents maximum right index it can cover
		for(int i = 0;i < n;i++) {
			int left = Math.max(0, i - A[i]);
			int right = Math.min(n, i + A[i] + 1);
			arr[left] = Math.max(arr[left], right);
		}
		
		int ans = 1, right = arr[0], next = 0;
		
		for(int i = 0;i < n;i++) {
			next = Math.max(next, arr[i]);
			if(i == right) {
				ans++;
				right = next;
			}
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		//int []A = {0,0,0,3,0,0,2,0,0};
		int []A = {3,0,2,0,1,0};
		System.out.println(new Solution().numFountains(A));
	}
}
