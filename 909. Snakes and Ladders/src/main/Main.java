package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int row = n - 1, col = 0, index = 0, inc = 1;
        while (index < n * n) {
            arr[index++] = board[row][col];
            if (inc == 1 && col == n - 1) {
                inc = -1;
                row--;
            } else if (inc == -1 && col == 0) {
                inc = 1;
                row--;
            } else {
                col += inc;
            }
        }
        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new LinkedList<>();
        int start = arr[0] > -1 ? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;
        int step = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0;i < size;i++) {
                int cur = q.poll();
                if (cur == n * n - 1) {
                    return step;
                }
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
                    int dest = arr[next] > -1 ? arr[next] - 1 : next;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]board = {{-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1}, 
				{-1,35,-1,-1,13,-1}, {-1,-1,-1,-1,-1,-1}, {-1,15,-1,-1,-1,-1}};
		System.out.println(new Solution().snakesAndLadders(board));
	}
}
