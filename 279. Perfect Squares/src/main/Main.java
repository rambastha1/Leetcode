package main;

class Solution {
	
	/*
	 * Lagrange’s Four Square Theorem states that 
	 * Every positive integer can be written as the sum of at most four squares
	 * 0(sqrt(N)) solution Efficient Solution
	 */
	
	/*
	 * Euler's Four Square Identity
	 * Product of 2 numbers can be  written as sum of 4 squares
	 */
	 
	public boolean isSquare(int n) {
        double sqrt = Math.sqrt(n);
        int x = (int) sqrt;
        return (Math.pow(sqrt, 2) == Math.pow(x, 2));
    }
	
    public int lagrange_numSquares(int num) {
    	// case:1
        // If answer is 1 => sum is perfect square
        if (isSquare(num))
            return 1;

        //case:2
        /*If answer is 2 => one factor has to be (int)square_root and remaining other 
         * number has to be a perfect square
         */
        int sqrt = (int) (Math.sqrt(num));
        for (int i = 1; i <= sqrt; i++) {
            if (isSquare(num - i * i))
                return 2;
        }

        //case:3
        //Let's come back to this in a min

        //case:4
        /*a positive integer can be expressed as the sum of three squares if and only if 
         * it is not of the form 4^k(8m+7) for some int k and m
         */
        
        while (num % 4 == 0) //handling 4^k
            num = num / 4;
        if (num % 8 == 7) //handling 8m+7
            return 4;

        // if none of above, case 3
        return 3;
    }
    
    /*
	 * DP solution
	 * 0(N*N) Time 0(N) space
	 */
	public int numSquares(int n) {
		if(n == 0 || n == 1 || n == 2 || n == 3)
			return n;
		int []dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		
		for(int i = 4;i <= n;i++) {
			dp[i] = i;
			for(int k = 1;k <= i;k++) {
				int temp = k*k;
				if(temp > i)
					break;
				else
					dp[i] = Math.min(dp[i], 1+dp[i-temp]);
			}
		}
		return dp[n];
	}
    
    
    public void printFourSquares(int a) { 
        // loops checking the sum of squares 
        for (int i = 0; i * i <= a; i++) { 
            for (int j = i; j * j <= a; j++) { 
                for (int k = j; k * k <= a; k++) { 
                    for (int l = k; l * l <= a; l++) { 
      
                        // if sum of four squares equals  
                        // the given no. 
                        if (i * i + j * j + k * k + l * l == a) { 
      
                            // printing the numbers 
                            System.out.print( a + " = " + i + "*" + i  + " + " + j + "*" + j + " + "); 
                            System.out.println( k + "*" + k + " + " + l + "*" + l); 
                        } 
                    } 
                } 
            } 
        }
    }
    
}

public class Main {

	public static void main(String[] args) {
		System.out.println(new Solution().lagrange_numSquares(24));
		System.out.println(new Solution().numSquares(24));
	}
}