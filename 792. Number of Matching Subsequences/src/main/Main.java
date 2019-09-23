package main;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

// https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation

class Solution {
    
	// Time 0(S.length)
	/* similar to merging k sorted arrays
	 * traverse each word in words in parallel
	 */
	public int numMatchingSubseq(String S, String[] words) {
		// can be 256 for extended ASCII 
		List<Pair<Integer, Integer>>[] waiting = new ArrayList[128];
		
		for(int i = 0;i < waiting.length;i++)
			waiting[i] = new ArrayList<>();
		
		for(int i = 0;i < words.length;i++) {
			waiting[words[i].charAt(0)].add(new Pair<>(i, 1));
		}
		
		for(char c : S.toCharArray()) {
			List<Pair<Integer, Integer>> advance = new ArrayList<>(waiting[c]);
			waiting[c].clear();
			
			for(Pair<Integer, Integer> pair : advance) {
				int wordnum = pair.getKey();
				int index = pair.getValue();
				
				if(index < words[wordnum].length()) {
					waiting[words[wordnum].charAt(index)].add(new Pair<>(wordnum, index+1));
					index++;
				} else 
					waiting[0].add(pair);
			}
		}
		return waiting[0].size();
	}
	
	
	// Time 0 (max words.length * S.length)
	public int numMatchingSubseq1(String S, String[] words) {
    	if(S == null || S.length() == 0 || words == null || words.length == 0)
    		return 0;
    	int ans = 0;
    	int []count = new int[26];
    	for(char c : S.toCharArray())
    		count[c-'a']++;
    	
    	for(int i = 0;i < words.length;i++) {
    		int []temp = new int[26];
    		for(char c : words[i].toCharArray())
    			temp[c-'a']++;
    		if(issubsequence(count, temp))
    			ans++;
    	}
    	return ans;
    }
    
    boolean issubsequence(int []count, int [] temp) {
    	for(int i = 0;i < 26;i++) {
    		if(temp[i] == 0) continue;
    		if(temp[i] > count[i]) return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "abcde";
		String []words = {"a", "bb", "acd", "ace"};
		System.out.println(new Solution().numMatchingSubseq(S, words));
	}
}
