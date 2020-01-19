package main;

import java.math.BigInteger;
// https://leetcode.com/problems/smallest-good-base/discuss/96587/Python-solution-with-detailed-mathematical-explanation-and-derivation
// https://leetcode.com/problems/smallest-good-base/discuss/440447/Java-solution-with-explaination
class Solution {
	public String smallestGoodBase(String n) {
        /**
            Formula for presenting a number as a base of x is x^(k - 1) + x^(k - 2) + .... + x + 1 = n 
            After muliplying both side by (x - 1) we get (x^k) - 1 = n*(x - 1)
            
            So we have to find smallest base x, for k from 2 to Math.log(1e18) for given n by using equation 
            (x^k) - 1 = n*(x - 1)
            
            Why k range  from 2 to Math.log(1e18) / Math.log(2)
            As per problem statement k >= 2  which we take a minimum value of k
            And n is in range [3, 10^18] which sets a limit on max value of k as Math.log(1e18) / Math.log(2)
        */
        BigInteger N = new BigInteger(n);
        int kMin = 2;
        int kMax = (int)Math.ceil(Math.log(1e18) / Math.log(2));
        
        for(int k = kMax; k > kMin; k--) {
            long lo = 2;
            long hi = Long.parseLong(n) + 1;
            
            //equation (x^k) - 1 = n * (x - 1)
            while(lo <= hi) {
                long mid = lo + (hi - lo) / 2;
                
                BigInteger lhs = BigInteger.valueOf(mid).pow(k).subtract(BigInteger.ONE);
                BigInteger rhs = N.multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE));
                
                int val = lhs.compareTo(rhs);
                if(val == 0)
                	return mid + "";
                if(val < 0) 
                	lo = mid + 1;
                else if(val > 0) 
                	hi = mid - 1;
            }
        }
        return (Long.parseLong(n) - 1) + "";
    }
}

public class Main {
	public static void main(String[] args) {
		String n = "13";
		System.out.println(new Solution().smallestGoodBase(n));
	}
}
