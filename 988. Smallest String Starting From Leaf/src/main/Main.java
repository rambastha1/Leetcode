package main;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	String ans = "";
    public String smallestFromLeaf(TreeNode root) {
    	if(root == null)
    		return ans;
    	dfs(root, new StringBuilder());    	
    	return ans;
    }
    
    void dfs(TreeNode root, StringBuilder sb) {
    	if(root == null)
    		return;
    	sb.insert(0, (char)(root.val + 'a'));
    	if(root.left == null && root.right == null) {
    		String str = sb.toString();
    		if(ans == "" || str.compareTo(ans) < 0) {
    			ans = str;
    		}
    	}
    	dfs(root.left, sb);
    	dfs(root.right, sb);
    	sb.deleteCharAt(0);
    }
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*s.root = s.new TreeNode(0);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(3);
		s.root.left.right = s.new TreeNode(4);
		s.root.right.left = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(4);*/
		
		s.root = s.new TreeNode(25);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(1);
		s.root.left.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(0);
		s.root.right.right = s.new TreeNode(2);
		System.out.println(s.smallestFromLeaf(s.root));
	}
}