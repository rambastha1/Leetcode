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
        List<String> answer = new ArrayList<>();
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == '+' && arr[i+1] == '+'){
                arr[i] = '-'; arr[i+1] = '-';
                answer.add(new String(arr));
                arr[i] = '+'; arr[i+1] = '+';
            }
        }
        return answer;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "++++";
		System.out.println(new Solution().generatePossibleNextMoves(s));
	}
}