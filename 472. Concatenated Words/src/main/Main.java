package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* start adding word from beginning that cannot be formed from words in set
 * else add in res
 */
class Solution {
	// Time 0(n^3)
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
    	List<String> res = new ArrayList<>();
    	if(words.length == 0)
    		return res;
    	int n = words.length;
    	Arrays.sort(words, (a,b) -> (a.length() - b.length()));
    	Set<String> set = new HashSet<>();
    	set.add(words[0]);
    	for(int i = 1;i < n;i++) {
    		if(wordbreak(set, words[i])) {
    			res.add(words[i]);
    		}
    		set.add(words[i]);
    	}
    	return res;
    }
	
	private boolean wordbreak(Set<String> set, String word) {
		int n = word.length();
		boolean []dp = new boolean[n+1];
		dp[0] = true;
		for(int j = 1;j <= n;j++) {
			for(int i = j-1;i >= 0;i--) {
				if(!dp[i])
					continue;
				String str = word.substring(i, j);
				if(dp[i] && set.contains(str)) {
					dp[j] = true;
					break;
				}
			}
		}
		return dp[n];
	}
    
}

public class Main {
	public static void main(String[] args) {
		//String []words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		String []words = {"a", "b", "ab", "abc"};
		System.out.println(new Solution().findAllConcatenatedWordsInADict(words));
	}
}
