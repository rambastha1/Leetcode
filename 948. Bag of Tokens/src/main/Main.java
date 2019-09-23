package main;

import java.util.Arrays;

/* https://leetcode.com/problems/bag-of-tokens/discuss/197856/Bad-descriptions!
 * You have a bag of tokens, from which you can take whichever token you want, and after you take one, 
 * you can't put it back to the bag, meaning you can use every token at most once.
 * 
 * You start the game with P power and 0 point.
 * 
 * For every tokens[i], you can use it in either way:
 * - plus tokens[i] powers, and minus 1 point;
 * - or, minus tokens[i] powers, and plus 1 point.
 * (meaning you exchange your powers to get 1 point, or exchange your point to get more powers)
 * 
 * But you have to make sure that during the process, both your powers>=0 and points>=0, 
 * otherwise you would have to stop playing the game.
 * 
 * And you can use just some of the tokens (don't have to use all of them).
 * 
 * Your target is to get the maximum points possible.
 */

class Solution {
	
	/* solution like meeting rooms II
	 * when have power, exchange it with lowest tokens[i]
	 * when exchanging point with token, exchange with highest token
	 */
	
    public int bagOfTokensScore(int[] tokens, int P) {
    	if(P == 0 || tokens.length == 0)
    		return 0;
    	Arrays.sort(tokens);
    	if(P < tokens[0])
    		return 0;
    	int points = 0, active = 0;
    	
    	int l = 0, r = tokens.length -1;
    	while(l <= r) {
    		if(P <= 0 && active == 0)
    			break;
    		if(P >= tokens[l]) {
    			P -= tokens[l++];
    			active++;
    		} else {
    			if(active > 0)
    				P += tokens[r--];
    			active--;
    		}
    		points = Math.max(points, active);
    	}
    	return points;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []tokens = {100};
		int P = 50;*/
		
		int []tokens = {100,200,300,400};
		int P = 200;
		System.out.println(new Solution().bagOfTokensScore(tokens, P));
	}
}
