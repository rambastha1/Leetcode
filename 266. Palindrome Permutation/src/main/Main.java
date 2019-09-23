package main;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/articles/palindrome-permutation/
/* every character count should be even to be placed at equal distance from center 
 * only center element is allowed to have odd count. thus count <=1
 */
class Solution {
	
	// Time 0(N) Space 0(1)
    public boolean canPermutePalindrome(String s) {
    	int []count = new int[128];
    	int odd = 0;
    	for(char c : s.toCharArray()) {
    		count[c]++;
    		if(count[c]%2==0)
    			odd--;
    		else 
    			odd++;
    	}
    	return odd<=1;
    }
    
    public boolean canPermutePalindrome1(String s) {
    	Map<Character, Integer> map = new HashMap<>();
    	int count = 0;
    	for(char c : s.toCharArray())
    		map.put(c, map.getOrDefault(c, 0)+1);
    	
    	for(int val : map.values()) 
    		count += val%2;
    	return count <= 1;
    }
}

public class Main {
	public static void main(String[] args) {
		//String s = "code";
		String s = "carerac";
		System.out.println(new Solution().canPermutePalindrome(s));
		System.out.println(new Solution().canPermutePalindrome1(s));
	}
}
