package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	
	/* https://leetcode.com/problems/binary-trees-with-factors/discuss/126277/Concise-Java-solution-using-HashMap-with-detailed-explanation.-Easily-understand!!!
	 * Time 0(N^2) Space 0(N)
	 * each integer A[i] will always have one tree with only itself
	 * if A[i] has divisor (a) in the map, and if A[i]/a also in the map
	 * then a can be the root of its left subtree, and A[i]/a can be the root of its right subtree;
	 * the number of such tree is map.get(a) * map.get(A[i]/a)
	 */
	
	public int numFactoredBinaryTrees(int[] A) {
        Arrays.sort(A);
        Map<Integer, Long> map = new HashMap();
        map.put(A[0], 1L);
        
        for (int i = 1; i < A.length; i++) {
        	long count = 1;
            for (Integer num : map.keySet()) {
                if (A[i] % num == 0 && map.containsKey(A[i] / num)) {
                    count += map.get(num) * map.get(A[i] / num);
                }
            }
            map.put(A[i], count);
        }
        long sum = 0;
        for (Integer n : map.keySet()) {
            sum = (sum + map.get(n)) % ((int) Math.pow(10, 9) + 7) ;
        }
        return (int) sum;
    }   
}

public class Main {
	public static void main(String[] args) {
		int []A = {2, 4, 5, 10};
		System.out.println(new Solution().numFactoredBinaryTrees(A));
	}
}
