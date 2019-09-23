package main;

/*
 * You are playing the following Flip Game with your friend: 
 * Given a string that contains only these two characters: + and -, you and your friend take 
 * turns to flip two consecutive "++" into "--". The game ends when a person can no longer make 
 * a move and therefore the other person will be the winner. 
 * Write a function to determine if the starting player can guarantee a win.
 * 
 * For example, given s = "++++", return true. The starting player can guarantee a win by 
 * flipping the middle "++" to become "+--+".
 * 
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */

class Solution {
    /*
     * First time it will run set first two char -- sec player 3-4 to -- till ones loses 
     * similarly for index 1-2 and so on
     * if any way guarentee first player wins returns true
     */
	public boolean canWin(String s) {
		if(s == null || s.length() == 0)
			return false;
		for(int i = 0;i < s.length()-1;i++) {
			if(s.charAt(i) == '+' && s.charAt(i+1) == '+') {
				StringBuilder sb = new StringBuilder(s);
				sb.setCharAt(i, '-');
				sb.setCharAt(i+1, '-');
				if(!canWin(sb.toString()))
					return true;
			}
		}
		return false;
	}
	
	
	//My working solution similar to all dfs algorithms
	/*public boolean canWin(String s) {
    	return dfs(s, true, 0);
    }
    
    boolean dfs(String s, boolean turn, int index) {
    	if(index >= s.length()-1) {
    		if(!turn)
    			return true;
    		return false; 	
    	}
    	
    	for(int i = index;i < s.length()-1;i++) {
    		String str = s.substring(0, i) + "--" + s.substring(i + 2);
    		if(dfs(str, !turn, i+2))
    			return true;
    		s = s.substring(0, i) + "++" + s.substring(i+2);
    	}
    	return false;
    }*/
}

public class Main {
	public static void main(String[] args) {
		String s = "++++";
		System.out.println(new Solution().canWin(s));
	}
}