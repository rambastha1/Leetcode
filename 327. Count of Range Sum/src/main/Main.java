package main;

import java.util.Arrays;

/* Lower <=pre[j] - pre[i] <= Upper
 * lower + pre[i] <= pre[j] <= upper + pre[i]
 * 
 * put all pre[i], pre[i] + lower and pre[i]+upper in array -> built bit of it
 * update all pre[i]
 * now add pre[i]+upper and subtract pre[i]+lower
 * just like find lower elements after itself
 * 0(nlgn)
 * https://leetcode.com/problems/count-of-range-sum/discuss/78006/Summary-of-the-Divide-and-Conquer-based-and-Binary-Indexed-Tree-based-solutions
 */
class Solution {
	public int countRangeSum(int[] nums, int lower, int upper) {
    	int n = nums.length;
        if(n == 0)
            return 0;
    	long []sum = new long[n+1];
    	long []can = new long[3*sum.length + 1];
    	
    	int index = 0;
    	can[index++] = sum[0];
    	can[index++] = sum[0] + lower - 1; // as lower is included
    	can[index++] = sum[0] + upper;
    	
    	for(int j = 1;j < sum.length;j++) {
    		sum[j] = sum[j-1] + nums[j-1];
    		can[index++] = sum[j];
    		can[index++] = sum[j] + lower - 1;
    		can[index++] = sum[j] + upper; // as lower is included
    	}
    	can[index] = Long.MIN_VALUE;
    	
    	Arrays.sort(can);
    	
    	int []bit = new int[can.length];
    	for(int i = 0;i < sum.length;i++) {
    		update(bit, Arrays.binarySearch(can, sum[i]), 1);
    	}
    	
    	int count = 0;
    	
    	for(int i = 0;i < sum.length;i++) {
    		update(bit, Arrays.binarySearch(can, sum[i]), -1);
    		count += sum(bit, Arrays.binarySearch(can, sum[i] + upper));
    		count -= sum(bit, Arrays.binarySearch(can, sum[i] + lower - 1)); 
    	}
    	return count;
    }
    
    private void update(int []bit, int i, int val) {
    	//i++;
    	while(i < bit.length) {
    		bit[i] += val;
    		i += i & (-i);
    	}
    }
    
    private int sum(int []bit, int i) {
    	//i++;
    	int sum = 0;
    	while(i > 0) {
    		sum += bit[i];
    		i -= i & (-i);
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {-2,5,-1};
		int lower = -2, upper = 2;
		System.out.println(new Solution().countRangeSum(nums, lower, upper));
	}
}
