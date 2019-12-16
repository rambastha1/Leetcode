package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class FreqStack {
	/* Time 0(1) Space 0(N) N -> number of elements 
	 * at any moment N numbers at max can be present in bucket
	 * move maxindex at counter to pop from stack
	 * https://leetcode.com/articles/maximum-frequency-stack/
	 */
	// num -> count
	Map<Integer, Integer> map;
	Map<Integer, Stack<Integer>> bucket;
	int maxindex;
	
    public FreqStack() {
    	this.map = new HashMap<>();
    	this.bucket = new HashMap<>();
	}
    
    public void push(int x) {
    	int count = map.getOrDefault(x, 0) + 1;
    	map.put(x, count);
    	
    	maxindex = Math.max(maxindex, count);
    	if(!bucket.containsKey(count))
    		bucket.put(count, new Stack<>());
    	bucket.get(count).push(x);
    }
    
    public int pop() {
    	int ans = bucket.get(maxindex).pop();
    	if(bucket.get(maxindex).size() == 0) {
    		bucket.remove(maxindex);
    		maxindex--;
    	}
    	map.put(ans, map.get(ans) - 1);
    	return ans;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */

public class Main {
	public static void main(String[] args) {
		FreqStack f = new FreqStack();
		f.push(5);
		f.push(7);
		f.push(5);
		f.push(7);
		f.push(4);
		f.push(5);
		System.out.println(f.pop());
		System.out.println(f.pop());
		System.out.println(f.pop());
		System.out.println(f.pop());
	}
}
