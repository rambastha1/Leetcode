package main;

/* https://leetcode.com/articles/4-keys-keyboard/
 * Imagine you have a special keyboard with the following keys:
 * 
 * Key 1: (A): Print one 'A' on screen.
 * 
 * Key 2: (Ctrl-A): Select the whole screen.
 * 
 * Key 3: (Ctrl-C): Copy selection to buffer.
 * 
 * Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
 * 
 * Now, you can only press the keyboard for N times (with the above four keys), 
 * find out the maximum numbers of 'A' you can print on screen.

 * Example 1:
 * Input: N = 3
 * Output: 3
 * Explanation: 
 * We can at most get 3 A's on screen by pressing following key sequence:
 * A, A, A
 * 
 * Example 2:
 * Input: N = 7
 * Output: 9
 * Explanation: 
 * We can at most get 9 A's on screen by pressing following key sequence:
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 * 
 * Note:
 * 1 <= N <= 50
 * Answers will be in the range of 32-bit signed integer.
 */


/*
 * Use DP. First initialize DP[ i ] to i, which means that the copy and paste operation is not used. 
 * Then start j from 3 to i-1, starting with 3 to ensure at least one copy-and-paste operation. 
 * Then loop: "After all the characters in dp[1], all the paste operations are "all until the paste operation 
 * is based on all the characters in dp[i-3]." For example: A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V 
 * where n = 7 , we used 3 (j=4, i-j=7-4) steps to get AAA. Then we use 4 steps: Ctrl A, Ctrl C, Ctrl V, Ctrl V, 
 * to get j - 1 = 4 - 1 = 3 copies of AAA (i.e total 9 A's). 
 * We either do not use the copy operation at all, that is, we initialize DP[i] to i. 
 * Either use the copy operation, so we have to leave 3 steps for Ctrl A, Ctrl C, Ctrl V , so j is at least 3. 
 * Get the formula dp[ i ] = max ( dp[ i ] , dp[ i - j ] * ( j - 1 ) ) { j in [ 3, i ) }
 * 
 * This is window of j = 4 to i = 7
 */

class Solution {	
	
	/* Understand first
	 * This question gives us four operations, namely, print A, select all, copy, paste. 
	 * Each operation counts as a step, giving us a number N, asking us to output more than one A for N operations. 
	 * We can analyze the examples in the title to find that N steps can print at least N A, 
	 * because we can print A every step. Then the situation that can exceed N is definitely the use of copy and 
	 * paste. Here, since all the selection and copying take two steps, 
	 * the operation of increasing the number of A is actually only N-2 steps, then how do we determine to print 
	 * a few A, left? The next is paste, in fact, it is a trade off, A print too much or too little, 
	 * will not get the maximum result, so the number of print A and paste is close, the easiest way is 
	 * to traverse all the cases and then take the maximum The number of times A is printed is between [1, N-3], 
	 * the number of pasting is N-2-i, and the printed part is N-1-i.
	 */
	
	//DP 0(N^2) Space 0(N)
    public int maxA(int N) {
    	int []dp = new int[N+1];
    	for(int i = 1;i <= N;i++) {
    		dp[i] = i;
    		for(int j = 3;j < i;j++) {
    			dp[i] = Math.max(dp[i], dp[i - j]*(j-1)); 
    		}
    	}    	
    	return dp[N];
    }
	
	/*public int maxA(int N) {
		int res = N;
    	for(int i = 1;i < N - 2;i++) {
    		res = Math.max(res, maxA(i)* (N-i-1));
    	}
    	return res;
	}*/
}

public class Main {
	public static void main(String[] args) {
		int N = 7;
		System.out.println(new Solution().maxA(N));
	}
}