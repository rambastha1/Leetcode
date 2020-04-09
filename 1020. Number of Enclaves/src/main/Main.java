package main;

class Solution {
    public int numEnclaves(int[][] A) {
    	int m = A.length, n = A[0].length;
    	for(int x = 0;x < m;x++) {
    		for(int y = 0;y < n;y++) {
    			if(x == 0 || x == m-1 || y == 0 || y == n-1)
    				if(A[x][y] == 1)
    					dfs(A, x, y);
    		}
    	}
    	
    	int ans = 0;
    	for(int x = 0;x < m;x++) {
    		for(int y = 0;y < n;y++) {
    			if(A[x][y] == 1)
    				ans++;
    		}
    	}
    	return ans;
    }
    
    private void dfs(int [][]A, int x,int y) {
    	if(!issafe(A, x, y))
    		return;
    	
    	A[x][y] = 3;
    	dfs(A, x-1, y);
    	dfs(A, x+1, y);
    	dfs(A, x, y-1);
    	dfs(A, x, y+1);
    }
    
    private boolean issafe(int [][]A, int x, int y) {
    	return x >= 0 && x < A.length && y >= 0 && y < A[x].length && A[x][y] == 1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]A = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
		System.out.println(new Solution().numEnclaves(A));
	}
}
