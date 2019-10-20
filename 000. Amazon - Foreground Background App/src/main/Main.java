package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* https://www.careercup.com/question?id=5750442676453376
 * Given max. travel distance and forward and backward route list, 
 * return pair of ids of forward and backward routes that optimally utilized the max travel distance.: 
 * eg: max travel distance is : 11000 
 * forward route list : [1,3000],[2,5000],[3,4000],[4,10000] 
 * backward route list : [1,2000],[2,3000],[3,4000] 
 * 
 * Result : [2,3] ...2 is from forward and 3 is from backward...total distance is 9000...no other combination is 
 * there which is >9000 and <=11,000.......0(n^2) solution is straight forward, thinking that sorting both might help.
 */

class Solution {
	public List<List<Integer>> findOptimalMemory(int capacity, List<List<Integer>> foreground,
	         List<List<Integer>> background) {
		List<List<Integer>> res = new ArrayList<>();
		// +1 because capacity is also included in answer
		List<List<Integer>> []arr = new ArrayList[capacity+1];
		int m = foreground.size(), n = background.size();
		int i = 0, j = n - 1;
		
		Collections.sort(foreground, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.get(1) - o2.get(1);
			}
		});
		
		Collections.sort(background, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.get(1) - o2.get(1);
			}
		});
		
		while(i < m && j >= 0) {
			int sum = foreground.get(i).get(1) + background.get(j).get(1);
			if(sum <= capacity) {
				if(arr[sum] == null)
					arr[sum] = new ArrayList<>();
				arr[sum].add(Arrays.asList(foreground.get(i).get(0), background.get(j).get(0)));
				i++;
			} else
				j--;
		}
		
		while(i < m) {
			int sum = foreground.get(i).get(1) + background.get(0).get(1);
			if(sum <= capacity) {
				if(arr[sum] == null) {
					arr[sum] = new ArrayList<>();
					arr[sum].add(Arrays.asList(foreground.get(i).get(0), background.get(0).get(0)));
				}
			}
			i++;
		}
		
		while(j >= 0) {
			int sum = foreground.get(m-1).get(1) + background.get(j).get(1);
			if(sum <= capacity) {
				if(arr[sum] == null) {
					arr[sum] = new ArrayList<>();
					arr[sum].add(Arrays.asList(foreground.get(m-1).get(0), background.get(j).get(0)));
				}
			}
			j--;
		}
		
		for(int k = capacity;k >= 0;k--) {
			if(arr[k] != null) {
				res.addAll(arr[k]);
				break;
			}
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		int capacity = 11000;
		List<List<Integer>> foreground = Arrays.asList(Arrays.asList(1,3000), Arrays.asList(2,5000), Arrays.asList(3,4000), 
				Arrays.asList(4,10000));
        List<List<Integer>> background = Arrays.asList(Arrays.asList(1,2000), Arrays.asList(2,3000), Arrays.asList(3,4000));
        System.out.println(new Solution().findOptimalMemory(capacity, foreground, background));
	}
}
