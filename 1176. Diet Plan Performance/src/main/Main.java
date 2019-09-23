package main;

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