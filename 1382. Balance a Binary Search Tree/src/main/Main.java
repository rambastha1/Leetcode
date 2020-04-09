package main;
import java.util.ArrayList;
import java.util.List;

/* Given a binary search tree, return a balanced binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.
 * 
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public TreeNode balanceBST(TreeNode root) {
        if(root == null)
            return null;
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return util(list, 0, list.size()-1);
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
    
    private TreeNode util(List<Integer> arr, int start, int end) {
        if(start > end)
            return null;
        int index = (end + start)/2;
        TreeNode node = new TreeNode(arr.get(index));
        node.left = util(arr, start, index-1);
        node.right = util(arr, index+1, end);
        return node;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.right = s.new TreeNode(2);
		s.root.right.right = s.new TreeNode(3);
		s.root.right.right.right = s.new TreeNode(4);
		s.root = s.balanceBST(s.root);
		
	}
}
