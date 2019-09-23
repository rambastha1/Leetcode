package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    	int [][]p = {p1,p2,p3,p4};
    	Arrays.sort(p, new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[0]==o2[0])?o1[1]-o2[1]:o1[0]-o2[0];
			}
		});
    	
    	return dist(p[0], p[1]) != 0 && dist(p[0], p[1]) == dist(p[1], p[3]) 
    			&& dist(p[1], p[3]) == dist(p[3], p[2]) && dist(p[3], p[2]) == dist(p[2], p[0])   
    			&& dist(p[0],p[3]) == dist(p[1],p[2]);
    }
    
    public double dist(int []p1, int []p2) {
    	return (p2[0]-p1[0])*(p2[0]-p1[0]) + (p2[1]-p1[1])*(p2[1]-p1[1]);
    }
}

public class Main {
	public static void main(String[] args) {
		int []p1 = {0,0}, p2 = {1,1}, p3 = {1,0}, p4 = {0,1};
		System.out.println(new Solution().validSquare(p1, p2, p3, p4));
	}
}
