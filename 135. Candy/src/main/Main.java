package main;
import java.util.Arrays;
// https://leetcode.com/articles/candy/
class Solution {
	
	public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int ans = 1;
        int up = 1, down = 0, peak = up;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                up++;
                peak = up;
                down = 0;
                ans += up;
            } else if (ratings[i] < ratings[i - 1]) {
                down++;
                ans += down < peak ? down : down + 1;
                up = 1;
            } else {
                ans++;
                up = 1;
                down = 0;
                peak = up;
            }
        }
        return ans;
    }
	
    public int candy1(int[] ratings) {
    	int n = ratings.length;
    	if(n == 1)
    		return 1;
    	int []res = new int[n];
    	Arrays.fill(res, 1);
    	for(int i = 1;i < n;i++) {
    		if(ratings[i] > ratings[i-1])
    			res[i] = res[i-1] + 1;
    	}
    	
    	int sum = res[n-1];
    	for(int i = n-2;i >= 0;i--) {
    		if(ratings[i] > ratings[i+1])
    			res[i] = Math.max(res[i], res[i+1] + 1);
    		sum += res[i];
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		//int[] ratings = {1,6,10,8,7,3,2};
		int[] ratings = {0,1,2,3,2,1};
		System.out.println(new Solution().candy(ratings));
	}
}
