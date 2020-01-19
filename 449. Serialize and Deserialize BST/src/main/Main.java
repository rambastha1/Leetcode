package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import main.Codec.TreeNode;

class Codec {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) 
        	return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) 
        	return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) 
        	return null;
        String s = q.peek();
        int val = Integer.parseInt(s);
        
        if (val < lower || val > upper) 
        	return null;
        q.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserialize(q, lower, val);
        root.right = deserialize(q, val, upper);
        return root;
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
		//System.out.println(c.list);
		TreeNode node = c.deserialize(data);
		c.levelorder(node);
	}
}