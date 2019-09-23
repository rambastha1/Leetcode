package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://aonecode.com/amazon-online-assessment-oa2-shopkeeper-sale

class Solution {
	void prices(int []A) {
		Stack<Integer> stack = new Stack<>();
		int n = A.length, total = 0;
		for(int i = 0;i < n;i++) {
			while(!stack.isEmpty() && A[i]  <= A[stack.peek()]) 
				total += A[stack.pop()] - A[i];
			stack.push(i);
		}
		
		List<Integer> res = new ArrayList<>();
		while(!stack.isEmpty()) {
			total += A[stack.peek()];
			res.add(0, stack.pop());
		}
		System.out.println("total:" + total);
		System.out.println(res);
	}
}

public class Main {
	public static void main(String[] args) {
		//int []A = {2,3,1,2,4,2};
		//int []A = {5,1,3,4,6,2};
		int []A = {1,3,3,2,5};
		new Solution().prices(A);
	}
}
