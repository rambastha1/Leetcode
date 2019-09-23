package main;

// https://leetcode.com/problems/maximum-product-of-word-lengths/discuss/76959/JAVA-Easy-Version-To-Understand!!!!!!!!!!!!!!!!!

/*
 * Now int is 32 bits. In this question only lower case alphabets are used means 26
 * thus lower 26 bits can be used to represent words[i]
 * For eg w - a = 23 thus 23rd from right bit will be set
 * taking bit OR tracks all bits that are set. In other words all characters in the words[i]
 * 
 * If words[i] and words[j] contains totally different characters, bitwise AND will be 0.
 */

class Solution {
    public int maxProduct(String[] words) {
    	if(words == null || words.length == 0)
    		return 0;
    	int []bits = new int[words.length];
    	
    	for(int i = 0;i < words.length;i++) {
    		String word = words[i];
    		for(int j = 0;j < word.length();j++) {
    			bits[i] |= 1 << (word.charAt(j) - 'a');
    		}
    	}
    	
    	/*for(int i : bits) {
    		System.out.println(i + "\t" + Integer.toBinaryString(i));
    	}*/
    	
    	int max = Integer.MIN_VALUE;
    	for(int i = 0;i < words.length;i++) {
    		for(int j = i+1;j < words.length;j++) {
    			if((bits[i] & bits[j]) == 0)
    				max = Math.max(max, words[i].length() * words[j].length());
    		}
    	}    	
    	return max;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"abcw","baz","foo","bar","xtfn","abcdef"};
		System.out.println(new Solution().maxProduct(words));
	}
}