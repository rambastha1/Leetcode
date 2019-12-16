package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// similar to 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
// Every board state is a node of graph, find shortest distance between two nodes
// https://leetcode.com/problems/sliding-puzzle/discuss/448083/Java-BFS-Solution-6ms
class Solution {
	
	class State {
		String str;
		int x, y;
		public State(String str, int x, int y) {
			this.str = str;
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof State) {
				State s = (State)o;
				return s.str.equals(this.str);
			}
			return false;
		}
	}
	
	int [][]arr = {{1,2,3},{4,5,0}};
	State dest = new State(makeString(arr), 1, 2);
	// using 3 in place of board.length i.e 2 because it gives error (0,1,2,2,3,4) in place of (0,1,2,3,4,5)
	public int slidingPuzzle(int[][] board) {
		Queue<State> q = new LinkedList<>();
		Set<State> set = new HashSet<>();
		for(int i = 0;i < 2;i++) {
			for(int j = 0;j < 3;j++) {
				if(board[i][j] == 0) {
					State s = new State(makeString(board), i, j);
					q.offer(s);
					set.add(s);
					break;
				}
			}
		}
		int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
		int ans = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0;i < size;i++) {
				State s = q.poll();
				if(s.equals(dest))
					return ans;
				
				for(int j = 0;j < 4;j++) {
					int x = s.x + dirs[j][0];
					int y = s.y + dirs[j][1];
					if(!issafe(x, y))
						continue;
					// double swap to get string
					swap(s, x, y);
					State temp = new State(swap(s, x, y), x, y);
					if(set.contains(temp))
						continue;
					set.add(temp);
					q.offer(temp);
				}
			}
			ans++;
		}
		return -1;
    }
	
	private String makeString(int [][]a) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < 2;i++) {
			for(int j = 0;j < 3;j++) {
				sb.append(Integer.toString(a[i][j]));
			}
		}
		return sb.toString();
	}
	
	// 3 because multiply with 2 is corner case gives error
	private String swap(State s, int x, int y) {
		int a = s.x*3+s.y;
		int b = x*3+y;
		char []str = s.str.toCharArray();
		char temp = str[a];
		str[a] = str[b];
		str[b] = temp;
		return new String(str);
	}
	
	private boolean issafe(int x,int y) {
		return x >= 0 && x < 2 && y >= 0 && y < 3;
	}
}

public class Main {
	public static void main(String[] args) {
		//int [][]board = {{1,2,3},{4,0,5}};
		//int [][]board = {{4,1,2},{5,0,3}};
		int [][]board = {{3,2,4},{1,5,0}};
		System.out.println(new Solution().slidingPuzzle(board));
	}
}
