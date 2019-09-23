package main;

import java.util.List;

/* This is opposite of problem 339
 * https://www.cnblogs.com/liuliu5151/p/9880836.html
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Different from the previous question where weight is increasing from root to leaf, 
 * now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, 
 * and the root level integers have the largest weight.
 * 
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * 
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, 
 * and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */

interface NestedInteger {
	// Constructor initializes an empty nested list.
	//public NestedInteger();

	// Constructor initializes a single integer.
	//public NestedInteger(int value);

	// @return true if this NestedInteger holds a single integer, rather than a nested list.
	boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	int getInteger();

	// Set this NestedInteger to hold a single integer.
	public void setInteger(int value);

	// Set this NestedInteger to hold a nested list and adds a nested integer to it.
	public void add(NestedInteger ni);

	// @return the nested list that this NestedInteger holds, if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	List<NestedInteger> getList();
}

/*
 * 1. We can observe that  
 * 1x + 2y + 3z  = (x+y+z) * (3+1)    -   (3x+2y+1z)
 *                 ^ levelSum  ^maxDepth   ^ Nested List Weight Sum I problem
 * 2. Use DFS recursion, converting this problem to Nested List Weight Sum I,  
 * updating levelSum and maxPath at the same time when using DFS
 * 
 * level sum is total level sum notice levelsum += n.GetInteger();
 */

class Solution {
	int levelsum = 0, maxdepth = 1;	
	public int depthSumInverse(List<NestedInteger> nestedList) {
		//depthsum is problem 339
		int depthsum = dfs(nestedList, 1);
		return levelsum*(maxdepth+1) - depthsum;
	}
	
	int dfs(List<NestedInteger> nestedList, int depth) {
		int sum = 0;
		for(NestedInteger n : nestedList) {
			if(n.isInteger()) {
				sum += n.getInteger()*depth;
				maxdepth = Math.max(depth, maxdepth);
				levelsum += n.getInteger();
			} else {
				sum += dfs(n.getList(), depth+1);
			}
		}
		return sum;
	}
}

public class Main {
	public static void main(String[] args) {

	}
}