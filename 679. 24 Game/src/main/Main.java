package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/24-game/discuss/107673/JAVA-Easy-to-understand.-Backtracking.
/* Thanks to (, ) operators, we don't take order of operations into consideration.

Let's say, we pick any two numbers, a and b, and apply any operator +, -, *, /, 
assuming that the expression is surrounded with parenthesis, e.g. (a + b). 
Then, the result of (a + b) instead of a and b would participate in the following calculations.
 * 
 */
class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int i : nums) {
            list.add((double) i);
        }
        return dfs(list);
    }

    private boolean dfs(List<Double> list) {
        if (list.size() == 1) {
           return Math.abs(list.get(0)- 24.0) < 0.001?true:false;
        }
        
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
            	double a = list.get(i), b = list.get(j);
            	List<Double> temp = Arrays.asList(a + b,a-b,b-a,a*b,a/b,b/a);
                for (double c : temp) {
                    List<Double> nextRound = new ArrayList<>();
                    nextRound.add(c);
                    for(int k = 0; k < list.size(); k++) {
                        if(k == j || k == i) 
                        	continue;
                        nextRound.add(list.get(k));
                    }
                    if(dfs(nextRound)) 
                    	return true;
                }
            }
        }
        return false;

    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {4,1,8,7};
		System.out.println(new Solution().judgePoint24(nums));
	}
}
