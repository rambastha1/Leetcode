package main;

import java.util.Arrays;
/* suppose num = 123
 * we need two factor with minimum difference
 * two square roots - difference = 0
 * as we move away from square roots to 1, difference between factors increases
 * thus first found value is answer i.e minimum difference
 * 1*100    ^
 * 2*50     |
 * 4*25     |
 * 5*20     |  (Increasing distance as we go up)
 * 10*10 <- sqrt, we see repetitions after this point (Also notice this is the closest)
 * 20*5 
 * 25*4 
 * 2*50 
 * 100*1
 */
class Solution {
    public int[] closestDivisors(int num) {
    	for(int i = (int)Math.sqrt(num+2); i >= 1;i--) {
    		if((num+1)%i == 0)
    			return new int[] {i, (num+1)/i};
    		if((num+2)%i == 0)
    			return new int[] {i, (num+2)/i};
    	}
    	return new int[0];
    }
}

public class Main {
	public static void main(String[] args) {
		int num = 123;
		System.out.println(Arrays.toString(new Solution().closestDivisors(num)));
	}
}
