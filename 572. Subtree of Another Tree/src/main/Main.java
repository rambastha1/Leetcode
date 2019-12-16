/* Constructed my own binary tree
 * No need to do for leetcode
 */

package main;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if(s == null)
			return false;
			
		if(issame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t))
			return true;
		return false;
	}
	
	private boolean issame(TreeNode s, TreeNode t) {
		if(s == null && t == null)
			return true;
		if(s == null || t == null)
			return false;
		if(s.val != t.val)
			return false;
		return issame(s.left, t.left) && issame(s.right, t.right);
	}
	
	public boolean isSubtree1(TreeNode s, TreeNode t) {
        String tree1 = preorder(s, true);
		String tree2 = preorder(t, true);
		return tree1.indexOf(tree2) >= 0;
    }
    
    public String preorder(TreeNode node, boolean isLeft) {
		
		if(node == null) {
			if(isLeft)
				return "lnull";
			else
				return "rnull";
		}
		return "#" + node.val + " " + preorder(node.left, true) + " " + preorder(node.right, false);	
	}
    
    public TreeNode root;
}

public class Main {

	public static void main(String[] args) {
		
		/*bst.root = bst.insert(bst.root, 50);
		bst.root = bst.insert(bst.root, 30);
		bst.root = bst.insert(bst.root, 20);
		bst.root = bst.insert(bst.root, 40);
		bst.root = bst.insert(bst.root, 70);
		bst.root = bst.insert(bst.root, 60);
		bst.root = bst.insert(bst.root, 80);
		
		bst.inorder(bst.root);
		System.out.println();
		bst.preorder(bst.root);
		System.out.println();
		bst.postorder(bst.root);
		System.out.println();
		
		System.out.println(bst.isbst(bst.root));*/
		
		/*bst.root = bst.new Node(3);
		bst.root.left = bst.new Node(4);
		bst.root.left.left = bst.new Node(1);
		bst.root.left.right = bst.new Node(2);
		//bst.root.left.right.left = bst.new Node(0);
		bst.root.right = bst.new Node(5);
		
		Tree bst1 = new Tree();
		bst1.root = bst1.new Node(4);
		bst1.root.left = bst.new Node(1);
		bst1.root.right = bst1.new Node(2);
		
		System.out.println(bst.isSubtree(bst.root, bst1.root));*/
	}
}