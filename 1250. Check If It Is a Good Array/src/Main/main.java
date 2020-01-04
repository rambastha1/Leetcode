package Main;

/* According to Bezout's Identity if gcd(a,b) = d, then there exists x and y for which ax+by = d
 * Here we need d to be 1, then if gcd of any two numbers is 1 then we have answer
 * we calculate gcd of whole array as if gcd of two numbers is 1 and gcd(1, num) is also 1
 */

class Solution {
    public boolean isGoodArray(int[] nums) {
    	int n = nums.length;
    	int a = nums[0];
    	
    	for(int i = 1;i < n;i++) {
    		a = gcd(a, nums[i]);
    	}
    	return a == 1;
    }
    
    private int gcd(int a, int b) {
    	if(a%b == 0)
    		return b;
    	return gcd(b, a%b);
    }
}

public class main {
	public static void main(String[] args) {
		int []nums = {12,5,7,23};
		System.out.println(new Solution().isGoodArray(nums));
	}
}
