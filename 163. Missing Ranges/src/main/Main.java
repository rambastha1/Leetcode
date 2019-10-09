package main;
import java.util.List;
import java.util.ArrayList;

/*
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], 
 * return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, 
 * return ["2", "4->49", "51->74", "76->99"]
 */

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        
        if (nums.length == 0) {
            if (lower > upper) return res;
            if (lower == upper) {
                res.add(String.valueOf(lower));
                return res;
            }
            res.add(lower + "->" + upper);
            return res;
        }
        
        
        int start = 0;
        int end =  nums.length - 1;
        while (start <= end && nums[start] < lower) 
        	start++;
        while (start <= end && nums[end] > upper) 
        	end--;
        
        if (start > end) 
        	return res;
        
        for (int i = start; i <= end; i++) {
            if (i == start) {
                if (nums[i] > lower) {
                    if (nums[i] - 1 == lower) {
                        res.add(String.valueOf(lower));
                    } else {
                        res.add(lower + "->" + (nums[i] - 1));
                    }
                }
            }
            if (i == end) {
                if (nums[i] < upper) {
                    if (nums[i] + 1 == upper) {
                        res.add(String.valueOf(upper));
                    } else {
                        res.add((nums[i] + 1) + "->" + upper);
                    }
                }
            }
            
            if (i >= start && i < end) {
                if (((long)(nums[i]) + 1) >= (long)(nums[i + 1])) {
                    continue;
                }
                
                int lo = nums[i] + 1;
                int hi = nums[i + 1] - 1;
                if (lo == hi) {
                    res.add(String.valueOf(lo));
                } else {
                    res.add(lo + "->" + hi);
                }
            }
        }
        
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {0, 1, 3, 50, 75};
		System.out.println(new Solution().findMissingRanges(nums, 0, 99));
	}
}