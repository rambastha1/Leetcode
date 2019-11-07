package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: 
course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.

 

Example 1:



Input: N = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: 
In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
Example 2:



Input: N = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: 
No course can be studied because they depend on each other.
 

Note:

1 <= N <= 5000
1 <= relations.length <= 5000
relations[i][0] != relations[i][1]
There are no repeated relations in the input.
 * 
 */

class Solution {
	// Time 0(V+E)
    public int minimumSemesters(int N, int[][] relations) {
    	Map<Integer, List<Integer>> graph = new HashMap<>();
    	int []indegree = new int[N+1];
    	for(int []sub : relations) {
    		if(!graph.containsKey(sub[0]))
    			graph.put(sub[0], new ArrayList<>());
    		graph.get(sub[0]).add(sub[1]);
    		indegree[sub[1]]++;
    	}
    	
    	Queue<Integer> q = new LinkedList<>();
    	boolean []visited = new boolean[N+1];
    	
    	for(int i = 1;i <= N;i++) {
    		if(indegree[i] == 0)
    			q.offer(i);
    	}
    	int semester = 0, subjects  = 0;
    
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			int sub = q.poll();
    			subjects++;
    			if(visited[sub] || !graph.containsKey(sub))
    				continue;
    			visited[sub] = true;
    			
    			for(int dependant : graph.get(sub)) {
    				indegree[dependant]--;
    				if(indegree[dependant] == 0)
    					q.offer(dependant);
    			}
    		}
    		semester++;
    	}
    	return subjects == N?semester:-1;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 3;
		int [][]relations = {{1,3}, {2,3}};
		System.out.println(new Solution().minimumSemesters(N, relations));
	}
}