class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	int val = 0;
    public TreeNode bstToGst(TreeNode root) {
    	if(root == null)
    		return root;
    	bstToGst(root.right);
    	root.val += val;
    	val = root.val;
    	bstToGst(root.left);
    	return root;
    }
    
    void inorder(TreeNode root) {
    	if(root == null)
    		return;
    	inorder(root.left);
    	System.out.print(root.val + " ");
    	inorder(root.right);
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(6);
		s.root.left.left = s.new TreeNode(0);
		s.root.left.right = s.new TreeNode(2);
		s.root.left.right.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(5);
		s.root.right.right = s.new TreeNode(7);
		s.root.right.right.right = s.new TreeNode(8);
		
		s.inorder(s.root);
		System.out.println();
		s.inorder(s.bstToGst(s.root));
	}
}