package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Union Find could work but cumbersome because its directed graph

class Solution {
	//Topological Sort
	public int[] loudAndRich(int[][] richer, int[] quiet) {
		int n = quiet.length;
		List<Integer> []poors = new ArrayList[n];
		int []outdegree = new int[n];
		for(int i = 0;i < poors.length;i++)
			poors[i] = new ArrayList<>();
    
	
		for(int []r : richer) {
			int rich = r[0];
			int poor = r[1];
			poors[rich].add(poor);
			outdegree[poor]++;
		}
		Queue<Integer> q = new LinkedList<>(); 
		for(int i = 0;i < outdegree.length;i++) {
			// the richest, outdegree is 0
			if(outdegree[i] == 0)
				q.offer(i);
		}
		
		int []res = new int[n];
		for(int i = 0;i < res.length;i++)
			res[i] = i;
		
		while(!q.isEmpty()) {
			int richest = q.poll();
			for(int poor : poors[richest]) {
				if(quiet[res[richest]] < quiet[res[poor]])
					res[poor] = res[richest];
				
				outdegree[poor]--;
				if(outdegree[poor] == 0)
					q.offer(poor);
			}
		}
		print(res);
		return res;
	}
	
	void print(int []res) {
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
		int [] quiet = {3,2,5,4,6,1,7,0};
		new Solution().loudAndRich(richer, quiet);
	}
}