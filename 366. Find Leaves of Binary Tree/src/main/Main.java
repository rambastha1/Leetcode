package main;
import java.util.*;

/*
 * Given a binary tree, collect a tree's nodes as if you were doing this: 
 * Collect and remove all leaves, repeat until the tree is empty.
 * Example:
 * Given binary tree 

          1
         / \
        2   3
       / \     
      4   5    
 * Returns [4, 5, 3], [2], [1].
 * Explanation:
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
          1
         / 
        2          
 * 2. Now removing the leaf [2] would result in this tree:

          1          
 * 3. Now removing the leaf [1] would result in the empty tree:

          []         
 * Returns [4, 5, 3], [2], [1].
 */

class Solution {
	
	public class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	void levelorder(TreeNode node) {
    	if(node == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(node);
    	while(!q.isEmpty()) {
    		TreeNode n = q.poll();
    		System.out.print(n.val + " ");
    		if(n.left != null)
    			q.offer(n.left);
    		if(n.right != null)
    			q.offer(n.right);
    	}
    }
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		height(root, res);
		return res;
	}
	
	public int height(TreeNode root, List<List<Integer>> res) {
		/*
		 * -1 is returned so that for the first time res.add(new ArrayList<>())
		 * is done list is created at index 0 of res. otherwise if 0 is returned
		 * it will be created from index 1;
		 * thus res.get(h) will throw null pointer exception 
		 */
		if(root == null)
			return -1;
		int h = 1 + Math.max(height(root.left, res), height(root.right, res));
		if(res.size() < h+1)
			res.add(new ArrayList<>());
		res.get(h).add(root.val);
		return h;
	}
}


public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		System.out.println(s.findLeaves(s.root));
	}
}