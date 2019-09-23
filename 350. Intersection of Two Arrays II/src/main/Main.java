package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  Follow Up question 3 -> use external sort
 */

class Solution {
	/* Follow up array already sorted
	 * 0(m+n) approach
	 */
	public int[] intersect(int[] nums1, int[] nums2) {
		// assuming array to be not sorted to include another 0(NlgN) method
		int m = nums1.length, n = nums2.length;
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		List<Integer> list = new ArrayList<>();
		int i = 0, j = 0;
		
		while(i < m && j < n) {
			if(nums1[i] < nums2[j])
				i++;
			else if(nums1[i] > nums2[j])
				j++;
			else {
				list.add(nums1[i]);
				i++;
				j++;
			}
		}
		int []res = new int[list.size()];
    	for(i = 0;i < list.size();i++)
    		res[i] = list.get(i);
    	return res;
	}
	
	
	// Not Sorted 0(N)
    public int[] intersect1(int[] nums1, int[] nums2) {
    	// number -> count
    	Map<Integer, Integer> map1 = new HashMap<>();
    	Map<Integer, Integer> map2 = new HashMap<>();
    	for(int num : nums1)
    		map1.put(num, map1.getOrDefault(num, 0) + 1);
    	
    	for(int num : nums2)
    		map2.put(num, map2.getOrDefault(num, 0) + 1);
    	
    	List<Integer> list = new ArrayList<>();
    	for(int num : map1.keySet()) {
    		if(map2.containsKey(num)) {
    			int count = Math.min(map1.get(num), map2.get(num));
    			while(count > 0) {
    				list.add(num);
    				count--;
    			}
    		}
    	}
    	int []res = new int[list.size()];
    	for(int i = 0;i < list.size();i++)
    		res[i] = list.get(i);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums1 = {1,2,2,1}, nums2 = {2,2};
		int []nums1 = {4,9,5}, nums2 = {9,4,9,8,4};
		System.out.println(Arrays.toString(new Solution().intersect(nums1, nums2)));
	}
}