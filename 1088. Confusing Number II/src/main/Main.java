package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, 
 * they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.

 

Example 1:

Input: 20
Output: 6
Explanation: 
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:

Input: 100
Output: 19
Explanation: 
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 

Note:

1 <= N <= 10^9
 * 
 */

// https://leetcode.com/problems/confusing-number-ii/discuss/312767/Java-DFS-Solution

class Solution {
	
	static Map<Integer, Integer> map = new HashMap<>();
	static {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
    }
	
    public int confusingNumberII(int N) {
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(0);
    	
    	// if do this duplicates
    	/*q.offer(1);
    	q.offer(8);
    	q.offer(6);
    	q.offer(9);*/
    	
    	int []list = {0,1,8,6,9};
    	
    	int ans = 0;
    	while(!q.isEmpty()) {
    		int num = q.poll();
    		if(isconfusing(num, N)) {
    			ans++;
    		}
    		for(int digit : list) {
    			int temp = num;
    			if(temp == 0 && digit == 0)
    				continue;
    			
    			temp = temp*10 + digit;
    			if(temp <= N) {
    				q.offer(temp);
    			}
    		}
    	}
        return ans;
    }
    
    private boolean isconfusing(int num, int N) {
    	if(num <= 1 || num > N)
    		return false;
    	int temp = num, rev = 0;
    	while(temp > 0) {
    		rev = rev*10 + map.get(temp%10);
    		temp /= 10;
    	}
    	return rev!= num;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 100;
		System.out.println(new Solution().confusingNumberII(N));
	}
}