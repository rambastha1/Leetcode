package main;
/* A dieter consumes calories[i] calories on the i-th day. For every consecutive sequence of k days, 
 * they look at T, the total calories consumed during that sequence of k days:

If T < lower, they performed poorly on their diet and lose 1 point;
If T > upper, they performed well on their diet and gain 1 point;
Otherwise, they performed normally and there is no change in points.
Return the total number of points the dieter has after all calories.length days.

Note that: The total points could be negative.

Example 1:

Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
Output: 0
Explaination: calories[0], calories[1] < lower and calories[3], calories[4] > upper, total points = 0.
Example 2:

Input: calories = [3,2], k = 2, lower = 0, upper = 1
Output: 1
Explaination: calories[0] + calories[1] > upper, total points = 1.
Example 3:

Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
Output: 0
Explaination: calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, total points = 0.

Constraints:

1 <= k <= calories.length <= 105
0 <= calories[i] <= 20000
0 <= lower <= upper
 * 
 */
class Solution {
	// Time 0(N) constant space
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
    	int n = calories.length;
    	int start = 0, ans = 0;
    	int sum = 0;
    	for(int end = 0;end < n;end++) {
    		sum += calories[end];
    		if(end-start+1 > k)
    			sum -= calories[start++];
    		// we need exact k sequence
    		if(end-start+1 < k)
    			continue;
    		ans += sum > upper?1:sum < lower?-1:0;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []calories = {1,2,3,4,5};
		int k = 1, lower = 3, upper = 3;*/
		/*int []calories = {3,2};
		int k = 2, lower = 0, upper = 1;*/
		int []calories = {6,5,0,0};
		int k = 2, lower = 1, upper = 5;
		System.out.println(new Solution().dietPlanPerformance(calories, k, lower, upper));
	}
}