package main;

import java.util.HashMap;
import java.util.Map;

/* https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation
 * state is boolean representation of already choosen numbers
 */

class Solution {
	// Time 0(2^N)
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    	if(desiredTotal <= 0)
    		return true;
    	// sum of first n numbers, will never reach desired total
    	if((maxChoosableInteger * (maxChoosableInteger+1))/2 < desiredTotal)
    		return false;
    	return dfs(desiredTotal, maxChoosableInteger, 0, new HashMap<>());
    }
    
    private boolean dfs(int desiredTotal, int maxChoosableInteger, int state, Map<Integer, Boolean> map) {
    	if(map.containsKey(state))
    		return map.get(state);
    	for(int i = 0;i < maxChoosableInteger;i++) {
    		// number already choosen
    		if((state & (1<<i)) != 0)
    			continue;
    		// player picks i, since it is 0-indexed, actual number is i+1
    		// no need to track turn as second part takes care that the other player doesn't win
    		if(desiredTotal <= i+1 || !dfs(desiredTotal-(i+1), maxChoosableInteger, state|(1<<i), map)) {
    			map.put(state, true);
    			return true;
    		}
    	}
    	map.put(state, false);
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int maxChoosableInteger = 15, desiredTotal = 60;
		System.out.println(new Solution().canIWin(maxChoosableInteger, desiredTotal));
	}
}
