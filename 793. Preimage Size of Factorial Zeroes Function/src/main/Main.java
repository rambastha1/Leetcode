package main;
/* If number of zero = K exists, total numbers of number having K zeroes can only be 5. a,a+1,a+2,a+3,a+4
 * if we add/subtract 5 a+5 has k+1 zeroes
 */
class Solution {
    public int preimageSizeFZF(int K) {
    	if(exist(K))
    		return 5;
    	return 0;
    }
    
    private boolean exist(int K) {
    	if(K == 0)
    		return true;
    	int l = 0, r = Integer.MAX_VALUE;
    	while(l <= r) {
    		int m = l + (r-l)/2;
    		int zero = count(m);
    		if(zero >= K) {
    			if(zero == K)
        			return true;
    			r = m-1;
    		} else
    			l = m+1;
    	}
    	return false;
    }
    
    private int count(int n) {
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
		int K = 1000000000;
		System.out.println(new Solution().preimageSizeFZF(K));
	}
}
