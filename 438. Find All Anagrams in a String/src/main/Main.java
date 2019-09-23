package main;
import java.util.*;

class Solution {
	/*
	 * Sliding Window Solution
	 * Time 0(N)
	 */
	
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> sol=new ArrayList<>();
        int start = 0,end = 0,counter = 0;
        int[] pchars=new int[26];
        for(char c:p.toCharArray()){
            pchars[c-'a']++;
        }
        
        /* Keep increasing the counter when encounter character present in pattern
         * If counter equals pattern length means anagram 
         * else keep shrinking window
         * 
         */
        char[] schar=s.toCharArray();
        while(end<s.length()) {
        	/*
        	 * If char is there, extend window and increment counter
        	 */
            if(pchars[schar[end]-'a']>0){
                pchars[schar[end]-'a']--;
                end++;
                counter++;
            }else{
            	/*
            	 * If not shrink window by incrementing start
            	 * decrement counter as well
            	 * notice schar[start]-'a' is added -> it is to find next index 
            	 */
                pchars[schar[start]-'a']++;
                start++;
                counter--;
            }
            
            //if counter equals pattern length anagram
            if(counter==p.length()){
                sol.add(start);
            }
        }
        return sol;
    }
}

public class Main {
	public static void main(String []args) {
		String s = "cbaebabacd";
		String p = "abc";
		System.out.println(new Solution().findAnagrams(s, p));
	}
}
