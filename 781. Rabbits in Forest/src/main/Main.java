package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	public int numRabbits(int[] answers) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : answers)
            m.put(i, m.getOrDefault(i, 0) + 1);
        int res = 0;
        for (int i : m.keySet())
            res += (m.get(i) + i) / (i + 1) * (i + 1);
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []answers = {0,2,0,2,1};
		System.out.println(new Solution().numRabbits(answers));
	}
}
