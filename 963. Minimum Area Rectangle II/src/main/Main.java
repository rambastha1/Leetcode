package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* We find center of diagonal and create a string as key store all the indices of points
 * which form this center. Now iterate map get length and breadth 
 * calculate area and return min area
 */

class Solution {
    public double minAreaFreeRect(int[][] points) {
        int len = points.length;
        double res = Double.MAX_VALUE;
        if (len < 4) return 0.0;
        Map<String, List<int[]>> map = new HashMap<>(); // int[] is the index of two points forming the diagonal
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                long dis = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                double centerX = (double)(points[j][0] + points[i][0])/2; // centerX and centerY is the coordinate of the diagonal center
                double centerY = (double)(points[j][1] + points[i][1])/2;
                String key = "" + dis + "+" + centerX + "+" + centerY; // key includes the length of the diagonal and the coordinate of the diagonal center
                if (map.get(key) == null) map.put(key, new ArrayList<int[]>());
                map.get(key).add(new int[]{i,j});
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {  
                List<int[]> list = map.get(key);
                for (int i = 0; i < list.size(); i++) { // there could be multiple rectangles inside
                    for (int j = i + 1; j < list.size(); j++) {
                        int p1 = list.get(i)[0]; // p1, p2 and p3 are the three vertices of a rectangle
                        int p2 = list.get(j)[0];
                        int p3 = list.get(j)[1];
                        // len1 and len2 are the length of the sides of a rectangle
                        double len1 = Math.sqrt((points[p1][0] - points[p2][0]) * (points[p1][0] - points[p2][0]) +  (points[p1][1] - points[p2][1]) * (points[p1][1] - points[p2][1])); 
                        double len2 = Math.sqrt((points[p1][0] - points[p3][0]) * (points[p1][0] - points[p3][0]) +  (points[p1][1] - points[p3][1]) * (points[p1][1] - points[p3][1]));
                        double area = len1 * len2; 
                        res = Math.min(res, area);
                    }
                }
            }
        }
        return res == Double.MAX_VALUE ?  0.0 : res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]points = {{1,2},{2,1},{1,0},{0,1}};
		int [][]points = {{0,1},{2,1},{1,1},{1,0},{2,0}};
		System.out.println(new Solution().minAreaFreeRect(points));
	}
}
