package main;
import java.util.ArrayList;
import java.util.List;

/*
 * Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

class Solution {
    public List<String> generateAbbreviations(String word) {
    	List<String> res = new ArrayList<>();
    	res.add(word);
    	dfs(word, res, 0);
    	return res;
    }
    
    void dfs(String word, List<String> res, int index) {
    	if(index > word.length())
    		return;
    	
    	for(int i = index;i < word.length();i++) {
    		for(int j = 1;i+j <= word.length();j++) {
    			//Find number
    			String num = String.valueOf(j);
    			//get whole string by replacing
    			String abbr = word.substring(0, i) + num + word.substring(i+j);
    			if(!res.contains(abbr))
    				res.add(abbr);
    			//dfs after number length i is included thus i+1 as well
    			dfs(word, res, i+1+num.length());
    		}
    	}    	
    }
    
}

public class Main {
	public static void main(String[] args) {
		String word = "word";
		System.out.println(new Solution().generateAbbreviations(word));
	}
}