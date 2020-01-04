package main;

class Solution {
	
	// To make it 0(M*N) store two minimum values from previous row like Paint House 2 265
	public int minFallingPathSum(int[][] arr) {
		int m = arr.length, n = arr[0].length;
		
		int fm = Integer.MAX_VALUE, secmin = Integer.MAX_VALUE;
		for(int a : arr[0]) {
			if(a < fm) {
				secmin = fm;
				fm = a;
			} else if(a < secmin)
				secmin = a;
		}
		
		for(int i = 1;i < m;i++) {
			int currfm = Integer.MAX_VALUE, currsm = Integer.MAX_VALUE;
			for(int j = 0;j < n;j++) {
				
				if(arr[i-1][j] == fm)
					arr[i][j] += secmin;
				else
					arr[i][j] += fm;
				
				if(arr[i][j] < currfm) {
					currsm = currfm;
					currfm = arr[i][j];
				} else if(arr[i][j] < currsm)
					currsm = arr[i][j];
			}
			fm = currfm;
			secmin = currsm;
		}
		return fm;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]arr = {{1,2,3}, {4,5,6}, {7,8,9}};
		int [][]arr = {{-73,61,43,-48,-36},{3,30,27,57,10},{96,-76,84,59,-15},{5,-49,76,31,-7},{97,91,61,-46,67}};
		System.out.println(new Solution().minFallingPathSum(arr));
	}
}
