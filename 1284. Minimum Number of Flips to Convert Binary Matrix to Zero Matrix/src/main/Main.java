package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// Google Phone Screen
// https://leetcode.com/discuss/interview-question/422725/google-phone-screen-lights-out-puzzle/397815
// https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/discuss/446342/JavaPython-3-Convert-matrix-to-int-then-BFS-w-explanation-comments-and-analysis.

// https://www.youtube.com/watch?v=oCHCD_-nhg4&t=3s
// https://math.stackexchange.com/questions/1052609/minimal-number-of-moves-needed-to-solve-a-lights-out-variant
/* state is required because one index could be fliped multiple times. Consider and index x,y
 * If it has already flipped index say (x+1,y) we should not flip it again from (x,y). This work is done by alreadyflipped
 * perform normal BFS
 * To track indices 2D index is converted into 1D index
 */

class Solution {
	
	class State {
		int [][]mat;
		int x, y;
		Set<Integer> alreadyflipped;
		public State(int [][]mat, int x,int y, Set<Integer> set) {
			this.alreadyflipped = new HashSet<>();
			this.alreadyflipped.add(x*mat.length+y);
			this.alreadyflipped.addAll(set);
			
			this.mat = new int[mat.length][mat[0].length];
			for(int i = 0;i < mat.length;i++) {
		    		for(int j = 0;j < mat[0].length;j++) {
		    			this.mat[i][j] = mat[i][j];
		    		}
			}
			this.x = x;
			this.y = y;
		}
	}
	
    public int minFlips(int[][] mat) {
	    	int m = mat.length, n= mat[0].length;
	    	if(m == 1 && n == 1)
	    		return mat[0][0];
	    	
	    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	    	Queue<State> q = new LinkedList<>();
	    	
	    	boolean allzero = true;
	    	for(int i = 0;i < m;i++) {
	    		for(int j = 0;j < n;j++) {
	    			q.offer(new State(mat, i, j, new HashSet<>()));
	    			if(mat[i][j] == 1) {
	    				allzero = false;
	    			}
	    		}
	    	}
	    	if(allzero)
	    		return 0;
	    	
	    	int ans = 0;
	    	
	    	while(!q.isEmpty()) {
	    		int size = q.size();
	    		ans++;
	    		for(int i = 0;i < size;i++) {
	    			State s = q.poll();
	    			int x = s.x, y = s.y;
	    			s.mat[x][y] ^= 1;
	    			
	    			for(int j = 0;j < 4;j++) {
	    				int X = s.x + dirs[j][0];
	    				int Y = s.y + dirs[j][1];
	    				if(issafe(m, n, X, Y)) {
	    					s.mat[X][Y] ^= 1;
	    				}
	    			}
	    			
	    			// Check after above operation matrix is 0-matrix or not from any state. If yes return
	    			// else add those indices which were not flipped from this index(s.x, s.y)
	    			allzero = true;
	    			for(int r = 0;r < s.mat.length;r++) {
		    	    		for(int c = 0;c < s.mat[r].length;c++) {
		    	    			if(s.mat[r][c] == 1) {
		    	    				allzero = false;
		    	    				if(!s.alreadyflipped.contains(r*m+c))
		    	    					q.offer(new State(s.mat, r, c, s.alreadyflipped));
		    	    			}
		    	    		}
	    			}
	    			if(allzero)
	    				return ans;
	    		}
	    	}
	    	return -1;
    }
    
    private boolean issafe(int m, int n, int x,int y) {
    	return x >= 0 && x < m && y>= 0 && y < n;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]mat = {{1,1,1},{1,0,1},{0,0,0}};
		System.out.println(new Solution().minFlips(mat));
	}
}