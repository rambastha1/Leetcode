package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javafx.util.Pair;

class Solution {
	class PairComparator implements Comparator<Pair<Integer, Pair<Integer, Integer>>> {
		@Override
		public int compare(Pair<Integer, Pair<Integer, Integer>> o1, Pair<Integer, Pair<Integer, Integer>> o2) {
			return o1.getKey() - o2.getKey();
		}
	}
	
	public List<Integer> merge(int [][]arr) {
		List<Integer> res = new ArrayList<>();
		PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> pq = new PriorityQueue<>(arr.length, new PairComparator());
		
		for(int i = 0;i < arr.length;i++) {
			pq.add(new Pair<Integer, Pair<Integer,Integer>>(arr[i][0], new Pair<>(i, 0)));
		}
		
		while(!pq.isEmpty()) {
			Pair<Integer, Pair<Integer, Integer>> pair = pq.poll();
			int arr_num = pair.getValue().getKey();
			int arr_index = pair.getValue().getValue();
			res.add(pair.getKey());
			if(arr_index + 1 < arr[arr_num].length)
				pq.add(new Pair<Integer, Pair<Integer,Integer>>(arr[arr_num][arr_index+1], new Pair<>(arr_num, arr_index + 1)));
		}		
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]arr = { { 2, 6, 12 }, { 1, 9 }, { 23, 34, 90, 2000 } };
		System.out.println(new Solution().merge(arr));
	}
}