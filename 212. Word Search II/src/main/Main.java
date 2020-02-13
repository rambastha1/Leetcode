package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	// https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> res = new ArrayList<>();
	    TrieNode root = buildTrie(words);
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[0].length; j++) {
	            dfs (board, i, j, root, res);
	        }
	    }
	    return res;
	}

	public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
	    char c = board[i][j];
	    if (c == '#' || p.next[c - 'a'] == null) return;
	    p = p.next[c - 'a'];
	    if (p.word != null) {   // found one
	        res.add(p.word);
	        p.word = null;     // de-duplicate
	    }

	    board[i][j] = '#';
	    if (i > 0) dfs(board, i - 1, j ,p, res); 
	    if (j > 0) dfs(board, i, j - 1, p, res);
	    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
	    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
	    board[i][j] = c;
	}

	public TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    for (String w : words) {
	        TrieNode p = root;
	        for (char c : w.toCharArray()) {
	            int i = c - 'a';
	            if (p.next[i] == null) p.next[i] = new TrieNode();
	            p = p.next[i];
	       }
	       p.word = w;
	    }
	    return root;
	}

	class TrieNode {
	    TrieNode[] next = new TrieNode[26];
	    String word;
	}
	
	// Intuitive solution
    public List<String> findWords1(char[][] board, String[] words) {
    	List<String> res = new ArrayList<>();
    	Search: 
    	for(String word : words) {
    		for(int x = 0;x < board.length;x++) {
    			for(int y = 0;y < board[0].length;y++) {
    				if(board[x][y] == word.charAt(0)) {
    					if(dfs1(board, word, x, y, 0)) {
    						res.add(word);
    						continue Search;
    					}
    				}
    			}
    		}
    	}
    	return res;
    }
    
    private boolean dfs1(char [][]board, String word, int x,int y, int index) {
    	if(index == word.length())
    		return true;
    	if(x < 0 || x >= board.length || y < 0 || y >= board[0].length)
    		return false;
    	if(board[x][y] != word.charAt(index))
    		return false;
    	board[x][y] ^= 256;
    	boolean exist = dfs1(board, word, x+1, y, index+1) || dfs1(board, word, x-1, y, index + 1) ||
    			dfs1(board, word, x, y-1, index+1) || dfs1(board, word, x, y+1, index+1);
    	board[x][y] ^= 256;
    	return exist;
    }
    
}

public class Main {
	public static void main(String[] args) {
		char [][]board = {{'a'}};
		String []words = {"a"};
		System.out.println(new Solution().findWords(board, words));
	}
}
