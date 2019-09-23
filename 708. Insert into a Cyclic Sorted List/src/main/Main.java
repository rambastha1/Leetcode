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
		if(node == null) {
			node = new Node(insertVal, null);
			node.next = node;
			return node;
		}
		
		Node head = node;
		while(node != null && node.next != null) {
			if(node.val < node.next.val) {
				if(node.val <= insertVal && insertVal <= node.next.val) {
					insertutil(node, insertVal);
					break;
				}
			} else if(node.val > node.next.val) {
				if(insertVal > node.val || insertVal < node.next.val) {
					insertutil(node, insertVal);
					break;
				}
			} else {
				if(node.next == node) {
					insertutil(node, insertVal);
					break;
				}
			}
			node = node.next;
		}
		return head;
	}
	
	private void insertutil(Node node, int x) {
		Node newnode = new Node(x, null);
		newnode.next = node.next;
		node.next = newnode;
	}
}

public class Main {
	public static void main(String[] args) {

	}
}
