package main;

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
    	return (rec1[0] < rec2[2] && rec1[1] < rec2[3] && rec2[0] < rec1[2] && rec2[1] < rec1[3]);
    }
}

public class Main {
	public static void main(String[] args) {
		int []rec1 = {0,0,2,2}, rec2 = {1,1,3,3};
		//int []rec1 = {0,0,1,1}, rec2 = {1,0,2,1};
		System.out.println(new Solution().isRectangleOverlap(rec1, rec2));
	}
}
