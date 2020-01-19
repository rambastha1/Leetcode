package main;

/* count total number of ones, maintain a window of this
 * count maximum number of ones in any window
 * total ones - maxone in any window is answer - minimum number of swap
 * those are the spots with 0 we need to make them one
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/discuss/355386/JavaPython-3-Sliding-Window-O(n)-code-w-brief-explanation-and-analysis.
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together 
 * in any place in the array.

 

Example 1:

Input: [1,0,1,0,1]
Output: 1
Explanation: 
There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
Example 2:

Input: [0,0,0,1,0]
Output: 0
Explanation: 
Since there is only one 1 in the array, no swaps needed.
Example 3:

Input: [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation: 
One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 

Note:

1 <= data.length <= 10^5
0 <= data[i] <= 1
 */
class Solution {
    public int minSwaps(int[] data) {
    	
    	int ones = 0;
    	for(int num : data) {
    		ones += num;
    	}
    	
    	int start = 0, maxones = 0, count = 0;
    	for(int end = 0;end < data.length;end++) {
    		count += data[end];
    		if(end - start >= ones)
    			count -= data[start++];
    		maxones = Math.max(maxones, count);
    	}
    	return ones - maxones;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []data = {0,0,0,1,0};
		int []data = {1,0,1,0,1,0,0,1,1,0,1};
		System.out.println(new Solution().minSwaps(data));
	}
}