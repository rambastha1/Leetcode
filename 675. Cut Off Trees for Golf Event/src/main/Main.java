package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* https://leetcode.com/problems/cut-off-trees-for-golf-event/discuss/107404/Java-solution-PriorityQueue-%2B-BFS
 * the question is to cut off trees in ascending order of heights 
 * stores each tree as node in array list and sort
 * question changes to find shortest path of one node to next greater node -> do bfs
 * keep adding steps and changing previous src
 */
class Solution {
	// time 0(m^2 * n^2)
	class Node {
		int x, y , h;
		public Node(int x,int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	
	public int cutOffTree(List<List<Integer>> forest) {
		
		if(forest.get(0).get(0) == 0)
			return -1;
		List<Node> trees = new ArrayList<>();
		int index = 0, m = forest.size(), n = forest.get(0).size();
		for(int i = 0;i < m;i++) {
			List<Integer> list = forest.get(i);
			for(int j = 0;j < n;j++) {
				if(list.get(j) > 1) {
					trees.add(new Node(i, j, list.get(j)));
				}
			}
		}
		
		Collections.sort(trees, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.h - o2.h;
			}
		});
		
		int []src = new int[2];
		int ans = 0;
		// 0(m*n)
		for(Node node : trees) {
			int x = node.x, y = node.y;
			// 0(m*n)
			int step = bfs(forest, src, new int[] {x,y}, m, n);
			if(step < 0)
				return -1;
			ans += step;
			src[0] = x;
			src[1] = y;
		}
		return ans;
    }
	
	private int bfs(List<List<Integer>> forest, int []src, int []dest, int m, int n) {
		int step = 0;
		boolean [][]visited = new boolean[m][n];
		Queue<int[]> q = new LinkedList<>();
		q.offer(src);
		visited[src[0]][src[1]] = true;
		int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0;i < size;i++) {
				int []node = q.poll();
				if(node[0] == dest[0] && node[1] == dest[1])
					return step;
				for(int j = 0;j < 4;j++) {
					int X = node[0] + dirs[j][0];
					int Y = node[1] + dirs[j][1];
					if(issafe(forest, X, Y, visited)) {
						visited[X][Y] = true;
						q.offer(new int[] {X,Y});
					}
				}
			}
			step++;
		}
		return -1;
	}
	
	private boolean issafe(List<List<Integer>> forest, int x, int y, boolean [][]visted) {
		return x >= 0 && x < forest.size() && y >= 0 && y < forest.get(0).size() && forest.get(x).get(y) != 0 
				&& !visted[x][y];
	}
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> forest = Arrays.asList(Arrays.asList(2,3,4), Arrays.asList(0,0,5), Arrays.asList(8,7,6));
		System.out.println(new Solution().cutOffTree(forest));
	}
}
