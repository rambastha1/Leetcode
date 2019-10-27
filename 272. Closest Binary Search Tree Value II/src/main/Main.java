package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

import javafx.util.Pair;

/* Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * 
 */


// I used PQ earlier.. working but not very fast
class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
    	if(root == null)
    		return new ArrayList<>();
    	Deque<Integer> deque = new ArrayDeque<>();
    	util(root, deque);
    	while(deque.size() > k) {
    		if(Math.abs(deque.peekFirst() - target) > Math.abs(deque.peekLast() - target)) {
    			deque.pollFirst();
    		} else {
    			deque.pollLast();
    		}
    	}
    	return new ArrayList<>(deque);
    }
    
    private void util(TreeNode root, Deque<Integer> deque) {
    	if(root == null)
    		return;
    	
    	util(root.left, deque);
    	deque.add(root.val);
    	util(root.right, deque);
    }
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(1);
		s.root.left.right = s.new TreeNode(3);
		s.root.right = s.new TreeNode(5);
		
		double target = 3.714286;
		int k = 2;
		System.out.println(new Solution().closestKValues(s.root, target, k));
	}
}