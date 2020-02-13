package main;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
// https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/92242/ConciseEasy-to-understand-Java-5ms-solution-with-Explaination
class Solution {
	// 0((lgN)^2)
	public int findKthNumber(int n, int k) {
	    int curr = 1;
	    k = k - 1;
	    while (k > 0) {
	        int steps = calSteps(n, curr, curr + 1);
	        if (steps <= k) {
	            curr += 1;
	            k -= steps;
	        } else {
	            curr *= 10;
	            k -= 1;
	        }
	    }
	    return curr;
	}
	//use long in case of overflow
	public int calSteps(int n, long n1, long n2) {
	    int steps = 0;
	    while (n1 <= n) {
	        steps += Math.min(n + 1, n2) - n1;
	        n1 *= 10;
	        n2 *= 10;
	    }
	    return steps;
	}
	
	// Naive Way 
    public int findKthNumber1(int n, int k) {
    	Set<Integer> curr = new HashSet<>();
    	PriorityQueue<String> pq = new PriorityQueue<>(Collections.reverseOrder());
    	for(int i = 1;i <= 9;i++) {
    		curr.add(i);
    		pq.offer(String.valueOf(i));
    		if(pq.size() > k)
    			pq.poll();
    	}
    	dfs(n, k, pq, curr);
    	if(pq.isEmpty())
    		return -1;
    	return Integer.parseInt(pq.peek());
    }
    
    void dfs(int n, int k, PriorityQueue<String> pq, Set<Integer> curr) {
    	boolean found = true;
    	while(found) {
    		found = false;
    		Set<Integer> temp = new HashSet<>();
    		for(int c : curr) {
    			for(int i = 0;i <= 9;i++) {
    				int num = c*10 + i;
    				if(num >= 1 && num <= n) {
    					found = true;
    					pq.offer(String.valueOf(num));
    					if(pq.size() > k)
    						pq.poll();
    					temp.add(num);
    				}
    			}
    		}
    		curr = temp;
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 2, k = 3;
		System.out.println(new Solution().findKthNumber(n, k));
	}
}
