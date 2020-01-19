package main;

import java.util.Stack;

/* https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/
 * https://leetcode.com/problems/maximal-rectangle/discuss/29064/A-O(n2)-solution-based-on-Largest-Rectangle-in-Histogram
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
    	if(matrix.length == 0 || matrix[0].length == 0)
            return 0;
    	int m = matrix.length, n = matrix[0].length;
    	int []dp = new int[n];
    	for(int col = 0;col < n;col++) 
    		dp[col] = matrix[0][col] == '1'?1:0;
    	
    	int ans = histogram(dp);
    	for(int row = 1;row < m;row++) {
    		for(int col = 0;col < n;col++) {
    			if(matrix[row][col] == '1') {
    				dp[col] += 1;
    			} else {
    				dp[col] = 0;
    			}
    		}
    		int area = histogram(dp);
    		ans = Math.max(ans, area);
    	}
    	return ans;
    }
    
    private int histogram(int []A) {
    	int n = A.length;
    	Stack<Integer> stack = new Stack<>();
    	int max = 0, i = 0;
    	while(i < n) {
    		while(!stack.isEmpty() && A[i] < A[stack.peek()]) {
    			int min = stack.pop();
    			max = Math.max(max, A[min] * (i - (stack.isEmpty()?0:stack.peek()+1)));
    		}
    		stack.push(i++);
    	}
    	
    	while(!stack.isEmpty()) {
    		max = Math.max(max, A[stack.pop()] * (n - (stack.isEmpty()?0:stack.peek()+1)));
    	}
    	return max;
    }
    
    public int largestRectangleArea(int[] heights) {
    	if(heights == null || heights.length == 0)
    		return 0;
    	int n = heights.length;
    	int ans = 0;
    	Stack<Integer> stack = new Stack<>();
    	int i = 0;
    	while(i < n) {
    		while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
    			int min = stack.pop();
    			ans = Math.max(ans, heights[min]*(i - (stack.isEmpty()?0:stack.peek()+1)));
    		}
    		stack.push(i++);
    	}
    	
    	while(!stack.isEmpty()) {
    		ans = Math.max(ans, heights[stack.pop()]*(n-(stack.isEmpty()?0:stack.peek()+1)));
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//char [][]matrix = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}};
		char [][]matrix = {{'1', '0'}};
		System.out.println(new Solution().maximalRectangle(matrix));
	}
}
