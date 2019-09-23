package main;

class Solution {
    public int[] sortArrayByParity(int[] A) {
    	if(A == null || A.length == 0)
    		return A;
    	int l = 0, r = A.length-1;
    	while(l < r) {
    		if(A[l]%2 != 0 && A[r]%2==0)
    			swap(A, l++, r--);
    		if(A[l]%2==0)
    			l++;
    		if(A[r]%2!=0)
    			r--;
    	}    	
    	print(A);
    	return A;
    }
    
    void swap(int []A, int l, int r) {
    	int temp = A[l];
    	A[l] =A[r];
    	A[r] = temp;
    }
    
    void print(int []A) {
    	for(int i : A)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {3,1,2,4};
		new Solution().sortArrayByParity(A);
	}
}