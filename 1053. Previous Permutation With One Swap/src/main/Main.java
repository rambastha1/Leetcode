package main;
// https://leetcode.com/problems/previous-permutation-with-one-swap/discuss/299215/JavaC%2B%2BPython-When-Leetcode-Was-Wrong

class Solution {
    public int[] prevPermOpt1(int[] A) {
    	int n = A.length, left = n-2, right = n-1;
    	while(left >= 0 && A[left] <= A[left+1]) left--;
    	if(left < 0) return A;
    	// right can come to left side of left. this will give maximum number smaller than A[left]
    	while(A[left] <= A[right]) right--;
    	while(right > 0 && A[right-1] == A[right]) right--;
    	swap(A, left, right);
    	print(A);
    	return A;
    }
    
    void print(int []A) {
    	for(int i : A)
    		System.out.print(i + " ");
    	System.out.println();
    }
    
    void swap(int []A, int a, int b) {
    	int temp = A[a];
    	A[a] = A[b];
    	A[b] = temp;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {1,9,4,6,7};
		int []A = {9,5,3,4,4,5,6};
		//int []A = {3,1,3,1};
		new Solution().prevPermOpt1(A);
	}
}