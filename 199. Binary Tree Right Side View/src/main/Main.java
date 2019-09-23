package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	if(root == null)
    		return res;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);    	
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		res.add(q.peek().val);
    		for(int i = 0;i < size;i++) {
    			TreeNode node = q.poll();
    			if(node.right != null)
        			q.offer(node.right);
    		
	    		if(node.left != null)
	    			q.offer(node.left);	
    		}	
    	}    	
    	return res;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.right.left = s.new TreeNode(6);
		s.root.right.right = s.new TreeNode(7);
		s.root.right.left.right = s.new TreeNode(8);
		
		System.out.println(new Solution().rightSideView(s.root));
	}
}