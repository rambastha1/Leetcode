package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* It is easy to see that the root of an MHT has to be the middle point (or two middle points) of the longest path of the tree.
 * thus there can be at most 2 roots
 * https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
 */
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	List<Integer> res = new ArrayList<>();
    	if(n <= 0)
    		return res;
    	
    	if(n == 1) {
    		res.add(0);
    		return res;
    	}
    	
    	List<Integer> []graph = new ArrayList[n];
    	int []indegree = new int[n];
    	for(int i = 0;i < n;i++)
    		graph[i] = new ArrayList<>();
    	
    	for(int []e : edges) {
    		graph[e[0]].add(e[1]);
    		graph[e[1]].add(e[0]);
    	}
    	
    	int count = n;
    	Queue<Integer> leaves = new LinkedList<>();
    	for(int i = 0;i < n;i++) {
    		indegree[i] = graph[i].size();
    		if(indegree[i] == 1)
    			leaves.offer(i);
    	}
    	
    	while(count > 2) {
    		int size = leaves.size();
    		count -= size;
    		
    		for(int i = 0;i < size;i++) {
    			int src = leaves.poll();
    			for(int dest : graph[src]) {
    				indegree[dest]--;
    				if(indegree[dest] == 1)
    					leaves.offer(dest);
    			}
    		}
    	}
    	res.addAll(leaves);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int n = 4;
		int [][]edges = {{1, 0}, {1, 2}, {1, 3}};*/
		int n = 6;
		int [][]edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		System.out.println(new Solution().findMinHeightTrees(n, edges));
	}
}
