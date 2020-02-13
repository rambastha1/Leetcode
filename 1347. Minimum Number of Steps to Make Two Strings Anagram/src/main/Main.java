package main;

class Solution {
    public int minSteps(String s, String t) {
    	int n = s.length();
    	if(n == 1)
    		return s.charAt(0)==t.charAt(0)?0:1;
    	
    	int []count = new int[26];
    	for(char c : s.toCharArray())
    		count[c-'a']++;
    	
    	int prev = 0;
    	for(int i = 0;i < n;i++) {
    		char c = t.charAt(i);
    		if(count[c-'a'] > 0) {
    			count[c-'a']--;
    		} else {
    			prev++;
    		}
    	}
    	return prev;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "leetcode", t = "practice";
		System.out.println(new Solution().minSteps(s, t));
	}
}
