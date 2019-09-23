package main;

import java.util.LinkedList;
import java.util.Queue;

class CBTInserter {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public TreeNode root;
    public CBTInserter(TreeNode root) {
        this.root = root;
    }
    //Construct Complete Binary Tree from array
    TreeNode construct(int []a, int index) {
    	if(index > a.length)
    		return null;
    	TreeNode root = new TreeNode(a[index]);
    	root.left = construct(a, index*2+1);
    	root.right = construct(a, index*2+2);
    	return root;
    }
    
    public int insert(int v) {
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		if(node.left != null && node.right != null) {
    			q.offer(node.left);
    			q.offer(node.right);
    		} else {
    			if(node.left == null)
    				node.left = new TreeNode(v);
    			else
    				node.right = new TreeNode(v);
    			return node.val;
    		}
     	}
    	return -1;
    }
    
    public TreeNode get_root() {
        return this.root;
    }
    
    void levelorder(TreeNode root) {
    	Queue<TreeNode> q = new LinkedList<>();
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

public class Main {
	public static void main(String[] args) {
		
		int a[] = { 1, 2, 3, 4, 5, 6, 6, 6, 6 }; 
	}
}