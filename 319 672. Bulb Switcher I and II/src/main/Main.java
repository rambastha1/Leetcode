package main;


/* sqrt of N is largest root till N.. It means all numbers lower than R say a*a < N 
 * (or can be square) Thus maximum perfect squares = math.sqrt(N)
 * 
 */
class Solution {
    public int bulbSwitch1(int n) {
    	if(n == 0)
    		return 0;
    	return (int)Math.sqrt(n);
    }
    
    
    //https://leetcode.com/articles/bulb-switcher-ii/
    public int flipLights(int n, int m) {
    	if(n == 0)
    		return 0;
    	n = Math.min(n, 3);
    	if(m == 0) return 1;
    	if(m == 1) return (n == 1)?2:(n==2)?3:4;
    	if(m == 2) return (n == 1)?2:(n==2)?4:7;    	
    	return (n==1)?2:(n==2)?4:8;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		System.out.println(new Solution().bulbSwitch1(n));
		
		int m = 1;
		System.out.println(new Solution().flipLights(n, m));
	}
}