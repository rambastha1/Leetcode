package main;

/* https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
 * The key point for any binary search is to figure out the "Search Space". 
 * For me, I think there are two kind of "Search Space" -- index and range
 * (the range from the smallest number to the biggest number). 
 * Most usually, when the array is sorted in one direction, 
 * we can use index as "search space", when the array is unsorted and we are going to 
 * find a specific number, we can use "range".
 * 
 * The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions, 
 * we can not find a linear way to map the number and its index.
 */

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        
        int n = matrix.length;
        if(n == 1 && k == 1)
            return matrix[0][0];
        int low = matrix[0][0], high = matrix[n - 1][n - 1] + 1;//[lo, hi)
        while(low < high) {
            int mid = (low+high)/2;
            int count = 0,  j = n - 1;
            //count larger elements in entire array
            for(int i = 0; i < n; i++) {
                while(j >= 0 && matrix[i][j] > mid) 
                    j--;
                count += (j + 1);
            }
            if(count < k) low = mid+1;
            else high = mid;
        }
        return low;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = { {1,  5,  9}, {10, 11, 13}, {12, 13, 15}};
		int k = 8;
		System.out.println(new Solution().kthSmallest(matrix, k));
	}
}