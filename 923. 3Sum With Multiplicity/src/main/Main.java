package main;

import java.util.Arrays;

class Solution {
	// Time 0(n^2)
    public int threeSumMulti(int[] A, int target) {
    	if(A == null || A.length == 0)
    		return 0;
    	int ans = 0;
    	int mod = (1<<31) + 7;
    	Arrays.sort(A);
    	for(int i = 0;i < A.length-2;i++)
    		ans += twosum(A, i+1, A.length-1, target-A[i]);
    	return ans;
    }
    
    int twosum(int []A, int l, int r, int target) {
    	int count = 0;
    	int mod = (1<<31) + 7;
    	while(l < r) {
    		int sum = A[l] + A[r];
    		
    		if(sum == target) {
    			if(A[l] == A[r]) {
    				count = (count + (r - l + 1) * (r - l) / 2) % mod;
    				/*count += ((r-l)*(r-l+1))/2;
    				count %= mod;*/
    				break;
    			}
    			int lcount = 1, rcount = 1;
    			while(l+lcount < r && A[l+lcount] == A[l])
    				lcount++;
    			while(l < r - rcount && A[r-rcount] == A[r])
    				rcount++;
    			count += (lcount*rcount);
    			count %= mod;
    			l += lcount;
    			r -= rcount;
    			
    		} else if(sum < target)
    			l++;
    		else
    			r--;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,1,2,2,3,3,4,4,5,5};
		int target = 8;
		/*int []A = {1,1,2,2,2,2};
		int target = 5;*/
		System.out.println(new Solution().threeSumMulti(A, target));
	}
}
