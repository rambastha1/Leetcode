package main;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	if(s1 == null && s2 == null || s1.length() == 0 && s2.length() == 0)
    		return true;
    	if(s1 == null || s2 == null || s1.length() > s2.length())
    		return false;
    	int []count = new int[26];
    	for(char c : s1.toCharArray())
    		count[c-'a']++;
    	int counter = 0, start = 0, end = 0;
    	
    	/* for loop also handles the case when for some starting chars doesn't match s1
    	 */
    	for(;end < s2.length();end++) {
    		char c = s2.charAt(end);
    		if(count[c-'a'] > 0) {
    			counter++;
    			count[c-'a']--;
    			if(counter == s1.length())
    				return true;
    		} else {
    			/* handles starting chars both start and end move without going into while loop
    			 */
    			while(start <= end && s2.charAt(start) != s2.charAt(end)) {
    				count[s2.charAt(start)-'a']++;
    				counter--;
    				start++;
    			}
    			start++;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//String s1 = "ab", s2 = "eidbaooo";
		//String s1= "ab", s2 = "eidboaoo";
		//String s1= "ab", s2 = "bc";
		String s1 = "ky", s2 = "ainwkckifykxlribaypk";
		System.out.println(new Solution().checkInclusion(s1, s2));
	}
}
