package main;

class Solution {
    public int[][] generateMatrix(int n) {
    	int [][]res = new int[n][n];
    	int rmin = 0, rmax = n-1, cmin = 0, cmax = n-1;
    	int num = 1;
    	while(num <= n*n) {
    		for(int col = cmin;col <= cmax;col++)
    			res[rmin][col] = num++;
    		rmin++;
    		
    		for(int row = rmin;row <= rmax;row++)
    			res[row][cmax] = num++;
    		cmax--;
    		
    		if(cmin <= cmax) {
    			for(int col = cmax;col >= cmin;col--)
    				res[rmax][col] = num++;
    			rmax--;
    		}
    		
    		if(rmin <= rmax) {
    			for(int row = rmax;row >= rmin;row--)
        			res[row][cmin] = num++;
    			cmin++;
    		}
    	}
    	print(res);
    	return res;
    }
    
    void print(int [][]res) {
    	for(int i = 0;i < res.length;i++) {
    		for(int j = 0;j < res[0].length;j++) 
    			System.out.print(res[i][j] + " ");
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		new Solution().generateMatrix(n);
	}
}