package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/* LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems. 
 * But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. 
 * Your job is to schedule the traveling to maximize the number of vacation days you could take, 
 * but there are certain rules and restrictions you need to follow.

Rules and restrictions:
You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical), called 
flights representing the airline status from the city i to the city j. If there is no flight from the city i to the city j,
 flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and can only 
take flights on each week's Monday morning. Since flight time is so short, we don't consider the impact of flight time.
For each city, you can only have restricted vacation days in different weeks, given an N*K matrix called days 
representing this relationship. For the value of days[i][j], it represents the maximum days you could take vacation 
in the city i in the week j.
You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.

Example 1:
Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
Output: 12
Explanation: 
Ans = 6 + 3 + 3 = 12. 

One of the best strategies is:
1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day. 
(Although you start at city 0, we could also fly to and start at other cities since it is Monday.) 
2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
3rd week : stay at city 2, and play 3 days and work 4 days.
Example 2:
Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
Output: 3
Explanation: 
Ans = 1 + 1 + 1 = 3. 

Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks. 
For each week, you only have one day to play and six days to work. 
So the maximum number of vacation days is 3.
Example 3:
Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
Output: 21
Explanation:
Ans = 7 + 7 + 7 = 21

One of the best strategies is:
1st week : stay at city 0, and play 7 days. 
2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
Note:
N and K are positive integers, which are in the range of [1, 100].
In the matrix flights, all the values are integers in the range of [0, 1].
In the matrix days, all the values are integers in the range [0, 7].
You could stay at a city beyond the number of vacation days, but you should work on the extra days, which won't be 
counted as vacation days.
If you fly from the city A to the city B and take the vacation on that day, the deduction towards vacation days will 
count towards the vacation days of city B in that week.
We don't consider the impact of flight hours towards the calculation of vacation days.
 * count towards the vacation days of city B in that week.count towards the vacation days of city B in that week.count towards
 */

class Solution {
	/* The idea behind this approach is as follows. The maximum number of vacations that can be taken given we start from the ith
	 * city in jth week is not dependent on the the vacations that can be taken in the earlier weeks. 
	 * It only depends on the number of vacations that can be taken in the upcoming weeks and also on the 
	 * connections between the various cities(flights). Thus we move backwards.
	 * 
	 * Therefore, we can make use of a 2-D dp, in which dp[i][k] represents the maximum number of vacations 
	 * which can be taken starting from the ith city in the kth week. This dp is filled in the 
	 * backward manner(in terms of the week number) because we cannot fill in forward manner subproblem possible only in backward
	 * While filling up the entry for dp[i][k], we need to consider the following cases:
	 * 1.We start from the ith city in the kth week and stay in the same city for the (k+1)th week. 
	 * Thus, the factor to be considered for updating the 
	 * dp[i][k] entry will be given by: days[i][k] + dp[i, k+1]
	 * 
	 * We start from the ith city in the kth week and move to the jth city in the (k+1)th week. 
	 * But, for changing the city in this manner, we need to be able to move from the ith city to the jth city 
	 * i.e. flights[i][j] should be 1 for such i and j.
	 * But, while changing the city from ith city in the kth week, we can move to any jth city such that a 
	 * connection exists between the ith city and the jth city i.e. flights[i][j]=1. But, in order to 
	 * maximize the number of vacations that can be taken starting from the ith city in the kth week, 
	 * we need to choose the destination city that leads to maximum no. of vacations. 
	 * Thus, the factor to be considered here, is given by: maxdays[j][k]+days[j,k+1], for all i, j, k 
	 * satisfying flights[i][j]=1, 0 ≤ i,j ≤ n, where0≤i,j≤n,wheren$$ refers to the number of cities.
	 * At the end, we need to find the maximum out of these two factors to update the dp[i][k] value.
	 * In order to fill the dp values, we start by filling the entries for the last week and proceed backwards. 
	 * At last, the value of dp[0][0] gives the required result.
	 */
	// used array of cities not week// time 0(N^2*K) Space (N)
	/* Now, dp[i] is used to store the number of vacations that provided that we start from the ith city in the current week. 
	 * The procedure remains the same as that of the previous approach, 
	 * except that we make the updation in the same dp row again and again. 
	 * In order to store the dp values corresponding to the current week temporarily, 
	 * we make use of a temp array so that the original dp entries corresponding to week+1 aren't altered.
	 * 
	 * 
	 * https://leetcode.com/articles/maximum-vacation-days/
	 */
    public int maxVacationDays(int[][] flights, int[][] days) {
    	int n = flights.length, k = days[0].length;
    	int []dp = new int[n];
    	
    	// notice started from the back
    	for(int week = k-1;week >= 0;week--) {
    		int []temp = new int[n];
    		for(int src = 0;src < n;src++) {
    			temp[src] = days[src][week] + dp[src];
    			for(int dest = 0;dest < n;dest++) {
    				if(flights[src][dest] == 1) {
    					temp[src] = Math.max(temp[src], days[dest][week] + dp[dest]);
    				}
    			}
    		}
    		dp = temp;
    	}
    	return dp[0];
    }
	
	
    public int maxVacationDays1(int[][] flights, int[][] days) {
    	int n = flights.length, k = days[0].length;
    	
    	int [][]dp = new int[n][k+1];
    	for(int week = k-1;week >= 0;week--) {
    		for(int src = 0;src < n;src++) {
    			dp[src][week] = days[src][week] + dp[src][week+1];
    			for(int dest = 0;dest < n;dest++) {
    				if(flights[src][dest] == 1) {
    					dp[src][week] = Math.max(dp[src][week], days[dest][week] + dp[dest][week+1]);
    				}
    			}
    		}
    	}
    	return dp[0][0];
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]flights = {{0,1,1},{1,0,1},{1,1,0}}, days = {{1,3,1},{6,0,3},{3,3,3}};
		int [][]flights = {{0,1,0},{0,0,0},{0,0,0}}, days = {{0,0,7},{2,0,0},{7,7,7}};
		System.out.println(new Solution().maxVacationDays(flights, days));
		System.out.println(new Solution().maxVacationDays1(flights, days));
	}
}