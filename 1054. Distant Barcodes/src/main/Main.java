package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
    	if(barcodes == null || barcodes.length == 0)
    		return new int[] {};
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1]==o1[1]?o2[0]-o1[0]:o2[1]-o1[1];
			}
		});
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	
    	for(int barcode : barcodes)
    		map.put(barcode, map.getOrDefault(barcode, 0)+1);
    	
    	for(int barcode : map.keySet())
    		pq.offer(new int[] {barcode, map.get(barcode)});
    	
    	for(int i = 0;i < barcodes.length;i++) {
    		int []first = pq.poll();
    		if(i == 0 || barcodes[i-1] != first[0]) {
    			barcodes[i] = first[0];
    			first[1]--;
    		} else {
    			int []second = pq.poll();
    			barcodes[i] = second[0];
    			second[1]--;
    			if(second[1] > 0)
    				pq.offer(second);
    		}
    		if(first[1] > 0)
    			pq.offer(first);
    	}
    	print(barcodes);
    	return barcodes;
    }
    
    void print(int []barcodes) {
    	for(int i : barcodes) 
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//int []barcodes = {1,1,1,2,2,2};
		int []barcodes = {1,1,1,1,2,2,3,3};
		new Solution().rearrangeBarcodes(barcodes);
	}
}
