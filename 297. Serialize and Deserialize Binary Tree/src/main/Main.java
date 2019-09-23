package main;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/serialize-deserialize-binary-tree/

class Codec {
	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	TreeNode root;
	static ArrayList<String> list = new ArrayList<String>();
	static int ind = 0;
	
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if(root == null){
			list.add("#");
			return "";
		}

		list.add(root.val+"");
		serialize(root.left);
		serialize(root.right);

		return "";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if(ind >= list.size() || list.get(ind).equals("#")){
			ind++;
			return null;
		}
		//System.out.println(list.get(ind));
		TreeNode root = new TreeNode(Integer.parseInt(list.get(ind)));
		ind++;
		root.left = deserialize(data);
		root.right = deserialize(data);
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
		System.out.println(c.list);
		Codec.TreeNode node = c.deserialize(str);
		c.inorder(c.root);
		System.out.println();
	}
}