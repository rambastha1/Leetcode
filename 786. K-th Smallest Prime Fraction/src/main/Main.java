package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// amazing article
// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/116107/Java-Better-than-O(NlogN)-it-can-be-O(KlogK) 


class Solution {
	
	// Time 0(nlgn) Space 0(1)
	// Binary Search
	public int[] kthSmallestPrimeFraction(int[] primes, int K) {
        double l = 0, r = 1;
        int[] ans = new int[]{0, 1};

        while (r - l > 1e-9) {
            double m = l + (r - l) / 2.0;
            int[] res = under(m, primes);
            
            if (res[0] < K) {
                l = m;
            } else {
                ans[0] = res[1];
                ans[1] = res[2];
                r = m;
            }
        }
        return ans;
    }

    public int[] under(double x, int[] primes) {
        // Returns {count, numerator, denominator}
        int numer = 0, denom = 1, count = 0, i = -1;
        
        // i runs n times overall thus overall time 0(n)
        for (int j = 1; j < primes.length; ++j) {
            // For each j, find the largest i so that primes[i] / primes[j] < x
            // It has to be at least as big as the previous i, so reuse it ("two pointer")
            while (primes[i+1] < primes[j] * x) 
            	i++;

            // There are i+1 fractions: (primes[0], primes[j]), (primes[1], primes[j]), ..., (primes[i], primes[j])
            count += i+1;
            // num/deno < p[i]/p[j] == num*p[j] < p[i]*deno
            if (i >= 0 && numer * primes[j] < denom * primes[i]) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{count, numer, denom};
    }
	
	// Time 0(nlgn) Space 0(n)
    public int[] kthSmallestPrimeFraction1(int[] A, int K) {
    	int n = A.length;
    	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return A[o1[0]]*A[o2[1]] - A[o1[1]]*A[o2[0]];
			}
		});
    	
    	pq.offer(new int[] {0, n-1});
    	while(K > 1 && !pq.isEmpty()) {
    		int []curr = pq.poll();
    		//if curr[1] is last element and curr[0] is not second last element
    		// minimum would be curr[0]+1/curr[1]
    		if(curr[1] == n-1 && curr[0] + 1 < curr[1]) {
    			pq.offer(new int[] {1+curr[0], curr[1]});
    		}
    		
    		if(curr[0] < curr[1]-1)
    			pq.offer(new int[] {curr[0], curr[1]-1});
    		K--;
    	}
    	if(pq.isEmpty())
    		return null;
    	return new int[] {A[pq.peek()[0]], A[pq.peek()[1]]};
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,2,3,5};
		int K = 3;
		System.out.println(Arrays.toString(new Solution().kthSmallestPrimeFraction(A, K)));
	}
}
