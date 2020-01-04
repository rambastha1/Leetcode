package main;
// https://leetcode.com/problems/zuma-game/discuss/97010/%22short%22-java-solution-beats-98
class Solution {
	int MAX = 6;
    public int findMinStep(String board, String hand) {
    	int []count = new int[26];
    	for(char c : hand.toCharArray())
    		count[c-'A']++;
    	MAX = dfs(board+"#", count);
    	return MAX == 6?-1:MAX;
    }
    
    private int dfs(String board, int []count) {
    	// remove all duplicates of length 3 or more
    	board = remove(board);
    	if(board.equals("#"))
    		return 0;
    	int res = MAX, need = 0;
    	for(int i = 0, j = 0;j < board.length();j++) {
    		if(board.charAt(i) == board.charAt(j))
    			continue;
    		/* for eg aabbc
    		 * for i = 0, j= 2 need = 1
    		 * if hand can satisfy remove all a's from i to j-1 and recur, get minimum
    		 */
    		need = 3 - (j- i);
    		if(count[board.charAt(i) - 'A'] >= need) {
    			count[board.charAt(i) - 'A'] -= need;
    			res = Math.min(res, need + dfs(board.substring(0, i) + board.substring(j), count));
    			count[board.charAt(i) - 'A'] += need;
    		}
    		i = j;
    	}
    	return res;
    }
    
    private String remove(String board) {
    	int n = board.length();
    	for(int i = 0, j = 0;j < n;j++) {
    		if(board.charAt(i) == board.charAt(j))
    			continue;
    		if(j-i >= 3)
    			return remove(board.substring(0, i) + board.substring(j));
    		else i = j;
    	}
    	return board;
    }
}

public class Main {
	public static void main(String[] args) {
		//String board = "WRRBBW", hand = "RB";
		String board = "WWRRBBWW", hand = "WRBRW";
		System.out.println(new Solution().findMinStep(board, hand));
	}
}
