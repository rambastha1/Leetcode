package main;
// https://leetcode.com/articles/decoded-string-at-index/
class Solution {
    public String decodeAtIndex(String S, int K) {
    	int n = S.length();
    	long size = 0;
    	for(char c : S.toCharArray()) {
    		if(Character.isDigit(c))
    			size *= c-'0';
    		else
    			size++;
    	}
    	
    	for(int i = n-1;i >= 0;i--) {
    		char c = S.charAt(i);
    		// important line
    		K %= size;
    		if(K == 0 && Character.isLetter(c))
    			return Character.toString(c);
    		if(Character.isDigit(c))
    			size /= c-'0';
    		else
    			size--;
    	}
    	throw null;
    }
}

public class Main {
	public static void main(String[] args) {
		//String S = "ha22";
		String S = "leet2code3";
		int K = 10;
		System.out.println(new Solution().decodeAtIndex(S, K));
	}
}
