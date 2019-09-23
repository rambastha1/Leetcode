package main;

/*
 * Extrapolate the square to right... the ray will meet corner if both x and y coordinates are
 * integers.. if it hits anywhere in the wall, q is not integer.. Thus find k for which 
 * k*gcd(p,q) = p. 
 * With every extrapolate imagine rotating the square anti clockwise 
 */

class Solution {
    public int mirrorReflection(int p, int q) {
    	int gcd = gcd(p, q);
    	p /= gcd;
    	p %= 2;
    	
    	q /= gcd;
    	q %= 2;
    	if(p == 1 && q == 1)
    		return 1;
    	return (p == 1)?0:2;
    }
    
    int gcd(int a, int b) {
    	if(a%b == 0)
    		return b;
    	return gcd(b, a%b);
    }
}

public class Main {
	public static void main(String[] args) {
		int p = 5, q = 1;
		System.out.println(new Solution().mirrorReflection(p, q));
	}
}