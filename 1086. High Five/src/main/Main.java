package main;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/* Given a list of scores of different students, return the average score of each student's top five scores in the order of 
 * each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated 
using integer division.

 

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 

Note:

1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores
 * 
 */

class Solution {
    public int[][] highFive(int[][] items) {
    	//Id -> list of top five subjects
    	Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
    	for(int []item : items) {
    		if(!map.containsKey(item[0]))
    			map.put(item[0], new PriorityQueue<>());
    		map.get(item[0]).offer(item[1]);
    		if(map.get(item[0]).size() > 5)
    			map.get(item[0]).poll();
    	}
    	
    	int [][]ans = new int[map.size()][2];
    	int index = 0;
    	for(int id : map.keySet()) {
    		ans[index][0] = id;
    		PriorityQueue<Integer> pq = map.get(id);
    		int sum = 0;
    		while(!pq.isEmpty()) 
    			sum += pq.poll();
    		ans[index][1] = sum/5;
    		index++;
    	}
    	print(ans);
    	return ans;
    }
    
    private void print(int [][]items) {
    	for(int []i : items) {
    		System.out.println(i[0] + "-> " + i[1]);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] items = {{1,91}, {1,92}, {2,93}, {2,97}, {1,60}, {2,77}, {1,65}, {1,87}, {1,100}, {2,100}, {2,76}};
		new Solution().highFive(items);
	}
}