package main;

import java.util.Arrays;
import java.util.Comparator;
// https://leetcode.com/problems/set-intersection-size-at-least-two/discuss/113086/Hope-you-enjoy-this-problem.-%3A-)-O(NlogN)JavaGreedy-Easy-to-understand-solution
class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
    	// sorting this way ensures that intervals are sorted in increasing order of interval gap i[1] - i[0]
    	/*
    	 * e.g [[1,5],[4,5],[5,9],[7,9],[9,10]] => [[4,5], [1,5], [7,9], [5,9] , [9,10]]
    	 * if we have smaller interval and we get larger interval which contains both these elements, discard as 
    	 * keeping these two values will satisfy the condition as well
    	 * for eg [4,5] . we get [3,6] no point in keeping [3,6] and [4,5] satisfies [3,6] as well 
    	 */
    	int n = intervals.length;
    	Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] == o2[1]?o2[0]-o1[0]:o1[1]-o2[1];
			}
		});
    	
    	int res = 0;
    	// two highest most point have more chances of being picked later
    	/* proof why largest two elements are enough
    	 * intervals are sorted in increasing end point.
https://leetcode.com/problems/set-intersection-size-at-least-two/discuss/113085/Ever-wonder-why-the-greedy-algorithm-works-Here-is-the-explanation!
    	 */
    	int l = intervals[0][1] - 1, r = intervals[0][1];
    	res += 2;
    	
    	for(int i = 0;i < n;i++) {
    		int []curr = intervals[i];
    		// 1 element common
    		if(l < curr[0] && curr[0] <= r) {
    			res++;
    			l = r;
    			r = curr[1];
    		// none common 
    		} else if(curr[0] > r) {
    			res += 2;
    			l = curr[1]-1;
    			r = curr[1];
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] intervals = {{2,10}, {3,7}, {3,15}, {4,11}, {6,12}, {6,16}, {7,8}, {7,11}, {7,15}, {11,12}};
		System.out.println(new Solution().intersectionSizeTwo(intervals));
	}
}
