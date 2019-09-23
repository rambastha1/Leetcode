package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	
	/* 0(NlgN) 
	 * https://leetcode.com/problems/minimum-increment-to-make-array-unique/discuss/197713/C%2B%2B-concise-solution-O(nlogn)-complexity-with-explanation-and-example-step-by-step
	 */
	public int minIncrementForUnique(int[] A) {
		int n = A.length;
		int res = 0;
		if(n < 2) 
			return 0;
		Arrays.sort(A);
		for(int i = 1;i < n;i++) {
			// update the A[i] by A[i-1]+1
			// thus res by new number - old number i.e A[i-1] + 1 - A[i] 
			if(A[i] <= A[i-1]) {
				res += A[i-1] + 1 - A[i];
				A[i] = A[i-1] + 1;
			}
		}
		return res;
	}
	
	
	// 0(N) Time 0(N) space
	// https://leetcode.com/articles/minimum-increment-to-make-array-unique/
	public int minIncrementForUnique1(int[] A) {
		int []count = new int[100000];
		for(int a : A)
			count[a]++;
		
		int ans = 0, taken = 0;
		for(int i = 0;i < 100000;i++) {
			if(count[i] >= 2) {
				taken += count[i] - 1;
				ans -= i *(count[i] - 1);
			} else if(taken > 0 && count[i] == 0) {
				taken--;
				ans += i;
			}
		}
		return ans;
	}
	
	// 0(nlgN) Time 0(N) space	
    /*public int minIncrementForUnique(int[] A) {
    	if(A == null || A.length <= 1)
    		return 0;
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int a : A)
    		map.put(a, map.getOrDefault(a, 0)+1);
    	Arrays.sort(A);
    	
    	int ans = 0, temp = A[0], i = 0;
    	for(i = 0;i < A.length;i++) {
    		if(map.get(A[i]) > 1) {
    			while(temp < A[i] || map.containsKey(temp))
    				temp++;
    			ans += temp-A[i];
    			map.put(A[i], map.get(A[i])-1);
    			map.put(temp, 1);
        		break;
    		}    		
    	}    	
    	if(i == A.length) return ans;
    	i++;
    	for(;i < A.length;i++) {
    		if(map.get(A[i]) > 1) {
    			while(temp < A[i] || map.containsKey(temp)) {
    				temp++;
    			}
    			ans += temp - A[i];
    			map.put(A[i], map.get(A[i])-1);
    			map.put(temp, 1);
    		}
    	}
    	return ans;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int []A = {3,2,1,2,1,7};
		//int []A = {0,2,2};
		System.out.println(new Solution().minIncrementForUnique(A));
	}
}
