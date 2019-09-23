package main;

class Solution {
	
	//optimized code
	public int matrixScore(int[][] A) {
		//1000 > 0111 thus flip all rows where leftmost value is 0
		for(int i = 0;i < A.length;i++) 
			if(A[i][0] == 0)
				flip(A, i);
		
		int ans = 0;
		/* 2^0 = 1
		 * j starts from rightmost where 2^0 is used and move leftwords where 2^A[0].length will
		 * be used. Thus, bitval = 1 and gets multiplied by 2 with each iteration.
		 * After flipping rows with leftmost 0, we don't need to flip columns where 
		 * num_zero > num_ones as A.length - ones will give that value.
		 * Just multiply this value by bitval and add to sum. 
		 */
		int bitVal = 1;
		for(int j = A[0].length-1;j >= 0;j--) {
			int num_ones = 0;
			for(int i = 0;i < A.length;i++) {
				if(A[i][j] == 1)
					num_ones++;
			}
			num_ones = Math.max(num_ones, A.length - num_ones);
			ans += bitVal*num_ones;
			bitVal *= 2;
		}
		return ans;
	}
	
	public void flip(int [][]A, int row) {
		for(int j = 0;j < A[0].length;j++)
			A[row][j] ^= 1;
	}
	
    /*public int matrixScore(int[][] A) {
    	
    	for(int i = 0;i < A.length;i++) {
    		if(A[i][0] == 0)
    			flip(A, i, true);
    	}
    	
    	for(int j = 1;j < A[0].length;j++) {
    		int count = 0;
    		for(int i = 0;i < A.length;i++) {
    			if(A[i][j] == 0)
    				count++;
    		}
    		if(count > A.length/2)
    			flip(A, j, false);
    	}
    	//print(A);
    	int sum = 0;
    	for(int i = 0;i < A.length;i++) {
    		for(int j = A[0].length-1, index = 0;j >= 0;j--, index++) {
    			sum += A[i][j] * Math.pow(2, index);
    		}
    		System.out.println("sum:" + sum);
    	}
    	return sum;
    }
    
    public void flip(int [][]A, int val, boolean isrow) {
    	if(isrow) {
    		for(int j = 0;j < A[val].length;j++)
    			A[val][j] ^= 1;
    	} else {
    		for(int i = 0;i < A.length;i++)
    			A[i][val] ^= 1;
    	}
    }*/
    
    public void print(int [][]A) {
    	for(int i = 0;i < A.length;i++) {
    		for(int j = 0;j < A[0].length;j++) {
    			System.out.print(A[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]A = {{0,0,1,1}, {1,0,1,0}, {1,1,0,0}};
		System.out.println(new Solution().matrixScore(A));
	}
}