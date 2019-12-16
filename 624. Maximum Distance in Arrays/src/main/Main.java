package main;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/* Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different 
 * arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to 
 * be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
 * 
 * https://leetcode.com/articles/maximum-distance-in-array/
 */

class Solution {
	//Time 0(N)
    public int maxDistance(List<List<Integer>> arrays) {
    	// value -> index
    	int res = 0, min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size()-1);
    	int n = arrays.size();
    	// Notice i starts from 1 -> res will never be max and min from array 0
    	// even if min and max from array 0
    	// res is calculated using elements of different arrays
    	// thus will never be from same array
    	for(int i = 1;i < n;i++) {
    		res = Math.max(res, Math.max(Math.abs(arrays.get(i).get(arrays.get(i).size()-1) - min), 
    				Math.abs(arrays.get(i).get(0) - max)));
    		max = Math.max(max, arrays.get(i).get(arrays.get(i).size()-1));
    		min = Math.min(min, arrays.get(i).get(0));
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> arrays = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(4,5), Arrays.asList(1,2,3));
		System.out.println(new Solution().maxDistance(arrays));
	}
}