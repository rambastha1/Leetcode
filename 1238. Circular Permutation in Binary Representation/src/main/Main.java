package main;

import java.util.ArrayList;
import java.util.List;
// https://cp-algorithms.com/algebra/gray-code.html
/* Let's look at the bits of number n and the bits of number G(n). Notice that i-th bit of G(n) equals 1 only when i-th bit of n equals 1 
 * and i+1-th bit equals 0 or the other way around (i-th bit equals 0 and i+1-th bit equals 1). Thus, G(n)=n⊕(n>>1):
 * 
 * i+1 bit because n>>1 shifts right
 */
class Solution {
    public List<Integer> circularPermutation(int n, int start) {
    	List<Integer> res = new ArrayList<>();
    	for(int i = 0;i < (1 << n);i++)
    		res.add(start ^ i ^ (i >> 1));
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 4, start = 10;
		System.out.println(new Solution().circularPermutation(n, start));
	}
}
