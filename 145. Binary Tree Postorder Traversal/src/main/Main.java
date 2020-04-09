package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null)
        	return res;
        stack.push(root);
        while(!stack.isEmpty()) {
        	TreeNode curr = stack.pop();
        	res.add(0, curr.val);
        	if(curr.left != null)
        		stack.push(curr.left);
        	if(curr.right != null)
        		stack.push(curr.right);
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
