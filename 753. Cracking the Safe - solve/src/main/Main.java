package main;

import java.util.HashSet;
import java.util.Set;

/* https://en.wikipedia.org/wiki/De_Bruijn_sequence
 * De Bruijn can be constructed using Hamiltonial Cycle of length n or Euler's Cycle of length n-1
 * https://www.geeksforgeeks.org/mathematics-euler-hamiltonian-paths/
 * https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/
 * https://www.geeksforgeeks.org/de-bruijn-sequence-set-1/
 * http://stones333.blogspot.com/2013/11/find-eulerian-path-in-directed-graph.html
 * https://en.wikipedia.org/wiki/Eulerian_path
 * https://www.youtube.com/watch?v=iPLQgXUiU14&feature=emb_logo
 * 
 * De Bruijn sequence of length n containing k digits has length k^n
 * number of de bruijn sequence is equal to number of euler's cycles/circuits = k!(k^(n-1))/k^n
 * 
 * Since this question is about finding shortest string password -> it means finding a euler's cycle. Use Hierholzers Algorithm
 * Use this link comment of self_learner
 * https://leetcode.com/problems/cracking-the-safe/discuss/110264/Easy-DFS
 * 
 * Below is DFS solution
 * find distinct string of length k^n
 * 
 * This shortest contains all permutations of k digits
 * for eg n= 2, k = 2
 * k = 2 means 0 and 1 -> 00,01,10,11
 * 00110 contains 00, 01, 10, 11 
 * since n-1 nodes/ edges are visited just once for shortest string it is euler cycle
 * length = k^n + (n-1)
 */

class Solution {
	// Time Complexity 0(k^n)
    public String crackSafe(int n, int k) {
    	StringBuilder sb = new StringBuilder();
    	Set<String> visited = new HashSet<>();
    	
    	int total = (int)Math.pow(k, n);
    	// create string "000...n times"
    	for(int i = 0;i < n;i++)
    		sb.append("0");
    	visited.add(sb.toString());
    	dfs(sb, total, visited, n, k);
    	return sb.toString();
    }
    
    private boolean dfs(StringBuilder sb, int total, Set<String> visited, int n, int k) {
    	if(visited.size() == total)
    		return true;
    	/* sb.length will keep increasing thus prev length will increase will add different k 
    	 * when it contains all permutation of n,k return true
    	 */
    	String prev = sb.substring(sb.length() - n + 1);
    	for(int i = 0;i < k;i++) {
    		String str = prev + i;
    		if(visited.contains(str))
    			continue;
    		visited.add(str);
    		sb.append(String.valueOf(i));
    		if(dfs(sb, total, visited, n, k))
    			return true;
    		visited.remove(str);
    		sb.setLength(sb.length()-1);
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 2, k = 2;
		System.out.println(new Solution().crackSafe(n, k));
	}
}
