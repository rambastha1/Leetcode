package main;

class Solution {
	
	//Time 0(N) Space 0(N)
	public int mincostTickets(int[] days, int[] costs) {
        if(days.length == 1) return costs[0];
        int[] dp = new int[days[days.length-1]+1];
        for(int i = 0;i < days.length;i++) {
        	int c1 = 0, c7 = 0, c30 = 0, min = Integer.MAX_VALUE;
        	c1 = (days[i]-1 <= 0)? costs[0] : dp[days[i]-1] + costs[0];
        	c7 = (days[i]-7 <= 0)? costs[1] : dp[days[i]-7] + costs[1];
        	c30 = (days[i]-30 <= 0)? costs[2] : dp[days[i]-30] + costs[2];
        	dp[days[i]] = Math.min(c1, Math.min(c7, c30));
        	for(int j = days[i]+1;i+1 < days.length && j < days[i+1];j++)
        		dp[j] = dp[j-1];
        }
        return dp[dp.length-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []days = {1,2,3,4,5,6,7,8,9,10,30,31};
		//int []days = {1,4,6,7,8,20};
		int []costs = {2,7,15};
		System.out.println(new Solution().mincostTickets(days, costs));
	}
}