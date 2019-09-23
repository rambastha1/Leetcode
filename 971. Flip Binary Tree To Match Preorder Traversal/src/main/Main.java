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
	
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
    	List<Integer> res = new ArrayList<>();
    	dfs(root, voyage, res);
    	return res;
    }
    int index = 0;
    void dfs(TreeNode root, int[] voyage, List<Integer> res) {
    	if(root == null)
    		return;
    	if(root.val != voyage[index]) {
    		res.clear();
    		res.add(-1);
    		return;
    	}
    	
    	index++;
    	if(root.left != null && root.left.val != voyage[index]) {
    		res.add(root.val);
    		TreeNode temp = root.left;
    		root.left = root.right;
    		root.right = temp;
    	}
    	dfs(root.left, voyage, res);
    	dfs(root.right, voyage, res);
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		int []voyage = {1,3,2};
		System.out.println(new Solution().flipMatchVoyage(s.root, voyage));
	}
}
