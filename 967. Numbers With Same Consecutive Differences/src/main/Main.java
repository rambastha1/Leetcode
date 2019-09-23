package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// https://leetcode.com/problems/numbers-with-same-consecutive-differences/discuss/211183/JavaC%2B%2BPython-Iterative-Solution
class Solution {
	public int[] numsSameConsecDiff(int N, int K) {
		
		List<Integer> res = Arrays.asList(0,1,2,3,4,5,6,7,8,9); 
		for(int i = 2;i <= N;i++) {
			List<Integer> curr = new ArrayList<>();
			for(int x : res) {
				int y = x%10;
				if(x > 0 && y+K < 10)
					curr.add(x*10 + y + K);
				if(x > 0 && K > 0 && y-K >= 0)
					curr.add(x*10 + y - K);
			}
			res = curr;
		}
		int []ans = new int[res.size()];
		for(int i = 0;i < res.size();i++)
			ans[i] = res.get(i);
		System.out.println(res);
		return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 5, K = 1;
		new Solution().numsSameConsecDiff(N, K);
	}
}
