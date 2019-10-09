package main;

import java.util.ArrayList;
import java.util.List;

/* Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note:
 * Each combination’s factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * For example:Input: 12
 * Output: [[2, 2, 3], [2, 6], [3, 4]]
 * Input: 15
 * Output: [[3, 5]]
 * Input: 28
 * Output: [[2, 2, 7], [2, 14], [4, 7]]
 * 
 */

// Similar to combination sum problem
class Solution {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(n, res, new ArrayList<>(), 2);
		//res.remove(res.size()-1);
		return res;
	}
	
	void dfs(int n, List<List<Integer>> res, List<Integer> curr, int num) {
		if(n == 1) {
			if(curr.size() > 1)
				res.add(new ArrayList<>(curr));
			return;
		}
		
		for(int i = num;i*i <= n;i++) {
			if(n%i == 0) {
				curr.add(i);
				n /= i;
				dfs(n, res, curr, i);
				n *= i;
				curr.remove(curr.size()-1);
			}
				
		}
	}
}

public class Main {
	public static void main(String[] args) {
		//int n = 8;
		//int n = 12;
		//int n = 15;
		int n = 28;
		System.out.println(new Solution().getFactors(n));
	}
}
