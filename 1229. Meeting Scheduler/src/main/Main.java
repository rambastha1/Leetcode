package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, 
 * return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
It is guaranteed that no two availability slots of the same person intersect with each other. 
That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

 

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 

Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6 
 * 
 */

class Solution {
	// Time 0(NlgN)
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    	
    	List<Integer> res = new ArrayList<>();
    	Comparator<int[]> comp = new Comparator<int[]>() {
    		@Override
    		public int compare(int []o1, int []o2) {
    			return o1[0] == o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
    		}
		};
    	Arrays.sort(slots1, comp);
    	Arrays.sort(slots2, comp);
    	int i = 0, j = 0, n1 = slots1.length, n2 = slots2.length;
    	while(i < n1 && j < n2) {
    		int []a1 = slots1[i], a2 = slots2[j];
    		if(a1[0] > a2[1] || a2[0] > a1[1]) {
    			if(a1[0] > a2[1])
    				j++;
    			else
    				i++;
    			continue;
    		}
    		
    		int time = Math.min(a1[1], a2[1]) - Math.max(a1[0], a2[0]);
    		if(time >= duration) {
    			int m = Math.max(a1[0], a2[0]);
    			res.add(m);
    			res.add(m+duration);
    			return res;
    		}
    		
    		if(a1[1] > a2[1])
    			j++;
    		else
    			i++;
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int [][]slots1 = {{10,50},{60,120},{140,210}}, slots2 = {{0,15},{60,70}};
		int duration = 8;*/
		int [][]slots1 = {{0,2}}, slots2 = {{1,3}};
		int duration = 1;
		System.out.println(new Solution().minAvailableDuration(slots1, slots2, duration));
	}
}