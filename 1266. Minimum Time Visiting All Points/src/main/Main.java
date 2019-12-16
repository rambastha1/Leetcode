package main;

class Solution {
	// Time 0(N)
	/* Proof: the time cost to travel between 2 neighboring points equals the larger value between the absolute 
	 * values of the difference of respective x and y coordinates of the 2 points.

a) Consider 2 points (x1, y1) and (x2, y2), let dx = |x1 - x2| and dy = |y1 - y2|; According to the constraints of the problem, 
each step at most moves 1 unit along x and/or y coordinate. Therefore, min time >= max(dx, dy);
b) On the other hand, each step can move 1 unit along x/y coordinate to cover the distance dx/dy, whichever is greater.
Combine the above a) and b), we complete the proof.
	 * 
	 */
    public int minTimeToVisitAllPoints(int[][] points) {
    	int n = points.length;
    	if(n == 1)
    		return 0;
    	int res = 0;
    	for(int i = 1;i < n;i++) {
    		int []curr = points[i], prev = points[i-1];
    		res += Math.max(Math.abs(curr[0]-prev[0]), Math.abs(curr[1]-prev[1]));
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]points = {{1,1}, {3,4}, {-1,0}};
		System.out.println(new Solution().minTimeToVisitAllPoints(points));
	}
}