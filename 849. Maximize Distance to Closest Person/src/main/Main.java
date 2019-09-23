package main;

/* https://leetcode.com/problems/maximize-distance-to-closest-person/discuss/155564/Clean-One-Pass-Two-Pointers-Java-Solution
 * 
 * If the current value is "0", keep going forward.
 * Left pointer points to the position of left "1" and right pointer points to the position of right "1". 
 * Keep updating two pointers and calculate the max distance.
 * Be careful of two situations: seats[0] is 0 and seats[len - 1] is 0. Just check them and 
 * get the final answer. Ex: 00101000.
 * 
 * the problem is to find the maximum distance between two continuous 1 in an array, 
 * and just return half of that maximum value...(also before the first 1 and after the last 1 
 * will be two special cases)
 * 
 */

class Solution {
    public int maxDistToClosest(int[] seats) {
    	if(seats == null || seats.length == 0)
    		return -1;
    	int l = -1, n = seats.length;
    	int dist = 0;
    	for(int r = 0;r < n;r++) {
    		if(seats[r] == 0)
    			continue;
    		if(l == -1)
    			dist = Math.max(dist, r);
    		else
    			dist = Math.max(dist, (r-l)/2);
    		l = r;
    	}
    	if(seats[n-1] == 0)
    		dist = Math.max(dist, n-1-l);
    	return dist;
    }
}

public class Main {
	public static void main(String[] args) {
		int []seats = {1,0,0,0,1,0,1};
		System.out.println(new Solution().maxDistToClosest(seats));
	}
}
