package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
	
	/*
	 * Using Divide and Conquer with quick select
	 *  Finding kth smallest element using quick select also gives k smallest array elements
	 *  that are at left side of kth element.
	 *  In quick sort, the partition function returns position of element at its correct place.
	 *  The partition function also divides array into three parts - first elements smaller than pivot are on 
	 *  left side, pivot element and lastly larger elements on right side of pivot.
	 */
	
	//This code that i have written is the simplest quick select code i have seen
	//kudos to me better than geeksforgeeks and leetcode best discussion code
	public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = partition(points, l, r);
            if (mid == K-1) 
            	break;
            if (mid < K-1) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int partition(int[][] A, int l, int r) {
        int[] pivot = A[r];
        int i = l-1;
        for(int j = l;j <= r;j++) {
        	//-ve value basically shows A[j] is smaller than pivot
        	if(compare(A[j], pivot) < 0)
        		swap(A, ++i, j);
        }
        swap(A, ++i, r);
        return i;
    }
    
    public void swap(int [][]A, int a, int b) {
    	int x = A[a][0], y = A[a][1];
    	A[a][0] = A[b][0];
    	A[a][1] = A[b][1];
    	A[b][0] = x;
    	A[b][1] = y;
    }

    //positive result gives whether p1 is farther from origin than p2 
    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
	
	
	//Using heap 
	/*class Point {
		int x, y;
		double dist;
		
		public Point(int x, int y, double dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
	}
    
	public int[][] kClosest(int[][] points, int K) {
    	int [][]res = new int[K][2];
    	if(points == null || points.length == 0 || points[0].length == 0 || K <= 0)
    		return res;
    	Comparator<Point> comp = new Comparator<Solution.Point>() {
			
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.dist > o2.dist) return -1;
				if(o1.dist < o2.dist) return 1;
				return 0;
			}
		};
    	
    	PriorityQueue<Point> pq = new PriorityQueue<>(K, comp);
    	for(int i = 0;i < points.length;i++) {
    		double dist = Math.sqrt(Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2));
    		pq.offer(new Point(points[i][0], points[i][1], dist));
    		if(pq.size() > K)
    			pq.poll();
    	}
    	
    	for(int i = 0;i < K;i++) {
    		Point p = pq.poll();
    		res[i][0] = p.x;
    		res[i][1] = p.y;
    	}
    	print(res);
    	return res;
    }*/
    
    void print(int [][]res) {
    	for(int i = 0;i < res.length;i++)
    		System.out.println(res[i][0] + " " + res[i][1]);
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][] points = {{1,3}, {-2,2}};
		int [][] points = {{3,3}, {5,-1}, {-2,4}};
		//int [][] points = {{-5,4}, {-6,-5}, {4,6}};
		int K = 2;
		int [][]res = new Solution().kClosest(points, K);
		for(int i = 0;i < K;i++)
			System.out.println(res[i][0] + " " + res[i][1]);
	}
}