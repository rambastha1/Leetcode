package main;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
    	int []res = new int[n];
    	Stack<Integer> s = new Stack<>();
    	// prev tracks start or end of previously running thread
    	// unless consequent start and end of same id comes
    	int prev = 0;
    	for(String log : logs) {
    		String []arr = log.split(":");
    		/* Encounters new thread with new timestamp
    		 * adds diff to previous thread
    		 */
    		if(!s.isEmpty())
    			res[s.peek()] += Integer.parseInt(arr[2]) - prev;
    		prev = Integer.parseInt(arr[2]);
    		if(arr[1].compareTo("start") == 0)
    			s.push(Integer.parseInt(arr[0]));
    		else {
    			//this is end
    			// increment prev because this is start of previous thread  
    			res[s.pop()]++;
    			prev++;
    		}
    	}
    	print(res);
    	return res;
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 2;
		List<String> logs = Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6");
		new Solution().exclusiveTime(n, logs);
	}
}