package main;

/* https://leetcode.com/problems/flip-string-to-monotone-increasing/discuss/189751/C%2B%2B-one-pass-DP-solution-0ms-O(n)-
or-O(1)-one-line-with-explaination.
 * Keep track of number of 1's in onesCount (Prefix). 
 * Any 0 that comes after we encounter 1 can be a potential candidate for flip. Keep track of it in flipCount.
 * If flipCount exceeds oneCount - (Prefix 1's flipped to 0's)
 * a. Then we are trying to flip more 0's (suffix) than number of 1's (prefix) we have.
 * b. Its better to flip the 1's instead.
 * 
 * 
 * Within the string s, a new incoming character, say ch, is appended to the original string. 
 * The question is that, how should counter_flip be updated, based on the sub-question? We should discuss it case by case.
 * 
 * When '1' comes, no more flip should be applied, since '1' is appended to the tail of the original string.
 * When '0' comes, things become a little bit complicated. 
 * There are two options for us: flip the newly appended '0' to '1', after counter_flip flips for the original string; 
 * or flip counter_one '1' in the original string to '0'. 
 * Hence, the result of the next step of DP, in the '0' case, is std::min(counter_flip + 1, counter_one);.
 */

//tried quick sort partition logic gave incorrect answers not all testcases passed
class Solution {
    public int minFlipsMonoIncr(String S) {
    	int ones = 0, flip = 0;
    	for(char c : S.toCharArray()) {
    		if(c == '1')
    			ones++;
    		//can write else part as flip = math.min(++flip, ones);
    		else
    			flip++;
    		flip = Math.min(ones, flip);
    	}
    	return flip;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "111011100100100";
		//String S = "00011000";
		System.out.println(new Solution().minFlipsMonoIncr(S));				
	}
}