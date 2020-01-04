package main;

import java.util.Arrays;

/* Given an integer array arr and a target value target, return the integer value such that when we change all the integers 
 * larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) 
 * to target.

In case of a tie, return the minimum such integer.

Notice that the answer is not necessarily a number from arr.

 

Example 1:

Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
Example 2:

Input: arr = [2,3,5], target = 10
Output: 5
Example 3:

Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361
 

Constraints:

1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5
 * 
 */

class Solution {
	// Time 0(Nlgn)
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int l = 0, r = target;

        int diff = -1, ans = -1;
        while(l <= r) {
            int m = l + (r-l)/2;
            int sum = 0;
            for(int a : arr) {
                sum += Math.min(a, m);
            }
            sum -= target;
            if(diff < 0 || Math.abs(sum) < diff) {
                diff = Math.abs(sum);
                ans = m;
            }
            
            if(sum < 0)
                l = m+1;
            else
                r= m-1;
        }
        return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {60864,25176,27249,21296,20204};
		int target = 56803;
		System.out.println(new Solution().findBestValue(arr, target));
	}
}
