package main;

class Sea {
	public boolean hasShips(int[] topRight, int[] bottomLeft) {
		return true;
	}
}

class Solution {
	/* Divide and Conquer
	 * find mid point and count on all four sides of mid till the base condition is hit
	 */
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if(!sea.hasShips(topRight, bottomLeft))
        	return 0;
        // base condition
        if(topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1] && sea.hasShips(topRight, bottomLeft))
        	return 1;
        
        int midx = bottomLeft[0] + (topRight[0] - bottomLeft[0])/2;
        int midy = bottomLeft[1] + (topRight[1] - bottomLeft[1])/2;
        return countShips(sea, topRight, new int[] {midx+1, midy+1}) + countShips(sea, new int[] {midx, midy}, bottomLeft) + 
        		countShips(sea, new int[] {midx, topRight[1]}, new int[] {bottomLeft[0], midy+1}) + 
        		countShips(sea, new int[] {topRight[0], midy}, new int[] {midx+1, bottomLeft[1]});
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
