package main;

import java.util.Stack;

class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
    	int i = arr1.length-1, j = arr2.length-1, carry = 0;
    	Stack<Integer> stack = new Stack<>();
    	
    	while(i >= 0 || j >= 0 || carry != 0) {
    		if(i >= 0)
    			carry += arr1[i--];
    		if(j >= 0)
    			carry += arr2[j--];
    		stack.push(carry&1);
    		carry = -(carry>>1);
    	}
    	
    	while(stack.size() > 1 && stack.peek() == 0) {
    		stack.pop();
    	}
    	System.out.println(stack);
    	int []ans = new int[stack.size()];
    	for(int index = 0;index < ans.length;index++) {
    		ans[index] = stack.pop(); 
    	}
    	return ans;
    }
    
    void print(int []ans) {
    	for(int i : ans)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//int []arr1 = {1,1,1,1,1}, arr2 = {1,0,1};
		int []arr1 = {1}, arr2 = {1};
		new Solution().addNegabinary(arr1, arr2);
	}
}
