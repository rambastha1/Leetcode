package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.Codec.TreeNode;

class Codec {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	List<String> list;
	int index = 0;
	
	public Codec() {
		list = new ArrayList<>();
	}
	
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if(root == null) {
    		list.add("#");
    		return "";
    	}
    	list.add(String.valueOf(root.val + ""));
    	serialize(root.left);
    	serialize(root.right);
    	return "";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if(index >= list.size() || list.get(index) == "#") {
    		index++;
    		return null;
    	}
    	TreeNode node = new TreeNode(Integer.valueOf(list.get(index++)));
    	node.left = deserialize(data);
    	node.right = deserialize(data);
        return node;
    }
    
    public TreeNode root;
    
    void levelorder(TreeNode root) {
    	if(root == null)
    		return;
    	Queue<TreeNode> q= new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		System.out.print(node.val + " "); 
    		if(node.left != null)
    			q.offer(node.left);
    		if(node.right != null)
    			q.offer(node.right);
    	}
    	System.out.println();
    }
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

public class Main {
	public static void main(String[] args) {
		Codec c = new Codec();
		c.root = c.new TreeNode(1);
		c.root.left = c.new TreeNode(2);
		c.root.right = c.new TreeNode(3);
		c.root.left.left = c.new TreeNode(4);
		c.root.left.right = c.new TreeNode(5);
		c.root.right.left = c.new TreeNode(6);
		String data = c.serialize(c.root);
		System.out.println(c.list);
		TreeNode node = c.deserialize(data);
		c.levelorder(node);
	}
}