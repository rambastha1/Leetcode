package main;

class Solution {
	public int maxheight(int []positions, int []heights) {
		if(positions.length == 0 || heights.length == 0 || positions.length != heights.length)
			return 0;
		
		int res = 0, n = heights.length;
		
		for(int i = 1;i < n;i++) {
			int curr = calculate(positions[i]-positions[i-1]-1, heights[i], heights[i-1]);
			res = Math.max(res, curr);
		}
		return res;
	}
	
	private int calculate(int dist, int h1, int h2) {
		int min = Math.min(h1, h2), max = Math.max(h1, h2);
		if(dist == 0)
			return 0;
		if(dist == 1)
			return min+1;
		if(min == max) {
			int add = (dist%2==0)?dist/2:dist/2+1;
			return min+add;
		}
		
		int diff = max-min;
		if(diff < dist) {
			dist -= diff;
			min += diff;
			int add = (dist%2==0)?dist/2:dist/2+1;
			return min+add;
		}
		return min+dist;
	}
}

public class Main {
	public static void main(String[] args) {
		int []positions = {1,10}, heights = {1,5};
		System.out.println(new Solution().maxheight(positions, heights));
	}
}