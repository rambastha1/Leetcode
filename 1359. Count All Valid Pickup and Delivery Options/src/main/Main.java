package main;
// https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/discuss/516951/Simple-Math-Formula-with-Explanation
class Solution {
    public int countOrders(int n) {
    	long ans = 1;
    	int mod = (int)1e9 + 7;
    	for(int i = 1;i <= n;i++) {
    		int space = (i-1)*2 + 1;
    		ans *= (space * (space+1))/2;
    		ans %= mod;
    	}
    	return (int)ans%mod;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		System.out.println(new Solution().countOrders(n));
	}
}
