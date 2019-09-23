package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)

class Solution {
    public List<List<String>> partition(String s) {
    	List<List<String>> res = new ArrayList<>();
    	ArrayList<String> curr = new ArrayList<>();
    	backtrack(res, curr, s, 0);
    	return res;
    }
    
    public void backtrack(List<List<String>> res, ArrayList<String> curr, String s, int l) {
    	if(l == s.length()) {
    		//use print to just print the palindromes
    		res.add(new ArrayList<String>(curr));
    	}
    	for(int i = l;i < s.length();i++) {
    		if(ispalindrome(s, l, i)) {
    			//Add
    			curr.add(s.substring(l, i+1));
    			//Check Next
    			backtrack(res, curr, s, i+1);
    			//Backtrack
    			curr.remove(curr.size()-1);
    		}
    	}
    	
    }
    
    public boolean ispalindrome(String s, int l, int r) {
    	if(l == r)
    		return true;
    	while(l < r) {
    		if(s.charAt(l) != s.charAt(r))
    			return false;
    		l++;
    		r--;
    	}
    	return true;
    }
    
    /*public void partition1(String s) {
    	if(s == null || s.length() == 0)
    		return;
    	if(s.length() == 1) {
    		System.out.println(s);
    		return;
    	}
    	List<String> set = new ArrayList();
    	for(int i = 0;i < s.length();i++) {
    		palindromeutil(s, i, i, set);
    		palindromeutil(s, i, i+1, set);
    	}
    	if(set.size() != 0)
    		System.out.println(set);
    		
    }
    
    public void palindromeutil(String s, int i, int j,List<String> set) {
    	while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
    		set.add(s.substring(i, j+1));
    		i--;
    		j++;
    	}
    }*/
}

public class Main {
	public static void main(String[] args) {
		String s = "aabc";
		System.out.println(new Solution().partition(s));
		//new Solution().partition1(s);
	}
}