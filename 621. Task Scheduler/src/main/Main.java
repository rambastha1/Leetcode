package main;

import java.util.Arrays;


// https://leetcode.com/articles/task-scheduler/
class Solution {
	
	/*
	 * Greedy Time 0(time) Space 0(1)
	 * sorting of 26 array is considered constant time
	 */
	
	public int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c: tasks)
			map[c - 'A']++;
		Arrays.sort(map);
		int time = 0;

		/* 25 is used because since array is sorted all values of count[i] will shift right
		 * thus maximum frequency task will be at 25
		 */ 

		while (map[25] > 0) {
			int i = 0;
			while (i <= n) {

				/* For optimal use of time, insert highest freqency task as soon 
				 * as cooldown ends i.e. task at index 25
				 */ 

				if (map[25] == 0)
					break;
				// One by one fill other tasks
				if (i < 26 && map[25 - i] > 0)
					map[25 - i]--;
				time++;
				i++;
			}
			//sort because after each iteration frequencies will change
			//always pick highest frequency task

			Arrays.sort(map);
		}
		return time;
	}
	
	
	/*
	 * By counting number of idle slots
	 */
	/*public int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int maxval = map[25]-1, idle = maxval*n;
        for(int i = 24;i >= 0 && map[i] > 0;i--) {
        	idle -= Math.min(map[i], maxval);
        }
        // idle is when left idle + time to  complete n tasks thus tasks.length
        return idle>0?idle+tasks.length:tasks.length;
	}*/
}

public class Main {
	public static void main(String[] args) {
		char []tasks = {'A','A','A','B','B','B'};
		int n = 2;
		System.out.println(new Solution().leastInterval(tasks, n));
	}
}