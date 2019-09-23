package main;
import java.util.*;

/*
 * last array stores last position of character
 * Now loop through string, keep extending 'end' of current window using values stored 
 * in last array. 
 * i keeps extending shrinking the window. when i == end, it means maximum end of all 
 * characters in "start to end" has been reached. Thus add to answer
 * And start from end +1 for next window.
 */

class Solution {
    public List<Integer> partitionLabels(String S) {
    	int []last = new int[26];
    	for(int i = 0;i < S.length();i++)
    		last[S.charAt(i) - 'a'] = i;
    	
    	int start = 0, end = 0;
    	List<Integer> ans = new ArrayList<>();
    	for(int i = 0;i < S.length();i++) {
    		char c = S.charAt(i);
    		end = Math.max(end, last[c - 'a']);
    		
    		if(i == end) {
    			ans.add(end - start + 1);
    			start = end + 1;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "ababcbacadefegdehijhklij";
		System.out.println(new Solution().partitionLabels(S));
	}
}