package main;

// https://leetcode.com/discuss/interview-question/363036/Twitter-or-OA-2019-or-Activate-Fountain

class Solution {
	public int minTaps(int n, int[] ranges) {
		int []arr = new int[n+1];
		// create an array where a[i] represents maximum right index it can cover
		for(int i = 0;i < n+1;i++) {
			int left = Math.max(0, i - ranges[i]);
			int right = Math.min(n, i + ranges[i]);
			arr[left] = Math.max(arr[left], right);
		}
		
		int ans = 1, right = arr[0], next = 0;
		
		for(int i = 0;i < n+1;i++) {
			next = Math.max(next, arr[i]);
			if(i == right) {
                if(right == n)
                    break;
				ans++;
				right = next;
			}
		}
		return right != n?-1:ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		int []ranges = {0,0,0,0};
		System.out.println(new Solution().minTaps(n, ranges));
	}
}
