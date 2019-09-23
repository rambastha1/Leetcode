package main;

import java.math.BigInteger;
import java.util.Arrays;

class Solution {
	
	
    public int numPrimeArrangements(int n) {
        int prime = count(n);
        long ans = 1;
        int MOD = 1000000007;
        for(int i = 1;i <= prime;i++)
        	ans = (ans*i)%MOD;
        
        for(int i = 1;i <= n-prime;i++)
        	ans = (ans*i)%MOD;
        
        return (int)ans;
    }
    // using this causes problem as n is huge
    private long factorial(int n) {
    	long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = (fact*i)%1000000007;
        }
        return fact;
    }
    
    private int count(int n) {
    	boolean []prime = new boolean[n+1];
    	Arrays.fill(prime, 2, n+1, true);
    	
    	for(int i = 2;i*i <= n;i++) {
    		if(prime[i]) {
    			for(int j = i*i;j <= n;j += i)
    				prime[j] = false;
    		}
    	}
    	
    	int count = 0;
    	for(int i = 2;i <= n;i++) {
    		if(prime[i])
    			count++;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 5;
		System.out.println(new Solution().numPrimeArrangements(n));
	}
}
