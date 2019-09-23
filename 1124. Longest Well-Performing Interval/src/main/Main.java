package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode.com/problems/longest-well-performing-interval/discuss/335163/O(N)-Without-Hashmap.-Generalized-ProblemandSolution%3A-Find-Longest-Subarray-With-Sum-greater-K.
class Solution {
	
	public int longestWPI(int[] hours) {
        int n = hours.length;
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            sum += hours[i] > 8?1:-1;
            if(sum > 0)
                res = i + 1;
            else{
                if(map.containsKey(sum-1)){
                    res = Math.max(res, i - map.get(sum-1));
                }
                map.putIfAbsent(sum, i);
            }
        }
        return res;
    }
	
	// generic
    public int longestWPI1(int[] hours) {
    	if(hours.length == 1)
    		return hours[0]>8?1:0;
    		
    	int n = hours.length, ans = 0;
    	int []prefix = new int[n+1];
    	for(int i = 1;i <= n;i++)
    		prefix[i] = prefix[i-1] + (hours[i-1]>8?1:-1);
    		
    	Stack<Integer> stack = new Stack<>();
    	for(int i = 0;i <= n;i++)
    		if(stack.isEmpty() || prefix[stack.peek()] > prefix[i])
    			stack.push(i);
    	
    	for(int i = n;i >= 0;i--) {
    		while(!stack.isEmpty() && prefix[stack.peek()] < prefix[i]) {
    			ans = Math.max(ans, i - stack.peek());
    			stack.pop();
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []hours = {9,9,6,0,6,6,9};
		//int []hours = {6,6,9};
		int []hours = {6,9,9};
		System.out.println(new Solution().longestWPI(hours));
	}
}
