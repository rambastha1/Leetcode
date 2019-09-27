package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
// the question asks where the last word of the first phrase is the same as the first word of the second phrase.
// https://leetcode.com/problems/before-and-after-puzzle/discuss/377131/6ms-Java-solution

class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
    	
    	// first string of phrase -> index of phrase in array to avoid using it twice
    	Map<String, List<Integer>> front = new HashMap<>();
    	// index
    	int i = 0;
    	for(String phrase : phrases) {
    		String []str = phrase.split(" ");
    		if(!front.containsKey(str[0]))
    			front.put(str[0], new ArrayList<>());
    		front.get(str[0]).add(i);
    		i++;
    	}
    	// set i back to 0
    	i = 0;
    	Set<String> set = new TreeSet<>();
    	/* check last string of every phrase
    	 * if front map contains this string means last of this matches front of some other phrases - make result string 
    	 * with all such strings
    	 */
    	for(String phrase : phrases) {
    		int index = phrase.lastIndexOf(" ");
    		String last = index >= 0?phrase.substring(index+1):phrase;
    		if(front.containsKey(last)) {
    			for(int ind : front.get(last)) {
    				// avoid making result string with itself
    				if(ind == i)
    					continue;
    				set.add(phrase + phrases[ind].substring(last.length()));
    			}
    		}
    		i++;
    	}
    	return new ArrayList<>(set);
    }
}

public class Main {
	public static void main(String[] args) {
		String []phrases = {"mission statement", "a quick bite to eat", "a chip off the old block", "chocolate bar", 
				"mission impossible", "a man on a mission", "block party", "eat my words", "bar of soap"};
		System.out.println(new Solution().beforeAndAfterPuzzles(phrases));
	}
}