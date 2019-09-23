package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// https://leetcode.com/problems/advantage-shuffle/discuss/149822/JAVA-Greedy-6-lines-with-Explanation

class Solution {
    public int[] advantageCount(int[] A, int[] B) {
    	int []res = new int[A.length];
    	if(A.length == 1)
    		return A;
    	Arrays.sort(A);
    	PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0]-o1[0];
			}
		});
    	
    	int l = 0, r = A.length-1;
    	for(int i = 0;i < B.length;i++)
    		pq.offer(new int[] {B[i], i});
    	
    	while(!pq.isEmpty()) {
    		int []curr = pq.poll();
    		int val = curr[0], index = curr[1];
    		if(A[r] > val) 
    		// use of index is amazing it puts A[r] or A[l] at specified position
    			res[index] = A[r--];
    		else
    			res[index] = A[l++];
    	}
    	print(res);
    	return res; 
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {2,7,11,15}, B = {1,10,4,11};
		int []A = {2,0,4,1,2}, B = {1,3,0,0,2};
		//int []A = {12,24,8,32}, B = {13,25,32,11};
		new Solution().advantageCount(A, B);
	}
}
