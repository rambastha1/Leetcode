package main;

import javafx.util.Pair;
import java.util.*;
// https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/

class Solution {
	public void print(int []arr) {
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
	/*
	 * 0(NlgN) solution using Graphs
	 * Swap required for cycle of length l = l-1
	 * https://www.youtube.com/watch?v=f7IIW0HVUcQ
	 */
	
	public int min_swaps(int []arr) {
		List<Pair<Integer, Integer>> temp = new ArrayList<>();
		for(int i = 0;i < arr.length;i++)
			temp.add(new Pair<Integer, Integer>(arr[i], i));
		
		Collections.sort(temp, new Comparator<Pair<Integer, Integer>>() {
			@Override
			public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
				return p1.getKey() - p2.getKey();
			}
		});
		
		boolean []visited = new boolean[arr.length];
		int swaps = 0;
		
		for(int i = 0;i < arr.length;i++) {
			if(visited[i] || temp.get(i).getValue() == i)
				continue;
			
			int cycle = 0;
			int j = i;
			while(!visited[j]) {
				visited[j] = true;
				j = temp.get(j).getValue();
				cycle++;
			}
			if(cycle > 0)
				swaps += cycle-1;
		}		
		return swaps;
	}
}


public class Main {
	public static void main(String[] args) {
		//int []arr = {4, 3, 2, 1};
		int []arr = {4, 3, 2, 1};
		System.out.println(new Solution().min_swaps(arr));
		//System.out.println(new Solution().min_swaps1(arr));
	}
}