package main;

/* Sprague-Grundy Theory
 * every player wants xor to be 0, so that he makes move and make xor = 0
 * For this question, player playing last loses thus even length check 
 */

class Solution {
    public boolean xorGame(int[] nums) {
    	int xor = 0;
    	for(int n : nums)
    		xor ^= n;
    	return xor == 0 || nums.length%2 == 0;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
