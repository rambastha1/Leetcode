package main;

// https://leetcode.com/problems/scramble-string/discuss/29387/Accepted-Java-solution
class Solution {
	public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true; 
        
        int[] letters = new int[26];
        for (int i=0; i<s1.length(); i++) {
            letters[s1.charAt(i)-'a']++;
            letters[s2.charAt(i)-'a']--;
        }
        for (int i=0; i<26; i++) { 
        	if (letters[i]!=0) 
        		return false;
        }
    
        // check normal string then swapped string
        for (int i=1; i<s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))) 
            	return true;
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))  && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) 
            	return true;
        }
        return false;
    }
}

public class Main {
	public static void main(String[] args) {
		String s1 = "great", s2 = "rgeat";
		System.out.println(new Solution().isScramble(s1, s2));
	}
}
