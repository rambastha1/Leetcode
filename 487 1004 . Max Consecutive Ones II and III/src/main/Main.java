package main;
/*
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can 
 * flip at most one 0.
 * 
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 * 
 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * 
 * Note:
package main;
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 * 
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream? In other words, 
 * you can't store all numbers coming from the stream as it's too large to hold in memory. 
 * Could you solve it efficiently?
 */

// It is basically find longest substring with atmost K 0's 

class Solution {
	//Better way of writing
	public int findMaxConsecutiveOnes(int[] nums) {
		//K = 1 is used in this problem..for 1004 K is parameter but same solution 
		// K = 0 gives ans to part I 485
		// same as max subarray with atmost k distinct characters 
		int i = 0, j = 0, maxlen = Integer.MIN_VALUE, zero = 0, k = 1;
		for(j = 0;j < nums.length;j++) {
			if(nums[j] == 0)
				zero++;
			while(zero > k) {
				if(nums[i++] == 0)
					zero--;
			}		
			maxlen = Math.max(maxlen, j-i+1);
		}
		return maxlen;
	}
	
	public int longestOnes(int[] A, int K) {
        int l = 0, zero = 0, ans = 0;
        for(int r = 0;r < A.length;r++) {
            if(A[r] == 0)
                zero++;
            while(zero > K)
                if(A[l++] == 0)
                    zero--;
            ans = Math.max(ans, r-l+1);
        }
        return ans;
    }
}


public class Main {
	public static void main(String[] args) {
		int []nums = {1,0,1,1,0};
		System.out.println(new Solution().findMaxConsecutiveOnes(nums));
		int []A = {1,1,1,0,0,0,1,1,1,1,0};
		int K = 2;
		System.out.println(new Solution().longestOnes(A, K));
	}
}