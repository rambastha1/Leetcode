package main;
import java.util.Arrays;

class Solution {
	// 0(N) solution
	public boolean carPooling(int[][] trips, int capacity) {
		int max = 0, n = trips.length;;
		for(int i = 0;i < n;i++)
			max = Math.max(max, trips[i][2]);
		
		int []dp = new int[max+1];
		for(int i = 0;i < n;i++) {
			dp[trips[i][1]] += trips[i][0];
			dp[trips[i][2]] -= trips[i][0];
		}
		
		for(int i = 0;i <= max;i++) {
			dp[i] += i > 0?dp[i-1]:0;
			if(dp[i] > capacity)
				return false;
		}
		return true;
	}
	
    public boolean carPooling1(int[][] trips, int capacity) {
    	int n = trips.length;
    	Integer []start = new Integer[n], end = new Integer[n];
    	for(int i = 0;i < n;i++) {
    		start[i] = i;
    		end[i] = i;
    	}
    	/* Separate start and end because there might be case that a journey j started after say journey i
    	 * and ended before thus j's passengers need to be subtracted from running total  
    	 */
    	Arrays.sort(start, (a,b) -> (trips[a][1] - trips[b][1]));
    	Arrays.sort(end, (a,b) -> (trips[a][2] - trips[b][2]));
    	
    	int max = trips[end[n-1]][2], s = 0, e = 0;
    	int passenger = 0;
    	for(int d = 0;d <= max;d++) {
    		while(e < n && trips[end[e]][2] <= d)
    			passenger -= trips[end[e++]][0];
    		while(s < n && trips[start[s]][1] <= d)
    			passenger += trips[start[s++]][0];
    		
    		if(passenger > capacity)
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]trips = {{9,3,4},{9,1,7},{4,2,4},{7,4,5}};
		int capacity = 23;
		System.out.println(new Solution().carPooling(trips, capacity));
		System.out.println(new Solution().carPooling1(trips, capacity));
	}
}
