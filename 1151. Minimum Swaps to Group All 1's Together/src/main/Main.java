package main;

/* count total number of ones, maintain a window of this
 * count maximum number of ones in any window
 * total ones - maxone in any window is answer - minimum number of swap
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/discuss/355386/JavaPython-3-Sliding-Window-O(n)-code-w-brief-explanation-and-analysis.
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