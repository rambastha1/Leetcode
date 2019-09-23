package main;
// https://www.geeksforgeeks.org/find-the-maximum-sum-of-plus-shape-pattern-in-a-2-d-array/
class Solution {
	public int maxplus(int [][]nums) {
		if(nums == null || nums.length == 0)
			return 0;
		int m = nums.length, n = nums[0].length;
		int [][]top = new int[m][n], down = new int[m][n], left = new int[m][n], right = new int[m][n];
		for(int i = 0;i < m;i++) {
			for(int j = 0;j < n;j++) {
				top[i][j] = Math.max(0, (i==0)?0:top[i-1][j])+ nums[i][j];
				left[i][j] = Math.max(0, (j==0)?0:left[i][j-1])+ nums[i][j];
			}
		}
		
		for(int i = m-1;i >= 0;i--) {
			for(int j = n-1;j >= 0;j--) {
				down[i][j] = Math.max(0, (i+1==m)?0:down[i+1][j])+ nums[i][j];
				right[i][j] = Math.max(0, (j+1==n)?0:right[i][j+1])+ nums[i][j];
			}
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 1;i < m-1;i++) {
			for(int j = 1;j < n-1;j++) {
				ans = Math.max(ans, nums[i][j] + top[i-1][j] + down[i+1][j] + left[i][j-1] + right[i][j+1]);
				//System.out.println(ans + " " + i + " " + j);
			}
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		//int [][]nums = { { 1, 1, 1, 1 }, { -6, 1, 1, -4 }, { 1, 1, 1, 1 } };
		int [][]nums = {{1,2,3}, {-6,1,-4}, {1,1,1}, {7,8,9}, {6,3,2}};
		System.out.println(new Solution().maxplus(nums));
	}
}
