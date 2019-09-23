package main;
import java.util.ArrayList;
import java.util.List;

class Solution {
	
	public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> list = new ArrayList<>();
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return list;
        int r1 = 0, r2 = matrix.length-1;
        int c1 = 0, c2 = matrix[0].length-1;
    	int i = 0;
    	
    	while(r1 <= r2 && c1 <= c2) {
    		for(i = c1;i <= c2;i++)
    			list.add(matrix[r1][i]);
    		r1++;
    		for(i = r1;i <= r2;i++)
    			list.add(matrix[i][c2]);
    		c2--;
    		if(r1 <= r2) {
	    		for(i = c2;i >= c1;i--)
	    			list.add(matrix[r2][i]);
	    		r2--;
    		}
    		if(c1 <= c2) {
	    		for(i = r2;i >= r1;i--)
	    			list.add(matrix[i][c1]);
	    		c1++;
    		}
    	}
    	return list;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = { {1,2,3,4},
				{5,6,7,8},
				{9,10,11,12}};
		System.out.println(new Solution().spiralOrder(matrix));
	}
}