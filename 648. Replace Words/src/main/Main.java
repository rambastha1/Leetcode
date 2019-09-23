package main;

import java.util.Arrays;
import java.util.List;

class Solution {
	
	class TrieNode {
		TrieNode []children;
		boolean isend;
		//Store word at the end
		String word;
		public TrieNode() {
			children = new TrieNode[26];
			for(int i = 0;i < children.length;i++)
				children[i] = null;
		}
	}
	
	//Using Trie
	public String replaceWords(List<String> dict, String sentence) {
		TrieNode trie = new TrieNode();
		for(String root : dict) {
			TrieNode temp = trie;
			for(char c : root.toCharArray()) {
				if(temp.children[c-'a'] == null)
					temp.children[c-'a'] = new TrieNode();
				temp = temp.children[c-'a'];
			}
			temp.word = root;
		}
		StringBuilder sb = new StringBuilder();
		String []str = sentence.split(" ");
		for(String word : str) {
			if(sb.length() > 0)
				sb.append(" ");
			TrieNode node = trie;
			for(char c : word.toCharArray()) {
				/*
				 * if there is root node.word != null holds true line 30 insertion of root
				 */
				if(node.children[c-'a'] == null || node.word != null)
					break;
				node = node.children[c-'a'];
			}
			sb.append((node.word != null)?node.word:word);			
		}
		return sb.toString();
	}
	
	
	//My working solution
    /*public String replaceWords(List<String> dict, String sentence) {
    	if(sentence.length() <= 1)
    		return sentence;
    	String []str = sentence.split(" ");
    	for(int x = 0;x < str.length;x++) {
    		String s = str[x];
    		int min = Integer.MAX_VALUE;
    		String ans = s;
    		for(int i = 0;i < dict.size();i++) {
    			String temp = dict.get(i);
    			if(s.contains(temp)) {
    				if(temp.length() < min) {
    					min = temp.length();
    					ans = temp;
    				}
    			}
    		}
    		str[x] = ans;
    		
    		
    		
    		boolean []dp = new boolean[s.length() + 1];
    		dp[0] = true;
    		
    		
    		for(int i = 0;i <= s.length();i++) {
    			for(int j = i-1;j >= 0;j--) {
    				String temp = s.substring(j, i);
    				if(dp[j] && dict.contains(temp)) {
    					dp[i] = true;
    					if(temp.length() < min) {
    						min = temp.length();
    						ans =  temp;
    					}
    				}
    			}
    		}
    		str[x] = ans;
    	}    	
    	
    	StringBuilder sb = new StringBuilder();
    	for(String s : str) {
    		sb.append(s + " ");
    	}
    	sb.deleteCharAt(sb.length()-1);
    	return sb.toString();
    }*/
}

public class Main {
	public static void main(String[] args) {
		List<String> dict = Arrays.asList("cat", "bat", "rat");
		String sentence = "the cattle was rattled by the battery";
		System.out.println(new Solution().replaceWords(dict, sentence));
	}
}