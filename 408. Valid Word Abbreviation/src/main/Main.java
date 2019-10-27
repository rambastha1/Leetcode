package main;

/* Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a 
valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
 * 
 */

/*
 * loop through abbr and skip those many chars in word
 */

class Solution {
	// Time 0(M)
    public boolean validWordAbbreviation(String word, String abbr) {
    	int m = word.length(), n = abbr.length();
    	int i = 0, j = 0;
    	while(i < m && j < n) {
    		if(word.charAt(i) == abbr.charAt(j)) {
    			i++;
    			j++;
    			continue;
    		}
    		
    		if(abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
    			return false;
    		}
    		
    		int k = j, num = 0;
    		while(k < abbr.length() && Character.isDigit(abbr.charAt(k))) {
    			num = num*10 + abbr.charAt(k) - '0';
    			k++;
    		}
    		i += num;
    		j = k;
    	}
        return i == m && j == n;
    }
}

public class Main {
	public static void main(String[] args) {
		String word = "internationalization", abbr = "i5a11o1";
		System.out.println(new Solution().validWordAbbreviation(word, abbr));
	}
}