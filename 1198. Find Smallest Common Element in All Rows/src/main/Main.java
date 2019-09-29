package main;

import java.util.Arrays;
import java.util.TreeMap;

class Solution {
	
	// Time 0(MlgN)
	/* Search for each number in first row 
	 * if all the rows have that number return it. The sorted nature will take care of smallest such number
	 */
	public int smallestCommonElement(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		int []firstrow = mat[0];
		for(int num : firstrow) {
			boolean fail = false;
			for(int i = 1;i < m;i++) {
				int index = Arrays.binarySearch(mat[i], num);
				if(index < 0) {
					fail = true;
					break;
				}
			}
			if(!fail) {
				return num;
			}
		}
		return -1;
	}
	
	// Time 0(MN)
    public int smallestCommonElement1(int[][] mat) {
    	// number -> count
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	int m = mat.length, n = mat[0].length;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			map.put(mat[i][j], map.getOrDefault(mat[i][j], 0) + 1);
    		}
    	}
    	
    	for(int num : map.keySet()) {
    		if(map.get(num) != m)
    			continue;
    		return num;
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]mat = {{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}};
		System.out.println(new Solution().smallestCommonElement(mat));
	}
}