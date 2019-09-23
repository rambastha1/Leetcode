package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 * For example,
 * Given n = 2, return [“11″,”69″,”88″,”96”].
 * 
 * Hint:
 * 
 * Try to use recursion and notice that it should recurse with n – 2 instead of n – 1.
 */

class Solution {
	// Time 0(n) n -> length of digits
	/*
	 * It adds digits at start and end
	 */
	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<>();
    	
		int start = 1;
    	if(n%2==0) {
    		res.add("");
    	} else {
    		res.add("0");
    		res.add("1");
    		res.add("8");
    		start = 2;
    	}
    	
    	for(int i = start;i <= n;i += 2) {
    		List<String> curr = new ArrayList<>();
    		for(String s : res) {
    			if(i != n-1)
    				curr.add("0" + s + "0");
    			curr.add("1" + s + "1");
    			curr.add("8" + s + "8");
    			curr.add("6" + s + "9");
    			curr.add("9" + s + "6");
    		}
    		res = curr;
    	}    	
    	return res;
	}
	
	// Time 0(N)
    public List<String> findStrobogrammatic1(int n) {
    	List<String> res = new ArrayList<>();
    	Map<Character, Character> map = new HashMap<>();
    	map.put('0', '0');
    	map.put('1', '1');
    	map.put('8', '8');
    	map.put('6', '9');
    	map.put('9', '6');
    	for(int i = (int)Math.pow(10, n-1);i < (int)Math.pow(10, n);i++) {
    		char digit = Character.forDigit(i%10, 10);
    		if(map.containsKey(digit))
    			if(isstrobogrammatic(i, map))
    				res.add(String.valueOf(i));
    	}
    	return res;
    }
    
    boolean isstrobogrammatic(int n, Map<Character, Character> map) {
    	String str = String.valueOf(n);
    	
    	int l = 0, r = str.length()-1;
    	while(l < r) {
    		char a = str.charAt(l), b = str.charAt(r);
    		if(map.containsKey(a) && map.containsKey(b) && map.get(a) == b) {
    			l++;r--;
    		} else 
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 5;
		System.out.println(new Solution().findStrobogrammatic(n));
	}
}
