package main;

// https://leetcode.com/problems/soup-servings/discuss/121711/C%2B%2BJavaPython-When-N-greater-4800-just-return-1

class Solution {
	double[][] memo = new double[200][200];
    public double soupServings(int N) {
    	// converting 25 ml into 1 50ml ->2 100ml -> 4
    	// taking ceil with n+24/25
        return N >= 4800 ?  1.0 : f((N + 24) / 25, (N + 24) / 25);
    }

    public double f(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1;
        if (b <= 0) return 0;
        if (memo[a][b] > 0) return memo[a][b];
        // similar to knight dialer, 1/4 of sum from where we can reach [a,b] 
        memo[a][b] = 0.25 * (f(a - 4, b) + f(a - 3, b - 1) + f(a - 2, b - 2) + f(a - 1, b - 3));
        return memo[a][b];
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 100;
		System.out.println(new Solution().soupServings(N));
	}
}
