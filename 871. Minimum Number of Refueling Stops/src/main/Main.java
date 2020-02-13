package main;
import java.util.Collections;
import java.util.PriorityQueue;
// https://leetcode.com/problems/minimum-number-of-refueling-stops/discuss/149858/Simple-Java-Solution-Using-PriorityQueue-O(nlogn)
// https://leetcode.com/articles/minimum-number-of-refueling-stops/
class Solution {
	// 0(NlgN)
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
    	int n = stations.length;
    	if(n == 0 && startFuel < target)
    		return -1;
        if(startFuel >= target)
            return 0;
        if(startFuel < stations[0][0])
            return -1;
    	
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int reach = startFuel, i = 0, ans = 0;
        // true because for !pq.isempty startFuel need to offer -> give wrong answer when i = 1 
        while(true) {
        	// till reach keep offering the maximum reach
        	while(i < n && stations[i][0] <= reach) {
        		pq.offer(stations[i][1]);
        		i++;
        	}
        	
        	if(reach >= target)
        		return ans;
        	if(pq.isEmpty())
        		return -1;
        	// add maximum reach to current
        	// it works because in subsequent process we get maximum fuel stations since reach is large -> higher probability
        	reach += pq.poll();
        	ans++;
        }
    }
}

public class Main {
	public static void main(String[] args) {
		int target = 100, startFuel = 50;
		int [][]stations = {{25,30}};
		System.out.println(new Solution().minRefuelStops(target, startFuel, stations));
	}
}
