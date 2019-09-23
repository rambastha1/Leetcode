package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;
        
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        //inward degree
        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        
        /* degree 0 means subject on which maximum courses depend
         * It is logical to select such course as it will free or reduce dependency of 
         * maximum number of subjects
         * Thus it keeps offering such subject to queue to reduce max burden each time. 
         */
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }
        
        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else    
            return false;
    }
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		int [][]prerequisites = {{0,1}, {0,2}, {1,2}};
		int numCourses = 3;
		System.out.println(s.canFinish(numCourses, prerequisites));
	}
}