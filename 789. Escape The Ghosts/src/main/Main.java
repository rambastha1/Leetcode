package main;

/*
 * https://leetcode.com/problems/escape-the-ghosts/discuss/116678/Why-interception-in-the-middle-is-not-a-good-idea-for-ghosts.
 *  
 */
class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
    	int max = Math.abs(target[0]) + Math.abs(target[1]);
    	for(int []ghost : ghosts) {
    		int d = Math.abs(target[0] - ghost[0]) + Math.abs(target[1] - ghost[1]);
    		if(d < max)
    			return false;
    	}    	
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][] ghosts = {{1, 0}, {0, 3}};
		int []target = {0, 1};
		System.out.println(new Solution().escapeGhosts(ghosts, target));
	}
}