package main;

import java.util.ArrayList;
import java.util.List;

/* Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree. 
 * An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted 
 * tree in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work. 
 * You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the 
 * original N-nary tree structure.

For example, you may encode the following 3-ary tree to a binary tree in this way:

Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, 
so please be creative and come up with different approaches yourself.

Note:

N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * https://leetcode.com/articles/introduction-to-n-ary-trees/
 */

class Codec {
	/* child to right and sibling to left
	 * 
	 */
	class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val,List<Node> _children) {
			val = _val;
			children = _children;
		}
	};
		
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	// Encodes an n-ary tree to a binary tree.
	public TreeNode encode(Node root) {
		if(root == null)
			return null;
		TreeNode node = new TreeNode(root.val);
		if(root.children.size() > 0) {
			node.right = encode(root.children.get(0));
		}
		
		TreeNode curr = node.right;
		for(int i = 1;i < root.children.size();i++) {
			curr.left = encode(root.children.get(i));
			curr = curr.left;
		}
		return node;
	}
	
	// Decodes your binary tree to an n-ary tree.
	public Node decode(TreeNode root) {
		if(root == null)
			return null;
		Node node = new Node(root.val, new ArrayList<>());
		TreeNode curr = root.right;
		
		while(curr != null) {
			node.children.add(decode(curr));
			curr = curr.left;
		}
		return node;
	}
}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.decode(codec.encode(root));

public class Main {
	public static void main(String[] args) {

	}
}