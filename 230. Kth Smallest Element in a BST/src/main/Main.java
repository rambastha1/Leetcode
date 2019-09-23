package main;

class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	TreeNode root;
	
	/*
	 * Morris Traversal
	 * Modifies the tree
	 * Time 0(N) Space 0(1)
	 */
	
	public int kthSmallest(TreeNode root, int k) {
		int count = 0;
		int ans = Integer.MIN_VALUE;
		TreeNode curr = root;
		while(curr != null) {
			if(curr.left == null) {
				count++;
				if(count == k)
					ans = curr.val;
				curr = curr.right;
			} else {
				TreeNode pre = curr.left;
				while(pre.right != null && pre.right != curr)
					pre = pre.right;
				if(pre.right == null) {
					pre.right = curr;
					curr = curr.left;
				} else {
					pre.right = null;
					count++;
					if(count == k)
						ans = curr.val;
					curr = curr.right;
				}
			}
		}
		return ans;
    }
	
	/*
	 * Using inorder traversal
	 * Time 0(N)
	 */
	
	/*int val = -1,c = 0;
	
    public int kthSmallest(TreeNode root, int k) {
    	kthSmallestutil(root, k);
    	return val;
    }
    
    public void kthSmallestutil(TreeNode root, int k) {
    	if(root != null) {
    		kthSmallestutil(root.left, k);
    		c++;
    		 if(c == k)
    			val = root.val;
    		kthSmallestutil(root.right, k);
    	}
    }*/
    
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(2);
		int k = 3;
		System.out.println(s.kthSmallest(s.root, k));
	}
}