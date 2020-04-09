package main;

import java.util.Arrays;
/* want to keep maximum satisfaction food at the last so sort
 * now running sum of satisfaction > 0 i.e sum > 0 because we don't want to cook less favorable food
 * res += sum adds remaining  
 */
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);
        int res = 0, sum = 0;
        for(int i = n-1;i >= 0 && (sum + satisfaction[i] > 0);i--) {
            sum += satisfaction[i];
            // this line is amazing just debug
            res += sum;
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []satisfaction = {-1,-8,0,5,-9};
		System.out.println(new Solution().maxSatisfaction(satisfaction));
	}
}
