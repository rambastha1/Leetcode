package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// amazing article
// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/116107/Java-Better-than-O(NlogN)-it-can-be-O(KlogK) 
/* why maximum fraction less than mid
 * To see why the binary search algorithm is guaranteed to find a solution that is the kth-smallest prime fraction, 
 * first note that, if the given k is valid (i.e., 1 <= k <= n^2), then the kth-smallest prime fraction must exist. 
 * Furthermore, if there are no duplicates (which is the case here), then the kth-smallest prime fraction will be the element 
 * in the matrix such that there are exactly k elements no greater than it. This implies if we can find a candidate fraction 
 * such that it satisfies the following two conditions:

The fraction is an element of the matrix;
There are exactly k elements in the matrix that are no greater than it;
then we can conclude the candidate fraction must be the kth-smallest prime fraction. Now if you look more carefully at our 
binary search algorithm, it is guaranteed to find a candidate fraction such that the second condition above is met 
(because the binary search loop will not terminate until such a fraction is found, and such a fraction must exist if the given k is valid). 
However, we cannot just return the found candidate fraction as the first condition may not be satisfied 
(i.e., the candidate fraction may not be an element of the matrix). So how do we find the fraction that meets the two conditions 
simultaneously? This is where the "maximum fraction" comes into play. First note that the maximum fraction always comes from 
the matrix itself, so the first condition is met automatically. Second, assume now our binary search loop has found a candidate 
fraction m such that the second condition is met, and the corresponding maximum fraction is p/q, then it's easy to show that 
there are also exactly k elements in the matrix that are no greater than p/q (that is, for any fraction f in the matrix such 
that f <= m, we must also have f <= p/q, because the matrix cannot contain any fraction in the range (p/q, m]). 
Therefore the maximum fraction p/q will meet the above two conditions simultaneously and we conclude it is the kth-smallest prime 
fraction in the matrix.

So in summary, as long as the given k is valid and there are no duplicates in the matrix, our binary search loop is guaranteed to 
find a fraction that satisfies the second condition, and the corresponding maximum fraction is then the kth-smallest prime fraction 
we are looking for.
 * 
 */


class Solution {
	
	// Time 0(nlgn) Space 0(1)
	// Binary Search works only if there are no duplicates fraction
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
