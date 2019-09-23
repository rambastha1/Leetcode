package main;

class Solution {
	
	/*
	 * Manacher's Algorithm 
	 * Time 0(N) Space 0(N)
	 * https://algs4.cs.princeton.edu/53substring/Manacher.java.html
	 * https://www.youtube.com/watch?v=nbTSfrEfo6M 
	 */
	public String longestPalindrome(String s) {
		char []t = preprocess(s); 
		int []p = new int[t.length];
		int len = t.length, maxlen = 0;
		int center = 0, right = 0;
		
		for(int i = 1;i < len-1;i++) {
			int mirror = 2*center-i;
			if(i < right)
				p[i] = Math.min(p[mirror], right-i);

			while(t[i + (1+p[i])] == t[i - (1+p[i])])
				p[i]++;
			
			if(p[i] > maxlen)
				maxlen = p[i];
			
			if(i + p[i] > right) {
				center = i;
				right = i + p[i];
			}			
		}		
		return longest_string(s, p);
	}
	
	public char[] preprocess(String s) {
		char []t = new char[s.length()*2 + 3];
		StringBuilder sb = new StringBuilder();
		t[0] = '$';
		t[s.length()*2+2] = '@';
		for(int i = 0;i < s.length();i++) {
			t[2*i+1] = '#';
			t[2*i+2] = s.charAt(i);
		}
		t[s.length()*2+1] = '#';
		return t;
	}
	
	public String longest_string(String s, int []p) {
		int len = 0, center = 0;
		for(int i = 1;i < p.length-1;i++) {
			if(p[i] > len) {
				len = p[i];
				center = i;
			}
		}
		return s.substring((center-len-1)/2, (center+len-1)/2);
	}	
    
	/*
	 * 0(n^2) Time
	 * 0(1) space
	 */
	private int lo, maxLen;
	public String longestPalindrome1(String s) {
		int len = s.length();
		if (len < 2)
			return s;
		
	    for (int i = 0; i < len-1; i++) {
	     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
	     	extendPalindrome(s, i, i+1); //assume even length.
	    }
	    return s.substring(lo, lo + maxLen);
	}
	
	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}
	
	void print(String s, int low , int high) {
		System.out.println(s.substring(low, high+1));
	}
}

public class Main {
	public static void main(String[] args) {
		 String s = "forgeeksskeegfor";
		 System.out.println(new Solution().longestPalindrome(s));
	}
}