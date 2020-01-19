package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, 
where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same 
both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * 
 */

/* idea is that first word will take care of 1st row and 1st column
 * second char of first word will be start for second word.. word has to start with that character only
 * similarly find for other index characters
 * however as we move from 1st row to last row all prefix need to be taken into consideration move principal diagonal
 * word need to start with this prefix till str(0, i) only
 * For eg.. wall, area, lady, lead
 * w a l l
 * a r e a
 * l e a d
 * l a d y
 * 
 * first row and col is fine
 * third row word has to start with le only..thus only lead qualify not lady
 * 
 * idea store all prefix in hashmap
 * https://leetcode.com/problems/word-squares/discuss/91348/My-Java-Solution-using-hashMap-and-backtracking-163ms
 */

// debug you'll understand
class Solution {
    public List<List<String>> wordSquares(String[] words) {
    	List<List<String>> res = new ArrayList<>();
    	if(words.length == 0)
    		return res;
    	
    	Map<String, Set<String>> map = new HashMap<>();
    	int n = words[0].length();
    	
    	// Store all prefixes
    	for(String word : words) {
    		for(int j = 0;j < word.length();j++) { 
	    		String str = word.substring(0, j + 1);
	    		if(!map.containsKey(str))
	    			map.put(str, new HashSet<>());
	    		map.get(str).add(word);
    		}
    	}
    	
    	for(int i = 0;i < words.length;i++) {
    		List<String> curr = new ArrayList<>();
    		curr.add(words[i]);
    		dfs(res, map, curr, n, 1);
    	}
    	return res;
    }
    
    private void dfs(List<List<String>> res, Map<String, Set<String>> map, List<String> curr, int n, int index) {
    	if(index == n) {
    		if(curr.size() == n)
    			res.add(new ArrayList<>(curr));
    		return;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	// this will build prefix before this principal diagonal element
    	// for eg. add l from wall and e from area to get "le"
    	for(int i = 0;i < index;i++) {
    		sb.append(curr.get(i).charAt(index));
    	}
    	
    	if(!map.containsKey(sb.toString()))
    		return;
    	
    	for(String str : map.get(sb.toString())) {
    		curr.add(str);
    		dfs(res, map, curr, n, index + 1);
    		curr.remove(curr.size()-1);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"area","lead","wall","lady","ball"};
		System.out.println(new Solution().wordSquares(words));
	}
}