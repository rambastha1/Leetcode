package main;

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
    	if(m == 0 || n == 0)
    		return 0;
    	// Brian Kerninghan Algorithm just make n equal to m as n >= m
    	// after certain operation all set bits will become 0 
    	// at the end just return n
    	while(n > m)
    		n &= (n-1);
    	return n;
    }
}

public class Main {
	public static void main(String[] args) {
		int m = 1, n = 2;
		System.out.println(new Solution().rangeBitwiseAnd(m, n));
	}
}
