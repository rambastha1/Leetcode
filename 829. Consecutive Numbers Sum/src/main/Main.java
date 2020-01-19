package main;

// https://codeforces.com/blog/entry/53763
// https://leetcode.com/problems/consecutive-numbers-sum/discuss/129015/5-lines-C%2B%2B-solution-with-detailed-mathematical-explanation.
class Solution {
    public int consecutiveNumbersSum(int N) {
    	int ans = 1;
    	for(int i = 2;i < Math.sqrt(2*N);i++) {
    		if((N - (i*(i-1))/2)%i == 0)
    			ans++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 5;
		System.out.println(new Solution().consecutiveNumbersSum(N));
	}
}
