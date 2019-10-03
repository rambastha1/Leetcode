package main;

import java.util.Arrays;
import java.util.Comparator;
// https://leetcode.com/problems/two-city-scheduling/discuss/278716/C%2B%2B-O(n-log-n)-sort-by-savings
class Solution {
	/* sort based on difference.
	 * first n/2 to city A then city B
	 */
    public int twoCitySchedCost(int[][] costs) {
    	Arrays.sort(costs, new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[0]-o1[1]) - (o2[0]-o2[1]);
			}
		});
    	int res = 0, n = costs.length;
    	for(int i = 0;i < n/2;i++) {
    		res += costs[i][0] + costs[i+n/2][1];
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]costs = {{10,20},{30,200},{400,50},{30,20}};
		int [][]costs = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
		System.out.println(new Solution().twoCitySchedCost(costs));
	}
}