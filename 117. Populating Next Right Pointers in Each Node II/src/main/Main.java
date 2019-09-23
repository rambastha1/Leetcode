package main;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37828/O(1)-space-O(n)-complexity-Iterative-Solution
class Solution {
	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {}
		public Node(int _val,Node _left,Node _right,Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
	
	// Time 0(N) constant space
	public Node connect(Node root) {
		if(root == null)
			return root;
		Node curr = root;
		Node dummy = new Node(0, null, null, null);
		Node temp = dummy;
		/* every time next nodes are created from dummy till curr reaches rightmost node
		 * at time curr is pointed to dummy.next i.e leftmost node in child level
		 * dummy.next is reset to null and temp to dummy
		 */
		while(curr != null) {
			if(curr.left != null) {
				temp.next = curr.left;
				temp = temp.next;
			}
			
			if(curr.right != null) {
				temp.next = curr.right;
				temp = temp.next;
			}
			
			if(curr.next != null) {
				curr = curr.next;
			} else {
				curr = dummy.next;
				dummy.next = null;
				temp = dummy;
			}
		}
		return root;
	}
	public Node root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new Node(1, null, null, null);
		s.root.left = s.new Node(2, null, null, null);
		s.root.left.left = s.new Node(4, null, null, null);
		s.root.left.right = s.new Node(5, null, null, null);
		s.root.right = s.new Node(3, null, null, null);
		s.root.right.right = s.new Node(7, null, null, null);
		s.root = s.connect(s.root);
	}
}
