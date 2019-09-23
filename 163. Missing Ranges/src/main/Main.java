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
        List<String> res = new ArrayList<String>();
        int next_ele = lower;
        for (int i = 0; i < nums.length; i++) {
            // 1. We don't need to add [Integer.MAX_VALUE, ...] to result
            if(lower == Integer.MAX_VALUE) return res;
            if (nums[i] < next_ele) {
                continue;
            }
            if (nums[i] == next_ele) {
                next_ele++;
                continue;
            }
            /*
             * means next_ele > nums[i]
             * No need to traverse from next_ele to nums[i]
             */
            
            res.add(getRange(next_ele, nums[i] - 1));
            // 2. We don't need to proceed after we have process Integer.MAX_VALUE in array
            if(nums[i] == Integer.MAX_VALUE) return res;
            //directly start from nums[i]+1
            next_ele = nums[i] + 1;
        }
        
        if (next_ele <= upper) {
            res.add(getRange(next_ele, upper));
        }
        return res;
    }
    
    public String getRange(int n1, int n2) {
        return n1 == n2 ? String.valueOf(n1) : String.format("%d->%d" , n1, n2);
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {0, 1, 3, 50, 75};
		System.out.println(new Solution().findMissingRanges(nums, 0, 99));
	}
}