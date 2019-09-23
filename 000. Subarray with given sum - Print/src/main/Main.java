package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
/* https://stackoverflow.com/questions/39322019/using-a-map-to-find-subarray-with-given-sum-with-negative-numbers/39322103#39322103
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
 */  	
	// Negative numbers or sum
	void subarray(int []A, int sum) {
		if(A == null || A.length == 0)
			return;
		int n = A.length;
		int s = 0;
		// store cummulative sum to index
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0;i < n;i++) {
			s += A[i];
			if(map.containsKey(s - sum))
				print(A, map.get(s-sum)+1, i);
			map.put(s, i);
		}
	}
	
	// Sliding Window 0(N) If no -ve sum or numbers
	void subarray1(int []A, int sum) {
		if(A == null || A.length == 0)
			return;
		int n = A.length;		
		
		int s = 0, start = 0, end = 0;
		for(end = 0;end < n;end++) {
			if(s == sum)
				print(A, start, end-1);
			s += A[end];
			while(s > sum)
				s -= A[start++];
		}
	}
	
	void print(int []A, int i, int j) {
		for(int x = i;x <= j;x++)
			System.out.print(A[x] + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		/*int []A = {1, 4, 20, 3, 10, 5}; 
		int sum = 33;*/
		
		/*int []A = {1, 4, 0, 0, 3, 10, 5}; 
		int sum = 7;*/
		
		/*int []A = {1, 4}; 
		int sum = 0;*/
		
		int A[] = {10, 2, -2, -20, 10};
		int sum = -10;
		
		/*int A[] = {-10, 0, 2, -2, -20, 10};
		int sum = 20;*/
		
		/*int []A = {1, 3, -2, 1};
		int sum = 2;*/
		
		new Solution().subarray(A, sum);
	}
}