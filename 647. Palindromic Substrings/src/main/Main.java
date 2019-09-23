package main;

class Solution {
	
	/*
	 * Manacher's Algorithm
	 * arr[i] stores length of longest palindromes with center i. Say arr[i] = len
	 * it means any substring with length < len like len-2, len-4 will be palindromes
	 * substract 1 from each side. Thus count += arr[i]+1/2;  
	 */
	public int countSubstrings(String s) {
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
		int count = 0;
		for(int i : p)
			count += ((1+i)/2);
		return count;
		
	}
	
	char []preprocess(String s) {
		char []t = new char[s.length()*2+3];
		t[0] = '$';
		t[s.length()*2+2] = '@';
		for(int i = 0;i < s.length();i++) {
			t[2*i+1] = '#';
			t[2*i+2] = s.charAt(i);
		}
		t[s.length()*2+1] = '#';
		return t;
	}
	
	//Time 0(N^2)
	/*int count = 0;
	public int countSubstrings(String s) {
    	for(int i = 0;i < s.length();i++) {
    		palindrome(s, i, i);
    		palindrome(s, i, i+1);
    	}
    	return count;
    }
	
	void palindrome(String s, int i, int j) {
		while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			count++;
			i--;
			j++;
		}			
	}*/
    
}

public class Main {
	public static void main(String[] args) {
		String s = "aaa";
		Solution sol = new Solution();
		System.out.println(sol.countSubstrings(s));
	}
}