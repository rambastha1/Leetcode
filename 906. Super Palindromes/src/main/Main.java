package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int superpalindromesInRange(String L, String R) {
    	Queue<String> q = new LinkedList<>();
    	for(int i = 1;i <= 9;i++) 
    		q.offer(String.valueOf(i));
    	Set<String> set = new HashSet<>();
    	while(!q.isEmpty()) {
    		StringBuilder s = new StringBuilder(q.poll());
    		if(ispalindrome(s.toString()) && issuper(s.toString()))
    			set.add(s.toString());
    		
    		for(int i = 0;i <= 9;i++) {
    			s.append(String.valueOf(i));
    			int low = s.toString().compareTo(L);
    			int high = s.toString().compareTo(R);
    			if(low >= 0 && high <= 0 && ispalindrome(s.toString()) && issuper(s.toString()))
    				set.add(s.toString());
    			if(high <= 0)
    				q.offer(s.toString());
    			s.setLength(s.length()-1);
    		}
    	}
    	return set.size();
    }
    
    private boolean ispalindrome(String s) {
    	int l = 0, r = s.length()-1;
    	while(l < r) {
    		if(s.charAt(l++) != s.charAt(r--))
    			return false;
    	}
    	return true;
    }
    
    private boolean issuper(String s) {
    	long val = Long.parseLong(s);
    	double sqrt = (double)Math.sqrt(val);
    	val = (long)Math.sqrt(val);
    	return (double)val == sqrt && ispalindrome(String.valueOf(sqrt));
    }
}

public class Main {
	public static void main(String[] args) {
		String L = "4", R = "1000";
		System.out.println(new Solution().superpalindromesInRange(L, R));
	}
}
