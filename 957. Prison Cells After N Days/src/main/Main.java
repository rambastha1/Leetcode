package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* Because there are at most 256 (64 actually) possible states for the prison, 
 * eventually the states repeat 
 * into a cycle rather quickly. We can keep track of when the states repeat to find the 
 * period t of this cycle, and skip days in multiples of t.
 * 
 * In the future when you see questions where you have to make a large amount of state 
 * transitions over a state space that seems small, just compare the size of the state 
 * space to the # of transitions to determine if there's a cycle so you can bound complexity 
 * to the size of the state space.
 * 
 * https://leetcode.com/problems/prison-cells-after-n-days/discuss/205684/JavaPython-Find-the-Loop-or-Mod-14
 */

class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
    	Map<String, Integer> visited = new HashMap<String, Integer>();
    	while(N > 0) {
    		int []cells2 = new int[8];
    		visited.put(Arrays.toString(cells), N--);
    		for(int i = 1;i < 7;i++) {
    			cells2[i] = cells[i-1] == cells[i+1]?1:0;
    		}
    		cells = cells2;
    		if(visited.containsKey(Arrays.toString(cells)))
    		/* visited.get(Arrays.toString(cells)) - N gives length of cycle
    		 * after each visited.get(Arrays.toString(cells)) - N this cells string will repeat
    		 */
    			N %= visited.get(Arrays.toString(cells)) - N;
    	}
    	print(cells);
    	return cells;
    }
    
    void print(int []cells) {
    	for(int i : cells) 
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int []cells = {0,1,0,1,1,0,0,1};
		int N = 7;
		new Solution().prisonAfterNDays(cells, N);
	}
}