package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] reconstructQueue(int[][] people) {
    	if(people == null || people.length == 0 || people[0].length == 0)
    		return people;
    	Arrays.sort(people, new Comparator<int []>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[0] != o2[0])?o2[0]-o1[0]:o1[1]-o2[1];
			}    		
		});
    	
    	/*
    	 * Just put people according to people[i][1] (taller people before people[i][0])
    	 * We can use insertion sort but it is 0(N^2)
    	 */
    	//optimized way
    	List<int[]> ans = new ArrayList<>();
    	for(int []p : people) {
    		ans.add(p[1], p);
    	}
    	return ans.toArray(people);
    	
    	//Insertion sort
    	/*for(int i = 0;i < people.length;i++) {
    		int index = people[i][1];
    		int []temp = people[i];
    		for(int j = 0;j < i - index;j++) {
    			people[i-j] = people[i-j-1];
    		}
    		people[index] = temp;
    	}*/    	
    	//return people;
    }
    
    void print(int [][]people) {
    	for(int i = 0;i < people.length;i++) {
			for(int j = 0;j < people[i].length;j++)
				System.out.print(people[i][j] + " ");
			System.out.println();
		}
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		Solution s = new Solution();
		people = new Solution().reconstructQueue(people);
		s.print(people);
	}
}