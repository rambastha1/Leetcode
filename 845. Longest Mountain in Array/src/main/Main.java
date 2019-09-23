package main;

class Solution {
    public int longestMountain(int[] A) {
    	if(A == null || A.length < 3)
    		return 0;
    	int ans = 0;
    	for(int i = 1;i < A.length;) {
    		if(A[i] > A[i-1]) {
	    		int j = i;
	    		while(j < A.length && A[j] > A[j-1])
	    			j++;
	    		if(j == A.length)
	    			break;
	    		if(A[j] == A[j-1]) {
	    			i = j+1;
	    			continue;
	    		}
	    		
	    		while(j < A.length && A[j] < A[j-1])
	    			j++;
	    		if(j-i>1)
	    			ans = Math.max(ans, j-i+1);
	    		i = j;
    		} else i++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {2,1,4,7,3,2,5};
		int []A = {7,3,2,1};
		System.out.println(new Solution().longestMountain(A));
	}
}
