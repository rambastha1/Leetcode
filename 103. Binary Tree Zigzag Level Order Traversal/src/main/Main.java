package main;
import java.util.*;

class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	
	// By modifying Printing 
	//recursive 0(N) solution
	/*public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	    List<List<Integer>> res = new ArrayList();
	    travel(res, 0, root);
	    return res;
	}
	
	private void travel(List<List<Integer>> res, int level, TreeNode cur) {
	    if (cur == null) return;
	    if (res.size() <= level) {
	        res.add(new ArrayList<Integer>());
	    }
	    if (level % 2 == 0) {
	        res.get(level).add(cur.val);
	    } else {
	        res.get(level).add(0, cur.val);
	    }
	    travel(res, level + 1, cur.left);
	    travel(res, level + 1, cur.right);
	}*/
	
	/*
	 * Using Queue
	 * 0(N) solution better Iterative
	 */
    
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null)
			return res;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		boolean order = true;
		int size = 1;
		while(!q.isEmpty()) {
			List<Integer> list = new ArrayList<>();
			for(int i = 0;i < size;i++) {
				TreeNode temp = q.poll();
				if(order)
					list.add(temp.val);
				else
					list.add(0,temp.val);
				
				if(temp.left != null)
					q.offer(temp.left);
				if(temp.right != null)
					q.offer(temp.right);
			}
			res.add(list);
			size = q.size();
			order = !order;
		}
		return res;
	}
	
    public void levelorder(TreeNode root) {
    	if(root == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode temp = q.poll();
    		System.out.print(temp.val + " ");
    		if(temp.left != null)
    			q.offer(temp.left);
    		if(temp.right != null)
    			q.offer(temp.right);
    	}
    }
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(9);
		s.root.right = s.new TreeNode(20);
		s.root.right.left = s.new TreeNode(15);
		s.root.right.right = s.new TreeNode(7);
		List<List<Integer>> list = s.zigzagLevelOrder(s.root);
		for(List<Integer> li : list) {
			for(int i : li)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}