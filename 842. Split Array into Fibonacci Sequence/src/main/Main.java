package main;

import java.util.ArrayList;
import java.util.List;

/* https://leetcode.com/articles/split-array-into-fibonacci-sequence/
 * starts from index 0, find number a, from +1 index find number b  
 * from b+1 index check if a+b exists from b+1 index
 * if not increase b index 
 * if no b works increase a index to form bigger number and repeat process again
 */

class Solution {
	// Time 0(N) Space 0(N)
    public List<Integer> splitIntoFibonacci(String S) {
    	int n = S.length();
    	// length of Integer.Max(2147483647) = 10 
    	for(int i = 0;i < Math.min(10, n);i++) {
    		// these check for leading zero
    		if(S.charAt(0) == '0' && i > 0)
    			break;
    		long a = Long.valueOf(S.substring(0, i+1));
    		if(a >= Integer.MAX_VALUE)
    			break;
    		Search :
    			for(int j = i+1;j < Math.min(10, n);j++) {
    				if(S.charAt(i+1) == '0' && j > i+1)
    					break;
    				long b = Long.valueOf(S.substring(i+1, j+1));
    				if(b >= Integer.MAX_VALUE)
    					break;
    				List<Integer> fib = new ArrayList<>();
    				fib.add((int)a);
    				fib.add((int)b);
    				int k = j+1;
    				/* this step will add all the fibonacci sequence from k to n-1
    				 */
    				while(k < n) {
    					long next = fib.get(fib.size()-1) + fib.get(fib.size()-2);
    					String nex = String.valueOf(next);
    					if(next <= Integer.MAX_VALUE && S.substring(k).startsWith(nex)) {
    						k += nex.length();
    						fib.add((int)next);
    					} else
    						continue Search;
    				}
    				if(fib.size() >= 3)
    					return fib;
    			}
    	}
    	return new ArrayList<>();
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "123456579";
		//String S = "11235813";
		System.out.println(new Solution().splitIntoFibonacci(S));
	}
}
