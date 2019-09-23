package main;

import java.util.Arrays;
//Activity Selection Problem
import java.util.Comparator;

class Solution {
    public int findLongestChain(int[][] pairs) {
    	Arrays.sort(pairs, new Comparator<int []>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
    	int curr = pairs[0][1], ans = 1;
    	for(int i = 1;i < pairs.length;i++) {
    		if(pairs[i][0] > curr) {
    			curr = pairs[i][1];
    			ans++;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]pairs = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
		System.out.println(new Solution().findLongestChain(pairs));
	}
}