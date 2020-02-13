package main;

import java.util.HashMap;
import java.util.Map;

/* Case 1:
frequency * (number of elements with that frequency) == length AND i != nums.length - 1
E.g. [1,1,2,2,3]
When the iteration is at index 3, the count will be equal to the length. It should update the result with (length + 1) as it 
should take an extra element in order to fulfil the condition.

Case 2:
frequency * (number of elements with that frequency) == length - 1
E.g. [1,1,1,2,2,3]
When the iteration is at index 4, the count will be equal to (length - 1). It should update the result with length as it fulfill 
the condition.
or
the current a occurs c times. So except all numbers that also occurs c times, it should leave one single number, or c + 1 same number.
i.e 1 extra number to delete
 * https://leetcode.com/problems/maximum-equal-frequency/discuss/403931/JavaC%2B%2BPython-Easy-to-understand-(compare-counts)
 */
class Solution {
	// 0(N) time and space
    public int maxEqualFreq(int[] nums) {
    	int n = nums.length;
    	// number -> count
    	Map<Integer, Integer> count = new HashMap<>();
    	// freq -> count
    	Map<Integer, Integer> freq = new HashMap<>();
    	int res = 0;
    	
    	for(int i = 0;i < n;i++) {
    		count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
    		int f = count.get(nums[i]);
    		freq.put(f, freq.getOrDefault(f, 0) + 1);
    		
    		if(f* freq.get(f) == i+1 && i != n-1)
    			res = Math.max(res, i+2);
    		else if(f * freq.get(f) == i)
    			res = Math.max(res, i+1);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {10,2,8,9,3,8,1,5,2,3,7,6};
		System.out.println(new Solution().maxEqualFreq(nums));
	}
}
