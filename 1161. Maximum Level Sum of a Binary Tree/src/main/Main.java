package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        int level = 1, max = Integer.MIN_VALUE, curLevel = 1;
        while (!q.isEmpty()) {
            int size = q.size(), sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            if (sum > max) {
                max = sum;
                level = curLevel;
            }
            curLevel++;
        }
        return level;
    }
	
	public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(7);
		s.root.left.left = s.new TreeNode(7);
		s.root.left.right = s.new TreeNode(-8);
		s.root.right = s.new TreeNode(0);
		System.out.println(s.maxLevelSum(s.root));
	}
}
