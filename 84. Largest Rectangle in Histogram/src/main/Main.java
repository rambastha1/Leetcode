package main;

import java.util.Stack;
// https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/29018/AC-clean-Java-solution-using-stack

/* for every element which is smaller than stack.peek(), if we multiply this element, area would be smaller
 * thus we keep pop till heights[stack.peek()] > heights[i]
 * calculate area and compare with answer
 */

class Solution {
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
		//int []heights = {2,1,5,6,2,3};
		int []heights = {5,5,1,7,1,1,5,2,7,6};
		System.out.println(new Solution().largestRectangleArea(heights));
	}
}