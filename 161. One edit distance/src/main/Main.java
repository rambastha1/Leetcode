package main;

// Given two strings S and T, determine if they are both one edit distance apart.

class Solution {
	public boolean isOneEditDistance( String s, String t) {
		int m = s.length(), n = t.length();
		for(int i = 0;i < Math.min(m, n);i++) {
			if(s.charAt(i) != t.charAt(i)) {
				if(m==n)
					return s.substring(i+1).compareTo(t.substring(i+1)) == 0;
				else if(m < n)
					return s.substring(i).compareTo(t.substring(i+1)) == 0;
				else
					return s.substring(i+1).compareTo(t.substring(i)) == 0;
			}
		}
		// 1 because 1 edit distance
		return (int)Math.abs(m-n)==1;
	}
}


public class Main {
	public static void main(String[] args) {
		//String s = "ab", t = "acb";
		String s = "", t = "a";
		System.out.println(new Solution().isOneEditDistance(s, t));
	}
}
