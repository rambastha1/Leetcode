package main;

import java.util.HashMap;
import java.util.Map;

/* Tried finding sources[i] index in S and replacing it in string builder
 * Giving error mostly because replacing string with shorter string is not allowed
 * For eg sb.replace(index, sources[i].length(), targets[i]);
 * If target[i] has smaller length throws exception
 * 
 * Thus store indices and work on it
 */

// https://leetcode.com/problems/find-and-replace-in-string/discuss/134758/Java-O(n)-solution

class Solution {
	
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		if(indexes.length == 0 || sources.length == 0 || targets.length == 0 || S.length() == 0)
    		return S;
    	StringBuilder sb = new StringBuilder();
    	Map<Integer, Integer> map = new HashMap<>(); 
    	for(int i = 0;i < indexes.length;i++) {
    		if(S.startsWith(sources[i], indexes[i]))
    			map.put(indexes[i], i);
    	}
		
    	int i = 0;
    	while(i < S.length()) {
    		if(map.containsKey(i)) {
    			sb.append(targets[map.get(i)]);
    			i += sources[map.get(i)].length();
    		} else {
    			sb.append(S.charAt(i++));
    		}
    	}
		return sb.toString();
	}
	
	// Problem at end of string rest working fine
    /*public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
    	if(indexes.length == 0 || sources.length == 0 || targets.length == 0 || S.length() == 0)
    		return S;
    	StringBuilder sb = new StringBuilder(S);
    	for(int i = 0;i < indexes.length;i++) {
    		int index = S.indexOf(sources[i]);
    		int s = sb.indexOf(sources[i]);
    		if(index == -1 || s == -1 || index != indexes[i])
    			continue;
    		sb.replace(s, s + sources[i].length(), targets[i]);
    	}
    	return sb.toString();
    }*/
}

public class Main {
	public static void main(String[] args) {
		/*String S = "abcd";
		int []indexes = {0,2};
		String[] sources = {"a","cd"}, targets = {"eee","ffff"};*/
		
		/*String S = "vmokgggqzp";
		int []indexes = {3,5,1};
		String[] sources = {"kg","ggq","mo"}, targets = {"s","so","bfr"};*/

		/*String S = "jjievdtjfb";
		int []indexes = {4,6,1};
		String[] sources = {"md","tjgb","jf"}, targets = {"foe","oov","e"};*/
		
		
		String S = "wreorttvosuidhrxvmvo";
		int []indexes = {14,12,10,5,0,18};
		String[] sources = {"rxv","dh","ui","ttv","wreor","vo"}, targets = {"frs","c","ql","qpir","gwbeve","n"};
		System.out.println(new Solution().findReplaceString(S, indexes, sources, targets));
	}
}