package main;

import java.util.ArrayList;
import java.util.List;


class Solution {
	
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		int i = 0, j = 0;
		List<int[]> list = new ArrayList<>();
		while(i < A.length && j < B.length) {
			int lower_limit = Math.max(A[i][0], B[j][0]);
			int upper_limit = Math.min(A[i][1], B[j][1]);
			if(lower_limit <= upper_limit)
				list.add(new int[] {lower_limit, upper_limit});
			if(A[i][1] < B[j][1])
				i++;
			else
				j++;
		}
		return list.toArray(new int[list.size()][]);
	}
	
	
	/* similar to 56. Merge Intervals
	 * Time 0(NlgN) Space 0(N+M)
	 */ 
	/*class Interval {
		int start, end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
    public int[][] intervalIntersection(int[][] A, int[][] B) {
    	if(A == null || B == null || A.length == 0 || B.length == 0 || A[0].length == 0 ||
    			B[0].length == 0)
    		return new int[0][0];
    	List<Interval> list = new ArrayList<>();
    	for(int i = 0;i < A.length;i++)
    		list.add(new Interval(A[i][0], A[i][1]));
    	for(int i = 0;i < B.length;i++)
    		list.add(new Interval(B[i][0], B[i][1]));
    	
    	int []start = new int[list.size()];
    	int []end = new int[list.size()];
    	for(int i = 0;i < list.size();i++) {
    		start[i] = list.get(i).start;
    		end[i] = list.get(i).end;
    	}
    	
    	Arrays.sort(start);
    	Arrays.sort(end);
    	list.clear();
    	for(int i = 1;i < start.length;i++) {
    		if(start[i] <= end[i-1]) {
    			list.add(new Interval(start[i], end[i-1]));
    		}
    	}
    	
    	int [][]ans = new int[list.size()][2];
    	for(int i = 0;i < list.size();i++) {
    		ans[i][0] = list.get(i).start;
    		ans[i][1] = list.get(i).end;
    	}
    	return ans;
    }*/
    
    void print(int []arr) {
    	for(int i : arr)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]A = {{0,2}, {5,10}, {13,23}, {24,25}};
		int [][]A = {{4,7}, {8,14}};
		
		//int [][]B = {{1,5}, {8,12}, {15,24}, {25,26}};
		int [][]B = {{3,4}};
		int [][]res = new Solution().intervalIntersection(A, B);
		for(int i = 0;i < res.length;i++) {
			System.out.println(res[i][0] + " " + res[i][1]);
		}
	}
}