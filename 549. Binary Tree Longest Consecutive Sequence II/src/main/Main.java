package main;

/* Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] 
 * are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the 
 * child-Parent-child order, where not necessarily be parent-child order.
 * Example 1:
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * Example 2:
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 * 
 */

class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	public TreeNode root;
	
	int maxlen = 0;
	public int longestConsecutive(TreeNode root) {
		if(root == null)
			return 0;
		dfs(root);
		return maxlen;
	}
	
	public int[] dfs(TreeNode root) {
		if(root == null) return new int[] {0,0};
		int inc = 1, dec = 1;
		if(root.left != null) {
			int []left = dfs(root.left);
			if(root.val == root.left.val + 1)
				dec = left[1] + 1;
			else if(root.val == root.left.val - 1)
				inc = left[0] + 1;
		}
		
		if(root.right != null) {
			int []right = dfs(root.right);
			if(root.val == root.right.val + 1)
				dec = right[1] + 1;
			else if(root.val == root.right.val - 1)
				inc = right[0] + 1;
		}
		// root is included in both thus -1
		maxlen = Math.max(maxlen, inc+dec-1);
		return new int[] {inc,dec};
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(2);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(3);
		System.out.println(s.longestConsecutive(s.root));
	}
}
