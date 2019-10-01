package main;

/* Given a node from a cyclic linked list which is sorted in ascending order, 
 * write a function to insert a value into the list such that it remains a cyclic sorted list. 
 * The given node can be a reference to any single node in the list, 
 * and may not be necessarily the smallest value in the cyclic list.
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. 
 * After the insertion, the cyclic list should remain sorted.
 * If the list is empty (i.e., given node is null), you should create a new single cyclic list and 
 * return the reference to that single node. Otherwise, you should return the original given node.
 * 
 * The following example may help you understand the problem better:
 * In the figure above, there is a cyclic sorted list of three elements. 
 * You are given a reference to the node with value 3, and we need to insert 2 into the list.

 * The new node should insert between node 1 and node 3. After the insertion, the list should look like this, 
 * and we should still return node 3.
 */

/* This question is to think about all the cases. 
 * 1: If node is null, you need to create a new node and connect yourself to yourself. 
 * In addition to the above case, we need to find the location of the point to be inserted in this list. 
 * We traverse the linked list and analyze it in different cases. 
 * 
 * 2: If node.val < node.next.val, then if x is in the two values here, we can insert x. 
 * 
 * 3: If node.val > node.next.val, this means that we are now at the last node of the linked list. 
 * Then if x is larger than the largest node value node.val in the entire linked list, or smaller 
 * than the smallest node value node.next.val in the entire linked list, then x can be inserted in both. 
 * 
 * 4: If node.val == node.next.val, then we still have to judge whether node is the last node of the ring, 
 * and whether node.next is the head of the ring. If so, we can insert x.
 * 
 * https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/282340/Simple-Java-0ms-iterative-solution-in-0(n)-that-beats-100-with-explanation
 */
class Solution {
	
	class Node {
	    public int val;
	    public Node next;

	    public Node() {}

	    public Node(int _val,Node _next) {
	        val = _val;
	        next = _next;
	    }
	}
	
	public Node insert(Node node, int insertVal) {
		Node newnode = new Node(insertVal, null);
		if(node == null) {
			newnode.next = newnode;
			return newnode;
		}
		
		Node prev = node, next = node.next;
		/* break when
		 * a) increasing list i.e head to tail and value is between any two nodes
		 * b) value greater than last node value (greatest) or less than first node value(smallest) i.e 
		 * should be insert at the last of list or at the beginning of the list
		 */
		while(next != node) {
			if((insertVal < next.val && insertVal >= prev.val) || (prev.val > next.val && (prev.val <= insertVal || insertVal <= next.val))) {
					break;
			}
			prev = next;
			next = next.next;
		}
		prev.next = newnode;
		newnode.next = next;
		return node;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		Solution.Node head = s.new Node(3, null);
		head.next = s.new Node(4, null);
		head.next.next = s.new Node(1, null);
		head.next.next.next = head;
		Solution.Node temp = s.insert(head, 2);
		System.out.println(temp.val);
	}
}
