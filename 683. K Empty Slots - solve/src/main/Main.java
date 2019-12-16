package main;

/* You have N bulbs in a row numbered from 1 to N. Initially, all the bulbs are turned off. 
 * We turn on exactly one bulb everyday until all bulbs are on after N days.

You are given an array bulbs of length N where bulbs[i] = x means that on the (i+1)th day, 
we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.

Given an integer K, find out the minimum day number such that there exists two turned on bulbs that have exactly K 
bulbs between them that are all turned off.

If there isn't such day, return -1.

 

Example 1:

Input: 
bulbs: [1,3,2]
K: 1
Output: 2
Explanation:
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.
Example 2:

Input: 
bulbs: [1,2,3]
K: 1
Output: -1
 

Note:

1 <= N <= 20000
1 <= bulbs[i] <= N
bulbs is a permutation of numbers from 1 to N.
0 <= K <= 20000
 * 
 */

/* The idea is to use an array days[] to record each position's bulb's blooming day. 
 * That means days[i] is the blooming day of the bulb in position i+1. 
 * We just need to find a subarray days[left, left+1,..., left+k-1, right] which satisfies: for any i = left+1,..., left+k-1, 
 * we can have days[left] < days[i] && days[right] < days[i]. Then, the result is max(days[left], days[right]).
 * If all these i have days[i] greter than both days[left] and days[right] it means when left and right were switched on,
 * these were off thus ans
 * https://leetcode.com/problems/k-empty-slots/discuss/107931/JavaC%2B%2B-Simple-O(n)-solution
 */
class Solution {
    public int kEmptySlots(int[] bulbs, int K) {
    	int n = bulbs.length;
    	int []day = new int[n];
    	for(int i = 0;i < n;i++) {
    		day[bulbs[i] - 1] = i+1;
    	}
    	// start and end of window
    	int left = 0, right = K+1;
    	int ans = Integer.MAX_VALUE;
    	for(int i = 1;right < n;i++) {
    		// if current day is valid continue reading
    		if(day[i] > day[left] && day[i] > day[right])
    			continue;
    		// reached boundary of window, since all previous values are valid record result
    		if(i == right) {
    			// maximum day of left and right
    			ans = Math.min(ans, Math.max(day[left], day[right]));
    		}
    		
    		// if not valid, move sliding window
    		left = i;
    		right = left + K + 1;
    	}
    	return ans == Integer.MAX_VALUE?-1:ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []bulbs = {1,3,2};
		int K = 1;
		System.out.println(new Solution().kEmptySlots(bulbs, K));
	}
}