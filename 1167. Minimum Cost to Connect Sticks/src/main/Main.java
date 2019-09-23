package main;

import java.util.PriorityQueue;

class Solution {
    public int connectSticks(int[] sticks) {
    	int n = sticks.length;
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	for(int s : sticks)
    		pq.offer(s);
    	
    	int sum = 0;
    	while(pq.size() > 1) {
    		int num = pq.poll() + pq.poll();
    		sum += num;
    		pq.offer(num);
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		int []sticks = {2,4,3};
		System.out.println(new Solution().connectSticks(sticks));
	}
}
