package main;

/* https://leetcode.com/problems/elimination-game/discuss/87119/JAVA%3A-Easiest-solution-O(logN)-with-explanation
 * I think one of the main points is that the "step" is the difference between two 
 * adjacent numbers in the sequence. And the author found that when the head changes, 
 * it changes to the very next one on the right.
 */

class Solution {
	
	// Time 0(lgN)
    public int lastRemaining(int n) {
    	if(n == 1) return 0;
    	boolean left = true;
    	int remain = n, head = 1,step = 1;
    	
    	while(remain > 1) {
    		if(remain%2==1 || left) 
    			head = head + step;
    		step *= 2;
    		remain /= 2;
    		left = !left;
    	}
    	
    	return head;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 9;
		System.out.println(new Solution().lastRemaining(n));
	}
}
