package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	// Time 0(N^3) Space O(M)
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
    	int m = matrix.length, n = matrix[0].length;
    	int []A = new int[m];
    	int res = 0;
    	/* loop through all pairs of columns and create A
    	 * find target in A
    	 */
    	for(int i = 0;i < n;i++) {
    		Arrays.fill(A, 0);
    		for(int j = i;j < n;j++) {
    			// A[k] will represent summation of elements of row k from column i to column j both inclusive
    			// Thus A represents sum of all elements of a sub matrix
    			for(int k = 0;k < m;k++) {
    				A[k] += matrix[k][j];
    			}
    			// use number of sub array with given sum in 1D
    			res += getcount(A, target);
    		}
    	}
        return res;
    }
    
    private int getcount(int []A, int target) {
    	// sum -> count
    	Map<Integer, Integer> map = new HashMap<>();
    	int sum = 0, res = 0;
    	map.put(0, 1);
    	for(int i = 0;i < A.length;i++) {
    		sum += A[i];
    		if(map.containsKey(sum - target))
    			res += map.get(sum - target);
    		map.put(sum, map.getOrDefault(sum, 0) + 1);
    	}
    	return res;
    }
    
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{0,1,0},{1,1,1},{0,1,0}};
		//int [][]matrix = {{1,-1}, {-1,1}};
		int target = 0;
		System.out.println(new Solution().numSubmatrixSumTarget(matrix, target));
	}
}