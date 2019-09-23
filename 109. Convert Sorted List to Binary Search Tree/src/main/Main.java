package main;

import java.util.LinkedList;
import java.util.Queue;

// https://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
class Solution {
	 
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public ListNode head;
	public TreeNode root;
	
	// Time 0(N)
	public TreeNode sortedListToBST(ListNode head) {
		if(head == null)
			return null;
		if(head.next == null)
			return new TreeNode(head.val);
		this.head = head;
		int len = getlength(head);
		return dfs(len);
	}
	
	public TreeNode dfs(int n) {
		if(n <= 0)
			return null;
		TreeNode left = dfs(n/2);
		TreeNode root = new TreeNode(head.val);
		root.left = left;
		head = head.next;
		/* Recursively construct the right subtree and link it  
         * with root. The number of nodes in right subtree  is  
         * total nodes - nodes in left subtree - 1 (for root) 
         */
		root.right = dfs(n-n/2-1);
		return root;
	}
	
	int getlength(ListNode head) {
		ListNode temp = head;
		int count = 0;
		while(temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}
	
    void levelorder(TreeNode root) {
    	if(root == null)
    		return;
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
		Solution s = new Solution();
		s.head = s.new ListNode(-10); 
		s.head.next = s.new ListNode(-3); 
		s.head.next.next = s.new ListNode(0); 
		s.head.next.next.next = s.new ListNode(5); 
		s.head.next.next.next.next = s.new ListNode(9); 
				
		s.root = s.sortedListToBST(s.head);
		s.levelorder(s.root);
	}
}
