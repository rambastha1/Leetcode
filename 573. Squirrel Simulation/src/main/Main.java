package main;

/* https://leetcode.com/articles/squirrel-simulation/
 * 
 * There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. 
 * Your goal is to find the minimal distance for the squirrel to collect all the nuts and 
 * put them under the tree one by one. The squirrel can only take at most one nut at one time 
 * and can move in four directions - up, down, left and right, to the adjacent cell. The distance 
 * is represented by the number of moves.
 * 
 * 
 * 
 */

/*
 * we'll achieve the maximum profit by picking up a nut which is farther from the tree 
 * but closer to the squirrel, as the first nut. This is because, the only travel distance 
 * which we can save is from the tree to the nut, but to achieve this saving, 
 * we need to go from the squirrel's start position to the nut's position.
 */

class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
    	int index = 0, min = Integer.MAX_VALUE;
    	for(int i = 0;i < nuts.length;i++) {
    		int dist = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
    		if(dist < min) {
    			min = dist;
    			index = i;
    		}
    	}
    	
    	int dist = min + Math.abs(tree[0] - nuts[index][0]) + Math.abs(tree[1] - nuts[index][1]);
    	for(int i = 0;i < nuts.length;i++) {
    		if(i != index) {
    			dist += (Math.abs(tree[0] - nuts[i][0]) + Math.abs(tree[1] - nuts[i][1])) * 2;
    		}
    	}    	
    	return dist;
    }
}

public class Main {
	public static void main(String[] args) {
		int height = 5;
		int width = 7;
		int []tree = {2,2};
		int []squirrel = {4,4};
		int [][]nuts = {{3,0}, {2,5}};
		System.out.println(new Solution().minDistance(height, width, tree, squirrel, nuts));
	}
}