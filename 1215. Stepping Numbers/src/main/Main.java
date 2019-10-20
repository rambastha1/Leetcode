package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/* A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1. 
 * For example, 321 is a Stepping Number while 421 is not.

Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.

 

Example 1:

Input: low = 0, high = 21
Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
 

Constraints:

0 <= low <= high <= 2 * 10^9
 * 
 * https://leetcode.com/problems/stepping-numbers/discuss/397600/Simple-BFS
 */

class Solution {
    public List<Integer> countSteppingNumbers(int low, int high) {
    	List<Integer> res = new ArrayList<>();
    	Queue<Long> q = new LinkedList<>();
    	for(long i = 1;i <= 9;i++)
    		q.offer(i);
    	if(low == 0)
    		res.add(low);
    	//BFS will take care of ordering
    	while(!q.isEmpty()) {
    		long num = q.poll();
    		if(num < high) {
    			long x = num%10;
    			// difference should be 1
    			if(x > 0)
    				q.offer(num*10 + x-1);
    			if(x < 9)
    				q.offer(num*10 + x+1);
    		}
    		
    		if(num >= low && num <= high)
    			res.add((int)num);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int low = 0, high = 21;
		System.out.println(new Solution().countSteppingNumbers(low, high));
	}
}