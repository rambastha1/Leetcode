package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
class Solution {
    int N, M;
    // b value -> switched value
    Map<Integer, Integer> map;
    Random random;
    public Solution(int N, int[] blacklist) {
        this.N = N;
        this.map = new HashMap<>();
        this.random = new Random();
        
        for(int b : blacklist) 
        	map.put(b, -1);
        
        this.M = N - map.size();
        for(int b : blacklist) {
        	if(b < M) {
        		while(map.containsKey(N-1))
        			N--;
        		map.put(b, N-1);
        		N--;
        	}
        }
    }
    
    public int pick() {
        int val = random.nextInt(M);
        if(map.containsKey(val))
        	return map.get(val);
        return val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */

public class Main {
	public static void main(String[] args) {
		int N = 1;
		int[] blacklist = {};
		Solution s = new Solution(N, blacklist);
		System.out.println(s.pick());
		System.out.println(s.pick());
		System.out.println(s.pick());
	}
}
