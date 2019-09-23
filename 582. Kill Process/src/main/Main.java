package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * Each process only has one parent process, but may have one or more children processes. 
 * This is just like a tree structure. Only one process has PPID that is 0, 
 * which means this process has no parent process. All the PIDs will be distinct positive integers.
 * We use two list of integers to represent a list of processes, 
 * where the first list contains PID for each process and the second list contains the 
 * corresponding PPID.
 * Now given the two lists, and a PID representing a process you want to kill, 
 * return a list of PIDs of processes that will be killed in the end. You should assume that 
 * when a process is killed, all its children processes will be killed. No order is 
 * required for the final answer.
 * Example 1:
 * Input: 
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * Explanation: 
           3
         /   \
        1     5
             /
            10
 * Kill 5 will also kill 10.
 * Note:
 * The given kill id is guaranteed to be one of the given PIDs.
 * n >= 1.
 */

class Solution {
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		List<Integer> res = new ArrayList<>();
		if(pid == null || ppid == null || pid.size() == 0 || ppid.size() == 0)
			return res;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i = 0;i < ppid.size();i++) {
			if(!map.containsKey(ppid.get(i))) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(ppid.get(i), list);
			} else {
				List<Integer> list = map.get(ppid.get(i));
				list.add(i);
				map.put(ppid.get(i), list);
			}
		}
		Queue<Integer> q = new LinkedList<>();
		q.offer(kill);
		while(!q.isEmpty()) {
			int parent = q.poll();
			res.add(parent);
			if(!ppid.contains(parent))
				continue;
			List<Integer> child = map.get(parent);
			for(int i = 0;i < child.size();i++)
				q.offer(pid.get(child.get(i)));				
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		List<Integer> pid =  Arrays.asList(1, 3, 10, 5);
		List<Integer> ppid =  Arrays.asList(3, 0, 5, 3);
		int kill = 5;
		System.out.println(new Solution().killProcess(pid, ppid, kill));
	}
}