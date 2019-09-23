package main;

class Solution {
	
	/* All local inversions are global inversions but not vice versa.
	 * If any element is not in its normal position by more than 1 index, it will be counted in global 
	 * but not in local. Thus false
	 */
	public boolean isIdealPermutation(int[] A) {
		if(A == null || A.length == 0)
    		return false;
    	if(A.length == 1)
    		return true;
    	for(int i = 0; i < A.length; i++){
            if(A[i] - i < -1 || A[i] - i > 1)
                return false;
        }
    	return true;
	}
	
	// using BIT
    public boolean isIdealPermutation1(int[] A) {
    	if(A == null || A.length == 0)
    		return false;
    	if(A.length == 1)
    		return true;
    	int n = A.length;
    	int local = 0;
    	for(int i = 0;i < n-1;i++) {
    		if(A[i] > A[i+1])
    			local++;
    	}
    	int global = 0;
    	
    	bit = new int[n+1];
    	for(int i = n - 1;i >= 0;i--) {
    		global += sum(A[i]-1);
    		update(A[i], 1);
    	}
    	return local == global;
    }
    
    int []bit;
    
    int sum(int i) {
    	int sum = 0;
    	i++;
    	while(i > 0) {
    		sum += bit[i];
    		i -= i & (-i);
    	}
    	return sum;
    }
    
    void update(int i, int val) {
    	i++;
    	while(i < bit.length) {
    		bit[i] += val;
    		i += i & (-i);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,0,2};
		//int []A = {1,2,0};
		//int []A = {2,1,0};
		System.out.println(new Solution().isIdealPermutation(A));
	}
}
