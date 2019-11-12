package main;

import java.util.ArrayList;
import java.util.List;

/* Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, 
 * return a sorted array of only the integers that appeared in all three arrays.

 

Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
 

Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
 * 
 */

class Solution {
	// 0(N)
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
    	int n = arr1.length;
    	List<Integer> res = new ArrayList<>();
    	int i = 0, j = 0, k = 0;
    	
    	for(int a : arr1) {
    		
    		for(;j < n;j++) {
    			if(arr2[j] >= a) {
    				break;
    			}
    		}
    		if(j == n)
    			break;
    		
    		if(arr2[j] != a) {
    			continue;
    		}
    		
    		for(;k < n;k++) {
    			if(arr3[k] >= a)
    				break;
    		}
    		if(k == n)
    			break;
    		
    		if(arr3[k] != a) {
    			continue;
    		}
    		res.add(a);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr1 = {1,2,3,4,5}, arr2 = {1,2,5,7,9}, arr3 = {1,3,4,5,8};
		System.out.println(new Solution().arraysIntersection(arr1, arr2, arr3));
	}
}