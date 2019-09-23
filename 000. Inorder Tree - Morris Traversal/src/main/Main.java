package main;

class Solution {
	class Node {
		int val;
		Node left, right;
		Node (int val) {
			this.val = val;
		}
	}
	
	Node root;
	
	/*
	 * https://www.youtube.com/watch?v=ffgg_zmbaxw
	 * Video in Folder
	 * Time 0(N) constant space
	 * changes tree structures and regain its structure
	 */
	
	public void inorder(Node root) {
		if(root == null)
			return;
		Node curr = root, pre = null;
		/* curr is used as parent node
		 * It is used by a child node to set its null right pointer point to it.*/
		while(curr != null) {
			if(curr.left == null) {
				System.out.print(curr.val + " ");
				curr = curr.right;
			} else {
				/* Find the inorder predecessor of current */
				pre = curr.left;
				//Assuming pre is not null. If null, no need to do anything				
				while(pre.right != null && pre.right != curr)
					pre = pre.right;
				/* Make current as right child of its inorder predecessor */
				if(pre.right == null) {
					pre.right = curr;
					curr = curr.left;
					
				} else {
					/* It means it has already traversed the left child node.
					 * So, Revert the changes made in if part to restore the  
                     * original tree i.e., fix the right child of predecssor*/
					pre.right = null;
					System.out.print(curr.val + " ");
					curr = curr.right;
				}
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new Node(1);
		s.root.left = s.new Node(2);
		s.root.right = s.new Node(3);
		s.root.left.left = s.new Node(4);
		s.root.left.right = s.new Node(5);
		s.inorder(s.root);
		System.out.println();
	}
}