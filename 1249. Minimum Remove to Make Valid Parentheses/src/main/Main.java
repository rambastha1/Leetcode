package main;

import java.util.Arrays;

class Solution {
    public String minRemoveToMakeValid(String s) {
    	int n = s.length();
    	boolean []valid = new boolean[n];
    	Arrays.fill(valid, true);
    	int open = 0;
    	
    	for(int i = 0;i < n;i++) {
    		char c = s.charAt(i);
    		if(Character.isAlphabetic(c))
    			continue;
    		open += c =='('?1:-1;
    		if(open < 0) {
    			valid[i] = false;
    			open++;
    		}
    	}
    	
    	open = 0;
    	for(int i = n-1;i >= 0;i--) {
    		char c = s.charAt(i);
    		if(Character.isAlphabetic(c))
    			continue;
    		open += c ==')'?1:-1;
    		if(open < 0) {
    			valid[i] = false;
    			open++;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0;i < n;i++) {
    		if(valid[i])
    			sb.append(s.charAt(i));
    	}
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "lee(t(c)o)de)";
		System.out.println(new Solution().minRemoveToMakeValid(s));
	}
}
