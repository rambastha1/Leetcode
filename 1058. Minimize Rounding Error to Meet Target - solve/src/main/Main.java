package main;

import java.util.PriorityQueue;

/* Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) 
 * so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target. Each operation Roundi(pi) 
 * could be either Floor(pi) or Ceil(pi).

Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, 
which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after the decimal.

 

Example 1:

Input: prices = ["0.700","2.800","4.900"], target = 8
Output: "1.000"
Explanation: 
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
Example 2:

Input: prices = ["1.500","2.500","3.500"], target = 10
Output: "-1"
Explanation: 
It is impossible to meet the target.
 

Note:

1 <= prices.length <= 500.
Each string of prices prices[i] represents a real number which is between 0 and 1000 and has exactly 3 decimal places.
target is between 0 and 1000000.
 * 
 */
// https://leetcode.com/problems/minimize-rounding-error-to-meet-target/discuss/337606/Clean-Java-Solution-using-PriorityQueue

class Solution {
    public String minimizeError(String[] prices, int target) {
    	PriorityQueue<Double> pq = new PriorityQueue<>();
    	float res = 0;
    	for(String price : prices) {
    		float val = Float.valueOf(price);
    		double low = Math.floor(val);
    		double high = Math.ceil(val);
    		
    		// If not an Integer
    		if(low != high) {
    			// closer the number to its ceil, more negative this number will be
    			// this will assist to convert this number to ceil first
    			double num = (high - val) - (val - low);
    			pq.offer(num);
    		}
    		// first convert all numbers to its floor and subtract floor from target
    		res += val - low;
    		target -= low;
    	}
    	
    	// all pq elements are 0.something so if target is higher than all the elements(count),
    	// impossible to convert to target
    	if(target < 0 || target > pq.size())
    		return "-1";
    	
    	// pq elements are all negative
    	// adding means first added difference to num-floor to res
    	// then adding difference between ceil gap to floor gap
    	// eventually means adding adding ceil gap
    	//eg. number 4.9
    	// res += 0.9
    	// pq has -0.8 i.e (5-4.9) - (4.9-4) = -0.8
    	// res += -0.8 = res = res + 0.9 -0.8 = res+ 0.1 i.e ceil difference
    	while(target-- > 0) {
    		res += pq.poll();
    	}
    	return String.format("%.3f", res);
    }
}

public class Main {
	public static void main(String[] args) {
		String []prices = {"0.700","2.800","4.900"};
		int target = 8;
		System.out.println(new Solution().minimizeError(prices, target));
	}
}