package main;

import java.util.Arrays;
import java.util.Comparator;

/* LIS in 2D
 * https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation
 */

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
    	if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0)
    		return 0;
    	if(envelopes.length == 1)
    		return 1;
    	Arrays.sort(envelopes, new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]==o2[0]?o2[1]-o1[1]:o1[0]-o2[0];
			}
		});
    	
    	int m = envelopes.length, n = envelopes[0].length;
    	int []LIS = new int[m];
    	int len = 0;
    	
    	for(int []envelope : envelopes) {
    		int index = Arrays.binarySearch(LIS, 0, len, envelope[1]);
    		if(index < 0)
    			index = -(index+1);
    		LIS[index] = envelope[1];
    		if(index == len)
    			len++;
    			
    	}
    	return len;
    }
}

public class Main {
	public static void main(String[] args) {
		//int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
		int[][] envelopes = {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
		System.out.println(new Solution().maxEnvelopes(envelopes));
	}
}