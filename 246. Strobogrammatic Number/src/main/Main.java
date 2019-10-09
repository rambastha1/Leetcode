package main;

import java.util.HashMap;
import java.util.Map;

/* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */

class Solution {
    public boolean isStrobogrammatic(String num) {
    	Map<Character, Character> map = new HashMap<>();
    	map.put('0', '0');
    	map.put('1', '1');
    	map.put('8', '8');
    	map.put('6', '9');
    	map.put('9', '6');
    	
    	int l = 0, r = num.length()-1;
    	while(l <= r) {
    		if(!map.containsKey(num.charAt(l)) || map.get(num.charAt(l)) != num.charAt(r))
    			return false;
    		l++;
    		r--;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String num = "818";
		//String num = "69";
		//String num = "21";
		System.out.println(new Solution().isStrobogrammatic(num));
	}
}
