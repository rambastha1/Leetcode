package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree 
 * whose values sum up to a given integer target.

Example 1:

Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.
Example 2:



Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false
 

Constraints:

Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9
 * 
 * https://leetcode.com/problems/two-sum-bsts/discuss/397624/Simple-Stack-Solution
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
    	Map<Integer, Integer> map = new HashMap<>();
    	traverse(root1, map, target);
    	return check(root2, map);
    }
    
    private void traverse(TreeNode root, Map<Integer, Integer> map, int target) {
    	if(root == null)
    		return;
    	map.put(target - root.val, map.getOrDefault(root.val, 0) + 1);
    	traverse(root.left, map, target);
    	traverse(root.right, map, target);
    }
    
    private boolean check(TreeNode root, Map<Integer, Integer> map) {
    	if(root == null)
    		return false;
    	if(map.containsKey(root.val))
    		return true;
    	return check(root.left, map) || check(root.right, map);
    }
    
    // Iterative Approach
    /* Maintain two stack one for each tree, traverse tree1 in inorder tree2 from max to mn
     * and two sum if sum == target return true
     * if less, move tree1 to tree1.right else tree2 to tree2.left
     */
    public boolean twoSumBSTs1(TreeNode root1, TreeNode root2, int target) {
    	if(root1 == null || root2 == null)
    		return false;
    	Stack<TreeNode> s1 = new Stack<>();
    	Stack<TreeNode> s2 = new Stack<>();
    	
    	while(true) {
    		if(root1.left != null) {
    			s1.push(root1);
    			root1 = root1.left;
    		}
    		
    		if(root2.right != null) {
    			s2.push(root2);
    			root2 = root2.right;
    		}
    		
    		if(s1.isEmpty() || s2.isEmpty())
    			break;
    		
    		TreeNode node1 = s1.peek(), node2 = s2.peek();
    		
    		if(node1.val + node2.val == target) {
    			return true;
    		} else if(node1.val + node2.val < target) {
    			s1.pop();
    			root1 = node1.right;
    		} else {
    			s2.pop();
    			root2 = node2.left;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}