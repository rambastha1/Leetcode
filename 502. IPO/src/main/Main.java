package main;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
	/* sorting in increasing order by capital makes sure we use less capital
	 * https://leetcode.com/problems/ipo/discuss/98210/Very-Simple-(Greedy)-Java-Solution-using-two-PriorityQueues 
	 * time 0(NlgN)
	 * worst case - all inserted to pq_pro
	 */
	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
    	int n = Capital.length;
    	PriorityQueue<int[]> pq_cap = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
    	
    	PriorityQueue<int[]> pq_pro = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
    	
    	for(int i = 0;i < n;i++)
    		pq_cap.offer(new int[] {Profits[i], Capital[i]});
    	
    	/* we need at most k projects - try to find k project
    	 * till pq_cap capital <= W offer to pq_pro which is sorted in project decreasing manner
    	 * we are offering only when it is within W and makes it optimal candidate as capital is min and profit is max
    	 * add best candidate i.e max profit one pq_pro.poll()[0] for every k
    	 */
    	for(int i = 0;i < k;i++) {
			while(!pq_cap.isEmpty() && pq_cap.peek()[1] <= W) {
				pq_pro.offer(pq_cap.poll());
			}
			if(pq_pro.size() == 0)
				break; 
			W += pq_pro.poll()[0];
    	}
    	return W;
    }
}

public class Main {
	public static void main(String[] args) {
		int k = 1, W = 2; 
		int []Profits = {1,2,3}, Capital = {1,1,2};
		System.out.println(new Solution().findMaximizedCapital(k, W, Profits, Capital));
	}
}
