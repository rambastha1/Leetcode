package main;

import java.util.LinkedList;
import java.util.List;
/* every layer contains values from 2^d to 2^(d+1) - 1
 * get offset i.e distance from maximum node in that layer
 * add to previous layer to get actual parent,
 * now
 */
class Solution {
	// time 0(lgN)
    public List<Integer> pathInZigZagTree(int label) {
    	LinkedList<Integer> res = new LinkedList<>();
    	// res.add(0, label) is 0(1) amortized
    	res.addFirst(label);
    	
    	while(label != 1) {
    		int d = (int)(Math.log(label)/Math.log(2));
    		int offset = (int)(Math.pow(2, d+1)) - 1 - label;
// https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/discuss/323312/Simple-solution-in-java-(Using-properties-of-complete-binary-tree)-(O-log-N)
    		label = (int)(Math.pow(2, d) + offset)/2;
    		res.addFirst(label);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int label = 26;
		System.out.println(new Solution().pathInZigZagTree(label));
	}
}
