package main;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a Binary Search Tree (BST) with root node root, and a target value V, 
 * split the tree into two subtrees where one subtree has nodes that are all smaller or 
 * equal to the target value, while the other subtree has all nodes that are greater than the 
 * target value.  It's not necessarily the case that the tree contains a node with value V.
 * 
 * Additionally, most of the structure of the original tree should remain.  
 * Formally, for any child C with parent P in the original tree, if they are both in the 
 * same subtree after the split, then node C should still have the parent P.
 * 
 * You should output the root TreeNode of both subtrees after splitting, in any order.
 * 
 * Example 1:
 * 
 * Input: root = [4,2,6,1,3,5,7], V = 2
 * Output: [[2,1],[4,3,6,null,null,5,7]]
 * 
 * Explanation:
 * Note that root, output[0], and output[1] are TreeNode objects, not arrays.
 * 
 * The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

 * while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1
 * 
 * 
 * Note:
 * The size of the BST will not exceed 50.
 * The BST is always valid and each node's value is different.
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		TreeNode() {};
	}
	
	public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode[] res = new TreeNode[2];
        if (root == null) return res;
        if (root.val <= V) {
            res[0] = root;
            TreeNode[] rightRes = splitBST(root.right, V);
            root.right = rightRes[0];
            res[1] = rightRes[1];
        } else {
            res[1] = root;
            TreeNode[] leftRes = splitBST(root.left, V);
            root.left = leftRes[1];
            res[0] = leftRes[0];
        }
        return res;
    }
	
	
	//My working code
    /*public TreeNode[] splitBST(TreeNode root, int V) {
    	TreeNode []res = new TreeNode[2];
    	util(root, root, root, res, V);    	
    	return res;
    }
    
    void util(TreeNode root, TreeNode node1, TreeNode node2, TreeNode[] res, int V) {
    	if(node1 == null && node2 == null)
    		return;
    	
    	if(node1 != null && node2 == null) {
    		res[0] = root;
    		res[1] = node2;
    		return;
    	}
    	
    	if(node1.val < V && node2.val > V) {
    		node1.right = null;
    		res[0] = root;
    		res[1] = node2;
    		return;
    	} else if(node1.val > V && node2.val < V) {
    		node1.left = null;
    		res[0] = root;
    		res[1] = node2;
    		return;
    	}
    	
    	if(node2.val == V) {
    		if(node2 == node1.left) {
    			node1.left = node2.right;
    		} else {
    			node1.right = node2.right;
    		}
    		node2.right = null;
    		res[0] = root;
    		res[1] = node2;
    		return;
    	}
    	
    	if(node2.val < V) {
    		util(root, node2, node2.right, res, V);
    	} else {
    		util(root, node2, node2.left, res, V); 
    	}
    }*/
    
    public TreeNode root;
    
    void levelorder(TreeNode root) {
    	if(root == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		System.out.print(node.val + " ");
    		if(node.left != null)
    			q.offer(node.left);
    		if(node.right != null)
    			q.offer(node.right);
    	}
    }
}


public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(6);
		s.root.left.left = s.new TreeNode(1);
		s.root.left.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(5);
		s.root.right.right = s.new TreeNode(7);
		
		/*s.root = s.new TreeNode(8);
		s.root.left = s.new TreeNode(6);
		s.root.right = s.new TreeNode(15);
		s.root.left.left = s.new TreeNode(3);
		s.root.left.right = s.new TreeNode(7);
		s.root.right.left = s.new TreeNode(13);
		s.root.right.right = s.new TreeNode(17);*/
		
		Solution.TreeNode []res = s.splitBST(s.root, 2);
		
		if(res[0] != null)
			s.levelorder(res[0]);
		System.out.println();
		if(res[1] != null)
			s.levelorder(res[1]);
	}
}
