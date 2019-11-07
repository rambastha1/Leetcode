package main;

import java.util.LinkedList;
import java.util.Queue;

/* An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
 * The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. 
 * Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that 
 * encloses all black pixels.

Example:

Input:
[
  "0010","0110","0100"
]
and x = 0, y = 2

Output: 6
 * 
 */

/* Suppose we have a 2D array

"000000111000000"
"000000101000000"
"000000101100000"
"000001100100000"

Imagine we project the 2D array to the bottom axis with the rule "if a column has any black pixel it's projection is 
black otherwise white". The projected 1D array is

"000001111100000"

Theorem

If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.

Proof by contradiction

Assume to the contrary that there are disconnected black pixels at i
and j where i < j in the 1D projection array. Thus there exists one
column k, k in (i, j) and and the column k in the 2D array has no
black pixel. Therefore in the 2D array there exists at least 2 black
pixel regions separated by column k which contradicting the condition
of "only one black pixel region".

Therefore we conclude that all the black pixels in the 1D projection
array is connected.

This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.

To find the left boundary, do the binary search in the [0, y) range and find the first column vector who has any black pixel.

To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)

We can do the same for the other boundaries. The area is then calculated by the boundaries.
Thus the algorithm runs in O(m log n + n log m)

like finding first and last position of 1's in 2D matrix

top = search row [0...x], find first row contain 1,
bottom = search row[x+1, row], find first row contian all 0
left = search col[0...y], find first col contain 1,
right = search col[y+1, col], find first col contain all 0
 * 
 */

class Solution {
	public int minArea(char[][] image, int x, int y) {
		int m = image.length, n = image[0].length;
		int top = search(image, 0, x, 0, n, true, true);
	    int bottom = search(image, x + 1, m, 0, n, false, true);
	    int left = search(image, 0, y, top, bottom, true, false);
	    int right = search(image, y + 1, n, top, bottom, false, false);
		return (bottom-top)*(right-left);
	}
	
	private int search(char [][]image, int l, int r, int start, int end, boolean findone, boolean isrow) {
		while(l != r) {
			int k = start, mid = l + (r-l)/2;
			while(k < end && iswhite(image, mid, k, isrow))
				k++;
			// if we want to find first row containing one, we need to move more up or towards low int 1D array
			if((k < end) == findone)
				r = mid;
			else
				l = mid+1;
		}
		return l;
	}
	
	private boolean iswhite(char [][]image, int mid, int k, boolean isrow) {
		return (isrow?image[mid][k]:image[k][mid]) == '0';
	}
	
	// Time 0(M*N)
    public int minArea1(char[][] image, int x, int y) {
    	int m = image.length, n = image[0].length;
    	int Xmin = m-1, Xmax = 0;
    	int Ymin = n-1, Ymax = 0;
    	
    	Queue<int[]> q = new LinkedList<>();
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	q.offer(new int[] {x,y});
    	image[x][y] = '2';
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			int []node = q.poll();
    			int xx = node[0], yy = node[1];
    			Xmin = Math.min(Xmin, node[0]);
    	    	Xmax = Math.max(Xmax, node[0]);
    	    	Ymin = Math.min(Ymin, node[1]);
    	    	Ymax = Math.max(Ymax, node[1]);
    	    	
    	    	for(int []dir : dirs) {
    	    		int X = xx + dir[0];
    	    		int Y = yy + dir[1];
    	    		if(issafe(image, X, Y)) {
    	    			q.offer(new int[] {X, Y});
    	    			image[X][Y] = '2';
    	    		}
    	    	}
    		}
    	}
    	return (Xmax-Xmin+1)*(Ymax-Ymin+1);
    }
    
    private boolean issafe(char [][]image, int x, int y) {
    	return x>= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == '1';
    }
}

public class Main {
	public static void main(String[] args) {
		char [][]image = {{'0','0','1','0'},{'0','1','1','0'}, {'0','1','0','0'}};
		int x = 0, y = 2;
		System.out.println(new Solution().minArea(image, x, y));
	}	
}