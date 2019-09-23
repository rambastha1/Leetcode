package main;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/longest-subarray-sum-divisible-k/

class Solution {
	public int subarraysDivByK(int[] A, int k) {
		if(A == null || A.length == 0)
			return 0;
		int n = A.length;
		int []mod = new int[n];
		int sum = 0;
		for(int i = 0;i < n;i++) {
			sum += A[i];
			mod[i] = ((sum%k) + k)%k;
		}
		// mod -> index
		Map<Integer, Integer> map = new HashMap<>();
		int maxlen = 0;
		for(int i = 0;i < n;i++) {
			// This case when array from index 0-i is divisible by k
			if(mod[i] == 0)
				maxlen = i+1;
			
			/* it works because suppose two indices i, j s.t. i < j
			 * mod[i] == mod[j] != 0
			 * it means if we remove sum till index i from sum till index j
			 * we get sum from index i+1 to j which is divisble by k and thus the length
			 * of subarray. we need maximum of such length
			 * 
			 * For eg Array {2, 7, 6, 1, 4, 5}
			 * Sum array     2, 9, 15,16,20,25
			 * Mod Array     2, 0, 0, 1, 2, 1
			 * if we remove sum0(mod = 2) from sum4(mod = 2) we get 18 which is divisible by k
			 */
			
			if(!map.containsKey(mod[i]))
				map.put(mod[i], i);
			else {
				maxlen = Math.max(maxlen, i - map.get(mod[i]));
			}
		}		
		return maxlen;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {2, 7, 6, 1, 4, 5};
		int k = 3;
		
		
		/*int []A = {-2, 2, -5, 12, -11, -1, 7};
		int k = ?;*/
		System.out.println(new Solution().subarraysDivByK(A, k));
	}
}