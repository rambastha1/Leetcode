package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left, right, random;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	// https://www.geeksforgeeks.org/clone-binary-tree-random-pointers/
	// Time and Space 0(N) tree modification not allowed
	public TreeNode clone(TreeNode root) {
		Map<TreeNode, TreeNode> map = new HashMap<>();
		TreeNode node = copyleftright(root, map);
		copyrandom(root, node, map);
		return node;
	}
	
	private TreeNode copyleftright(TreeNode root, Map<TreeNode, TreeNode> map) {
		if(root == null)
			return null;
		TreeNode node = new TreeNode(root.val);
		map.put(root, node);
		node.left = copyleftright(root.left, map);
		node.right = copyleftright(root.right, map);
		return node;
	}
	
	private void copyrandom(TreeNode root, TreeNode clone, Map<TreeNode, TreeNode> map) {
		if(clone == null)
			return;
		clone.random = map.get(root.random);
		copyrandom(root.left, clone.left, map);
		copyrandom(root.right, clone.right, map);
	}
	
	// tree temporary modification allowed time 0(N) constant space
	// http://ideone.com/7U8Y0d
	
	public TreeNode clone1(TreeNode root) {
        if (root == null)
            return root;
 
        TreeNode clone = new TreeNode(root.val);
        clone.left = clone1(root.left);
        clone.right = clone1(root.right);
        
        // change root.random to point to clone
        clone.random = root.random;
        root.random = clone;
        return clone;
    }
	
	void restoreRandom(TreeNode root) {
		if (root == null)
			return;
		TreeNode clonedRoot = root.random;
		TreeNode origRandom = clonedRoot.random;
		
		if (origRandom == null)
			clonedRoot.random = null;
		else
			clonedRoot.random = origRandom.random;

		root.random = origRandom;
		restoreRandom(root.left);
		restoreRandom(root.right);
	}
	
	void levelorder(TreeNode root) {
		if(root == null)
			return;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0;i < size;i++) {
				TreeNode node = q.poll();
				System.out.print(node.val + " ");
				if(node.left != null)
					q.offer(node.left);
				if(node.right != null)
					q.offer(node.right);
			}
		}
		System.out.println();
	}
	
	public TreeNode root;
}


public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.right = s.new TreeNode(3);
		s.root.random = s.root.left.right;
		s.root.left.left.random = s.root;
		s.root.left.right.random = s.root.right;
		
		s.levelorder(s.root);
		/*Solution.TreeNode clone = s.clone(s.root);
		s.levelorder(clone);*/
		
		Solution.TreeNode clone1 = s.clone1(s.root);
		s.restoreRandom(s.root);
		s.levelorder(clone1);
	}
}
