package main;

class Solution {
	int partition(int []A) {
		if(A == null || A.length == 0)
			return -1;
		int n = A.length;
		int []pre = new int[n];
		int sum = 0;
		for(int i = 0;i < n;i++)
			sum += A[i];
		
		int left = 0, right = 0;		
		for(int i = 0;i < n;i ++) {
			left += A[i];
			if(left == sum-left) {
				print(A, 0, i);
				print(A, i+1, n-1);
				return i;
			}
		}
		return -1;
	}
	
	void print(int []A, int i, int j) {
		for(int x = i;x <= j;x++)
			System.out.print(A[x] + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		//int []A = {1 , 2 , 3 , 4 , 5 , 5 };
		//int []A = {4, 1, 2, 3};
		int []A = {-4, -1, 2, -3};
		System.out.println("index: " + new Solution().partition(A));
	}
}
