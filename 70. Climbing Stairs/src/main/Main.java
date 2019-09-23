package main;

public class Main {

	public static void main(String[] args) {
		int num = 4;
		//System.out.println(distinct(num));
		System.out.println(climbStairs(num));
	}
	
	public static int climbStairs(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        int []dp = new int[n+1];
        
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3;i <= n;i++)
            dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }

}
