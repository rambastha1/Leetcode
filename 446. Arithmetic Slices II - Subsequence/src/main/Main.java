package main;

import java.util.HashMap;
import java.util.Map;
/* 
 * https://www.geeksforgeeks.org/count-arithmetic-progression-subsequences-array/
 * https://leetcode.com/articles/arithmetic-slices-ii-subsequence/
 * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/discuss/92822/Detailed-explanation-for-Java-O(n2)-solution
 */
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
    	int n = A.length;
    	Map<Integer, Integer> []map = new HashMap[n];
    	
    	int res = 0;
    	for(int i = 0;i < n;i++) {
    		map[i] = new HashMap<>();
    		for(int j = 0;j < i;j++) {
    			long d = (long)A[i] - A[j];
    			if(d <= Integer.MIN_VALUE || d >= Integer.MAX_VALUE)
    				continue;
    			int diff = (int)d;
    			int c1 = map[i].getOrDefault(diff, 0);
    			int c2 = map[j].getOrDefault(diff, 0);
    			res += c2;
    			// The 1 appears here because of the property one, we can form a new weak arithmetic subsequence for the pair (i, j)
    			// c1 because there might be many i, j with common difference = d
    			// c2 because those weak AP become AP
    			map[i].put(diff, c1 + c2 + 1);
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,4,6,8,10};
		System.out.println(new Solution().numberOfArithmeticSlices(A));
	}
}
