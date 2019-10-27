package main;

/* Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 * 
 */


class Solution {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	int ans = 0;
	double diff = Double.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
    	if(root == null)
    		return 0;
    	double diff = Math.abs(target - root.val);
    	if(diff < this.diff) {
    		this.diff = diff;
    		ans = root.val;
    	}
    	closestValue(root.left, target);
    	closestValue(root.right, target);
        return ans;
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
		System.out.println(s.closestValue(s.root, target));
	}
}