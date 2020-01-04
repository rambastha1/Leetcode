package main;
/* number of trailing zeroes means number of 0 + number of 5s
 * in 25 there are 2 5's
 * thus we need to find how many 5's, 25's 125's are there in numbers -> thus divide 
 * another way could be use a counter and after every iteration multiple y it by 5
 */
class Solution {
    public int trailingZeroes(int n) {
    	int ans = 0;
    	while(n > 0) {
    		n /= 5;
    		ans += n;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 37;
		System.out.println(new Solution().trailingZeroes(n));
	}
}
