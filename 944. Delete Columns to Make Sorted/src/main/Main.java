package main;

class Solution {
    public int minDeletionSize(String[] A) {
    	int ans = 0;
    	
    	for(int c = 0;c < A[0].length();c++) {
    		for(int r = 1;r < A.length;r++) {
    			if(A[r].charAt(c) < A[r-1].charAt(c)) {
    				ans++;
    				break;
    			}
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String []A = {"cba","daf","ghi"};
		System.out.println(new Solution().minDeletionSize(A));
	}
}
