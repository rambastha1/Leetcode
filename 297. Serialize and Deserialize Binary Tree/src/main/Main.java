package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/serialize-deserialize-binary-tree/

class Codec {
	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	TreeNode root;
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q);
    }
    
    public TreeNode deserialize(Queue<String> q) {
        String s = q.poll();
        if (s.equals("#")) 
        	return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserialize(q);
        root.right = deserialize(q);
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
		Codec c = new Codec();
		c.root = c.new TreeNode(1);
		c.root.left = c.new TreeNode(2);
		c.root.right = c.new TreeNode(3);
		c.root.right.left = c.new TreeNode(4);
		c.root.right.right = c.new TreeNode(5);
		c.inorder(c.root);
		System.out.println();
		String str = c.serialize(c.root);
		//System.out.println(c.list);
		Codec.TreeNode node = c.deserialize(str);
		c.inorder(c.root);
		System.out.println();
	}
}