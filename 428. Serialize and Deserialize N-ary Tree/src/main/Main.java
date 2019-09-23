package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/discuss/190318/Serialize-and-Deserialize-Binary-and-N-ary-Tree-Summary

class Codec {
	
	class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val,List<Node> _children) {
			val = _val;
			children = _children;
		}
	};
	
	// Encodes a tree to a single string.
	public String serialize(Node root) {
		StringBuilder sb = new StringBuilder();
		build(root, sb);
		return sb.toString();
	}
	
	private void build(Node node, StringBuilder sb) {
		if(node == null) {
			sb.append("#");
			sb.append(",");
		} else {
			sb.append(node.val).append(",");
			sb.append(node.children.size()).append(",");
			for(Node child : node.children)
				build(child, sb);
		}
	}

	// Decodes your encoded data to tree.
	public Node deserialize(String data) {
		Deque<String> deque = new ArrayDeque<>(Arrays.asList(data.split(",")));
		return buildTree(deque);
	}
	
	private Node buildTree(Deque<String> deque) {
		String str = deque.removeFirst();
		if(str.compareTo("#") == 0)
			return null;
		int val = Integer.parseInt(str);
		int numchild = Integer.parseInt(deque.removeFirst());
		Node node = new Node(val, null);
		node.children = new ArrayList<>();
		for(int i = 0;i < numchild;i++)
			node.children.add(buildTree(deque));
		
		return node;
	}
}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));

public class Main {
	public static void main(String[] args) {

	}
}