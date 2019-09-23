package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
		Map<String, Integer> map = new HashMap<>();
		int ans = 0;
		for(int []domino : dominoes) {
			Arrays.sort(domino);
			String s = domino[0]+"#"+domino[1];
			map.put(s, map.getOrDefault(s, 0) + 1);
		}
		for(int val : map.values())
			ans += val>1?(val*(val-1))/2:0;
		return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]dominoes = {{1,2},{2,1},{3,4},{5,6}};
		System.out.println(new Solution().numEquivDominoPairs(dominoes));
	}
}
