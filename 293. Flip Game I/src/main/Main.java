package main;

import java.util.ArrayList;
import java.util.List;

/*
 * You are playing the following Flip Game with your friend: 
 * Given a string that contains only these two characters: + and -, you and your friend 
 * take turns to flip two consecutive “++” into “–”. The game ends when a person can no longer 
 * make a move and therefore the other person will be the winner.

 * Write a function to compute all possible states of the string after one valid move.
 * Example:

 * Input: s = “++++“
 * Output:
 * [
 * ”--++”,
 * "+--+",
 * "++--"
 * ]
 *
 * Note: If there is no valid move, return an empty list [].
 */

class Solution {
    public List<String> generatePossibleNextMoves(String s) {
    	List<String> res = new ArrayList<>();
    	if(s == null || s.length() <= 1)
    		return res;
    	
    	String temp = "--";
    	
    	for(int i = 0;i < s.length()-1;i++) {
    		StringBuilder sb = new StringBuilder();
    		sb.append(s.substring(0, i));
    		sb.append(temp);
    		sb.append(s.substring(i+temp.length(), s.length()));
    		res.add(sb.toString());
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "++++";
		System.out.println(new Solution().generatePossibleNextMoves(s));
	}
}