package main;

import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/least-operators-to-express-number/discuss/208428/Java-DFS-with-memoization
class Solution {
	Map<Integer, Integer> map = new HashMap<>();
    public int leastOpsExpressTarget(int x, int target) {
        if (target == 1) 
            return x == 1 ? 0 : 1;
        if (map.containsKey(target))
            return map.get(target);
        // idea is to fill as many * as possible as it takes minimum operators
        long product = x;
        int count = 0;
        while (product < target) {
            count++;
            product *= x;
        }
        // there arise two scenarios
        
        // candidate1 : in the form : x*x*...*x - (......) = target, product larger than target
        int cand1 = Integer.MAX_VALUE;
        if (product == target)
            cand1 = count;
        else if (product - target < target)
            cand1 = count + leastOpsExpressTarget(x, (int)(product - target)) + 1;
        
        // candidate2 : in the form : x*x*...*x + (......) = target, product smaller than target
        int cand2 = Integer.MAX_VALUE;
        product /= x;
        /* count == 0 means x is greater or equal to target, so we use x / x + (......) to continue the search, 
         * there are two more operators which are "/" and "+", so we add 2. If count != 0, 
         * count-1 represents the number of operators used in x * x * ... * x, we need one more operator "+" to connect it with (......), 
         * so we should add count - 1 + 1 = count.
         */
        cand2 = leastOpsExpressTarget(x, (int)(target - product)) + (count == 0 ? 2 : count);
        int res = Math.min(cand1, cand2);
        map.put(target, res);
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int x = 3, target = 19;
		System.out.println(new Solution().leastOpsExpressTarget(x, target));
	}
}
