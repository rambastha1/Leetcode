package main;

class Solution {
	/* find number of magical numbers less than LCM say its x
	 * number less than 2*LCM = 2x and so on it repeats
	 * number less than num = numbers divisible by A + divisible by B - divisible by both i.e LCM
	 * we need to find least number such that num = N
	 * https://leetcode.com/articles/nth-magical-number/
	 */
	// 0(lgN*min(A,B))
	public int nthMagicalNumber(int N, int A, int B) {
        int MOD = 1_000_000_007;
        int L = A / gcd(A, B) * B;

        long lo = 0;
        long hi = (long) 1e15;
        while (lo < hi) {
            long mi = lo + (hi - lo) / 2;
            // If there are not enough magic numbers below mi...
            if (mi / A + mi / B - mi / L < N)
                lo = mi + 1;
            else
                hi = mi;
        }

        return (int) (lo % MOD);
    }
	
	int gcd(int a, int b) {
		if(a%b==0)
			return b;
		return gcd(b, a%b);
	}
	
	// like ugly number 0(N)
    public int nthMagicalNumber1(int N, int A, int B) {
    	int p1 = 1, p2 = 1, num = 0;
    	int mod = (int)1e9;
    	for(int i = 0;i < N;i++) {
    		int prod1 = (p1*A)%mod, prod2 = (p2*B)%mod;
    		num = Math.min(prod1, prod2)%mod;
    		if(num == prod1 && num == prod2) {
    			p1++;
    			p2++;
    		} else if(num == prod1)
    			p1++;
    		else
    			p2++;
    	}
    	return num%mod;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 1000000000, A = 40000, B = 40000;
		System.out.println(new Solution().nthMagicalNumber(N, A, B));
	}
}
