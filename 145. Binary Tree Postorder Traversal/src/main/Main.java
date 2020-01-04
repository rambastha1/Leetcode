package main;
import java.util.ArrayList;
import java.util.List;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        return util(root, res);
    }
    
    public List<Integer> util(TreeNode root, List<Integer> res) {
        if(root == null)
            return res;
        util(root.left, res);
        util(root.right, res);
        res.add(root.val);
        return res;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
