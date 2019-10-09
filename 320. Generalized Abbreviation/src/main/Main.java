package main;
import java.util.ArrayList;
import java.util.List;

/*
 * Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 * https://leetcode.com/problems/generalized-abbreviation/discuss/77218/Java-14ms-beats-100
 */

/* For each char c[i], either abbreviate it or not.

Abbreviate: count accumulate num of abbreviating chars, but don't append it yet.
Not Abbreviate: append accumulated num as well as current char c[i].
In the end append remaining num.
Using StringBuilder can decrease 36.4% time.
This comes to the pattern I find powerful:

int len = sb.length(); // decision point
... backtracking logic ...
sb.setLength(len);     // reset to decision point
 * 
 * deleteCharAt does just that, deletes a character at index, while .setLength(len) truncates to that length. If num > 9 
 * then sb.append(num) adds two characters. "dictionary" probably falls into that category...
 * 
 */

class Solution {
    public List<String> generateAbbreviations(String word) {
    	List<String> res = new ArrayList<>();
    	dfs(word, res, new StringBuilder(), 0, 0);
    	return res;
    }
    
    void dfs(String word, List<String> res, StringBuilder sb, int index, int count) {
    	int len = sb.length();
    	if(index == word.length()) {
    		if(count != 0)
    			sb.append(count);
    		res.add(sb.toString());
    	} else {
			dfs(word, res, 	sb, index+1, count+1);
			
			if(count != 0)
				sb.append(count);
			dfs(word, res, sb.append(word.charAt(index)), index+1, 0);
    	}
		sb.setLength(len);
    }
}

public class Main {
	public static void main(String[] args) {
		String word = "word";
		System.out.println(new Solution().generateAbbreviations(word));
	}
}