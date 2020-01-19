package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  
If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]
Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
 * 
 */

// https://www.youtube.com/watch?v=tJPpHxPEXX0
class Solution {
    public String[] expand(String S) {
    	List<String> list = new ArrayList<>();
    	int n = S.length();
    	// create list of string inside {}
    	for(int i = 0;i < n;i++) {
    		if(S.charAt(i) == '{') {
    			int j = i+1;
    			StringBuilder sb = new StringBuilder();
    			while(j < n && S.charAt(j) != '}') {
    				if(S.charAt(j) == ',') {
    					j++;
    					continue;
    				}
    				sb.append(S.charAt(j));
    				j++;
    			}
    			list.add(sb.toString());
    			i = j;
    		} else {
    			list.add(S.charAt(i) + "");
    		}
    	}
    	// Now the list will contain k, abcdefg
    	List<String> res = new ArrayList<>();
    	dfs(list, res, new StringBuilder(), 0);
    	
    	int size = res.size();
    	int i = 0;
    	String []result = new String[size];
    	for(String s : res) {
    		result[i++] = s;
    	}
    	Arrays.sort(result);
    	return result;
    }
    
    private void dfs(List<String> list, List<String> res, StringBuilder sb, int index) {
    	if(sb.length() == list.size()) {
    		res.add(sb.toString());
    		return;
    	}
    	/* it will add recursively say list is ab, cd, ef, gh
    	 * it will move like aceg then aceh then acfg acfh and so on
    	 * 
    	 */
    	for(char c : list.get(index).toCharArray()) {
    		sb.append(c);
    		dfs(list, res, sb, index+1);
    		// this line is important this is same as removing c after dfs
    		// since there's no way to do that set length = length -1
    		/* for eg ac.. after adding all possible permutation after ac like aceg and aceg, acfg and acfh
    		 * it needs to remove c and add d so to add adeg and so on
    		 */
    		sb.setLength(sb.length() - 1);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "k{a,b,c,d,e,f,g}";
		System.out.println(Arrays.toString(new Solution().expand(S)));
	}
}