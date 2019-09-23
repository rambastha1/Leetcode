package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	
    	if(gas.length == 0 || cost.length == 0 || (gas.length == 1 && gas[0] < cost[0]))
    		return -1;
    	int len = gas.length;
    	int start = 0, debt = 0, remain = 0;
    	for(int i = 0;i < len;i++) {
    		remain += gas[i] - cost[i];
    		if(remain < 0) {
    			debt += remain;
    			start = i+1;
    			remain = 0;
    		}
    	}
    	return (remain + debt) < 0?-1:start;
    }
}

public class Main {
	public static void main(String[] args) {
		int []gas = {3,1,1};
		int []cost = {1,2,2};
		System.out.println(new Solution().canCompleteCircuit(gas, cost));
	}
}