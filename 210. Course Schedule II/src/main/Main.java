package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public int[] findOrder(int numCourses, int[][] prerequisites) { 
	    if (numCourses == 0) return null;
	    // Convert graph presentation from edges to indegree of adjacent list.
	    int num_pre_required[] = new int[numCourses], order[] = new int[numCourses], index = 0;
	    for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
	        num_pre_required[prerequisites[i][0]]++;   // Number of out arrows shows num of prerequisites     

	    Queue<Integer> queue = new LinkedList<Integer>();
	    for (int i = 0; i < numCourses; i++) 
	        if (num_pre_required[i] == 0) {
	            // Add the course to the order because it has no prerequisites.
	            order[index++] = i;
	            queue.offer(i);
	        }

	    // How many courses don't need prerequisites. 
	    while (!queue.isEmpty()) {
	        int prerequisite = queue.poll(); // Already finished this prerequisite course.
	        for (int i = 0; i < prerequisites.length; i++)  {
	            if (prerequisites[i][1] == prerequisite) {
	                num_pre_required[prerequisites[i][0]]--; 
	                if (num_pre_required[prerequisites[i][0]] == 0) {
	                    // If indegree is zero, then add the course to the order.
	                    order[index++] = prerequisites[i][0];
	                    queue.offer(prerequisites[i][0]);
	                }
	            } 
	        }
	    }
	    return (index == numCourses) ? order : new int[0];
	}
}

public class Main {

	public static void main(String[] args) {
		int numCourses = 4;
		int [][]prerequisites = { {1,0}, {2,0}, {3,1},{3,2}};
		int []res = new int[numCourses];
		res = new Solution().findOrder(numCourses, prerequisites);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}