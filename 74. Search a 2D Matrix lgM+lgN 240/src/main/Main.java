package main;

class Solution {
	// Time 0(lgm + lgn) = 0(lg(mn))
    public boolean searchMatrix(int[][] matrix, int target) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return false;
    	int m = matrix.length, n = matrix[0].length;
    	int row = -1;
    	int l = 0, r = m-1;
    	
    	// get row of target
    	while(l <= r) {
    		int mid = l + (r-l)/2;
    		if(matrix[mid][0] == target)
    			return true;
    		else if(target > matrix[mid][0]) {
    			if(target <= matrix[mid][n-1]) {
    				row = mid;
    				break;
    			}
    			l = mid + 1;
    		} else 
    			r = mid-1;
    	}
    	if(row == -1)
    		return false;
    	l = 0; r = n-1;
    	// search in columns of that row
    	while(l <= r) {
    		int mid = l + (r-l)/2;
    		if(matrix[row][mid] == target)
    			return true;
    		else if(matrix[row][mid] > target) {
    			r = mid-1;
    		} else 
    			l = mid+1;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{1,3,5,7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
		int target = 16;
		System.out.println(new Solution().searchMatrix(matrix, target));
	}
}
