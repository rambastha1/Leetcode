package main;
import java.util.*;

class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
    public List<List<Integer>> levelOrder(TreeNode root) {
    	if(root == null)
    		return null;
    	List<List<Integer>> res = new ArrayList<>();  
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);  	
    	
    	while(!q.isEmpty()) {
    		int level = q.size();
    		List<Integer> list = new ArrayList<>();
    		for(int i = 0;i < level;i++) {
    			if(q.peek().left!= null) q.offer(q.peek().left);
    			if(q.peek().right != null) q.offer(q.peek().right);
    			list.add(q.poll().val);
    		}
    		res.add(list);
    	}    	
        return res;
    }
}

public class Main {

	public static void main(String[] args) {
		
	}
}