package main;

import java.util.HashSet;
import java.util.Set;

/* https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260936/Detailed-Explanation-using-Modular-Arithmetic
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260852/JavaC%2B%2BPython-O(1)-Space-with-Proves-of-Pigeon-Holes
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260875/Python-O(K)-with-Detailed-Explanations
 */

/* rem will be 0, 1, 11, 111, 1111
 * There can be at most k-1 remainders
 * create numbers take mod, if 0 -> divisible
 */

class Solution {
    public int smallestRepunitDivByK(int K) {
    	if(K%2 == 0 || K%5==0)
    		return -1;
    	if(K == 1)
    		return 1;
    	int rem = 0;
    	// max size k-1 as there can be k-1 different remainders
    	
    	//Set<Integer> set = new HashSet<>();
    	for(int N = 1;N <= K;N++) {
    		rem = (10*rem+1)%K;
    		if(rem == 0)
    			return N;
    		/*if(set.contains(rem))
    			return -1;
    		set.add(rem);*/
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int K = 17;
		System.out.println(new Solution().smallestRepunitDivByK(K));
	}
}
