package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int scheduleCourse(int[][] courses) {
    	Arrays.sort(courses, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] == o2[1]?o1[0] - o2[0] : o1[1] - o2[1];
			}
		});
    	
    	int ans = 0;
    	
    	
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
		System.out.println(new Solution().scheduleCourse(courses));
	}
}
