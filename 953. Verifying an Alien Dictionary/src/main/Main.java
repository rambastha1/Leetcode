package main;

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
    	int []arr = new int[26];
    	for(int i = 0;i < order.length();i++) {
    		char c = order.charAt(i);
    		arr[c-'a'] = i;
    	}
    	int n = words.length;
    	for(int i = 0;i < n-1;i++) {
    		if(!isvalid(words[i], words[i+1], arr))
    			return false;
    		
    	}
    	return true;
    }
    
    private boolean isvalid(String str1, String str2, int []arr) {
    	int i = 0;
    	for(;i < Math.min(str1.length(), str2.length());i++) {
    		char c = str1.charAt(i), d = str2.charAt(i);
    		if(c == d)
    			continue;
    		
    		if(arr[c-'a'] > arr[d-'a'])
    			return false;
    		else 
    			return true;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"hello","leetcode"};
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		System.out.println(new Solution().isAlienSorted(words, order));
	}
}