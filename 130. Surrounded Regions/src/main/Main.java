package main;

import java.util.LinkedList;
import java.util.Queue;

/* start from boundary where board[i][j] = 'O'
 * mark every connected 'O' nodes 'B'
 * turn every other 'O' nodes to 'X'
 * turn 'B' nodes to 'O'
 */
class Solution {
    public void solve(char[][] board) {
    	if(board == null || board.length == 0 || board[0].length == 0)
    		return;
    	int m = board.length, n = board[0].length;
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	Queue<int []> q = new LinkedList<>();
    	boolean [][]visited = new boolean[m][n];
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if((i == 0 || i == m-1 || j == 0 || j == n-1) && board[i][j] == 'O') {
    				q.offer(new int [] {i,j});
    			}    				
    		}
    	}
    	// turn every boundary and connected 'O' to 'B'
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			int []node = q.poll();
    			int x = node[0];
    			int y = node[1];
    			if(visited[x][y])
    				continue;
    			visited[x][y] = true;
    			board[x][y] = 'B';
    			for(int j = 0;j < 4;j++) {
    				int X = x + dirs[j][0];
    				int Y = y + dirs[j][1];
    				if(issafe(m, n, X, Y) && board[X][Y] == 'O')
    					q.offer(new int[] {X,Y});
    			}
    		}
    	}
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(board[i][j] == 'O')
    				board[i][j] = 'X';
    		}
    	}
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(board[i][j] == 'B')
    				board[i][j] = 'O';
    		}
    	}
    	print(board);
    }
    
    private boolean issafe(int m, int n, int x, int y) {
    	return x >= 0 && x < m && y >= 0 && y < n;
    }
    
    private void print(char [][]board) {
    	for(int i = 0;i < board.length;i++) {
    		for(int j= 0;j < board[0].length;j++)
    				System.out.print(board[i][j] + " ");
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		char [][]board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
		//char [][]board = {{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
		new Solution().solve(board);
	}
}
