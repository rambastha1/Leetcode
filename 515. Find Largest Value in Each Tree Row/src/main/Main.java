package main;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
    public List<Integer> largestValues(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		int size = q.size();
    		int max = Integer.MIN_VALUE;
    		for(int i = 0;i < size;i++) {
    			TreeNode node = q.poll();
    			max = Math.max(max, node.val);
    			if(node.left != null)
    				q.offer(node.left);
    			if(node.right != null)
    				q.offer(node.right);
    		}
    		res.add(max);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(3);
		s.root.right = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(5);
		s.root.left.right = s.new TreeNode(3);
		s.root.right.right = s.new TreeNode(9);
		System.out.println(s.largestValues(s.root));
	}
}