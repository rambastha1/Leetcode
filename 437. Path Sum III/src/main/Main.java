package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Tree {

	class TreeNode {
		int val;
		TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	TreeNode root, prev;

	private int num_path = 0;

    public int pathSum(TreeNode root, int sum) {
    	Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map, sum, 0);
        return num_path;
    }

    void dfs(TreeNode root, Map<Integer, Integer> map, int target, int sum) {
        if (root == null) return;

        sum += root.val;
        if (sum == target) num_path++;

        num_path += map.getOrDefault(sum - target, 0);
        //ADD
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        //Recursion
        dfs(root.left, map, target, sum);
        dfs(root.right, map, target, sum);
        //Backtrack
        map.put(sum, map.get(sum) - 1);
    }
	
	public boolean isbst(TreeNode node) {
		while (node != null) {
			if (!isbst(node.left))
				return false;
			if (prev != null && node.val < prev.val)
				return false;
			prev = node;
			return isbst(node.right);
		}
		return true;
	}

	public int height(TreeNode node) {

		if (node == null)
			return 0;
		int l = height(node.left) + 1;
		int r = height(node.right) + 1;
		return (l >= r) ? l + 1 : r + 1;
	}

	public void levelorder(TreeNode node) {

		if (node == null)
			return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			System.out.print(temp.val + " ");
			if (temp.left != null)
				queue.offer(temp.left);
			if (temp.right != null)
				queue.offer(temp.right);
		}
	}
}

public class Main {

	public static void main(String[] args) {

		Tree bt = new Tree();
		bt.root = bt.new TreeNode(10);
		bt.root.left = bt.new TreeNode(5);
		bt.root.right = bt.new TreeNode(-3);
		bt.root.left.left = bt.new TreeNode(3);
		bt.root.left.right = bt.new TreeNode(2);
		bt.root.left.left.left = bt.new TreeNode(3);
		bt.root.left.left.right = bt.new TreeNode(-2);
		bt.root.left.right.right = bt.new TreeNode(1);
		bt.root.right.right = bt.new TreeNode(11);
		//bt.levelorder(bt.root);
		//System.out.println();
		System.out.println(bt.pathSum(bt.root, 8));
	}

}