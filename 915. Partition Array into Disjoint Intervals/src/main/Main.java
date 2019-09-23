package main;

class Solution {
    public int partitionDisjoint(int[] A) {
    	if(A == null || A.length == 0)
    		return 0;
    	int n = A.length-1, max = A[0], leftmax = A[0], res = 1;
    	for(int i = 1;i < n;i++) {
    		max = Math.max(max, A[i]);
    		// important don't put equal sign as we need smallest such left
    		if(A[i] < leftmax) {
    			res = i+1;
    			leftmax = max; 
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {5,0,3,8,6};
		int []A = {1,1,1,0,6,3,8,12};
		System.out.println(new Solution().partitionDisjoint(A));
	}
}
