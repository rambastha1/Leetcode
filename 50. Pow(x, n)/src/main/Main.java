package main;

class Solution {
	public double myPow(double x, int n) {
        if(n == Integer.MIN_VALUE && x > 1)
            return 0;
        if(n == 0)
            return 1;
        if(n < 0) {
            n = -n; //consider the overflow situation
            x = 1/x;
        }
        //square number and power/2 (x^2)n/2 = x^n
        return (n%2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }
}

public class Main {
	public static void main(String[] args) {
		int x = -1, n = Integer.MIN_VALUE;
		System.out.println(new Solution().myPow(x, n));
	}
}
