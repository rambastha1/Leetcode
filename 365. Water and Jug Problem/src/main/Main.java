package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution
class Solution {
	
	// GCD z should be multiple of GCD of x and y
	public boolean canMeasureWater(int x, int y, int z) {
		if(x+y < z)
			return false;
		if(x == z || y == z || x+y == z)
			return true;
		return z%gcd(x, y) == 0;
	}
	
	private int gcd(int a, int b) {
		while(b != 0) {
			int temp = b;
			b = a%b;
			a = temp;
		}
		return a;
	}
	
	
	// Intuitive BFS
    public boolean canMeasureWater1(int x, int y, int z) {
    	if(x > y) {
    		int temp = x;
    		x = y;
    		y = temp;
    	}
    	
    	if(z > x+y)
    		return false;
    	
    	Queue<int[]> q = new LinkedList<>();
    	Set<int[]> visited = new HashSet<>();
    	q.offer(new int[] {0,0});
    	visited.add(new int[] {0,0});
    	
    	while(!q.isEmpty()) {
    		int []arr = q.poll();
    		int a = arr[0], b = arr[1];
    		if(a+b==z)
    			return true;

    		Set<int[]> states = new HashSet<>();
    		states.add(new int[] {x,b});
    		states.add(new int[] {a,y});
    		states.add(new int[] {0,b});
    		states.add(new int[] {a,0});
    		// pour y to x
    		states.add(new int[] {Math.min(x, a+b), (b<x-a)?0:b-(x-a)});
    		// pour x to y
    		states.add(new int[] {(a<y-b)?0:a-(y-b), Math.min(a+b, y)});
    		
    		for(int []state : states) {
    			int s1 = state[0], s2 = state[1];
    			boolean found = false;
    			for(int []v : visited) {
    				if(s1 == v[0] && s2 == v[1]) {
    					found = true;
    					break;
    				}
    			}
    			if(!found) {
    				q.offer(new int[] {s1,s2});
    				visited.add(new int[] {s1,s2});
    			}
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//int x = 3, y = 5, z = 4;
		int x = 2, y = 6, z = 5;
		System.out.println(new Solution().canMeasureWater(x, y, z));
	}
}
