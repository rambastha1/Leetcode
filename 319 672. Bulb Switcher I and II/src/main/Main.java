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
    
    /*
     * There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. Initially, all the bulbs are turned off.

At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb change color to blue only if it is on and all 
the previous bulbs (to the left) are turned on too.

Return the number of moments in which all turned on bulbs are blue.
     */
    public int numTimesAllBlue(int[] light) {
        int s1 = 0, s2 = 0, ans = 0;
        for(int i = 0;i < light.length;i++) {
            s1 += i + 1;
            s2 += light[i];
            if(s1 == s2)
                ans++;
        }
        return ans;
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