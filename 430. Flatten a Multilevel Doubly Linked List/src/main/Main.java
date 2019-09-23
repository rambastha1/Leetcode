package main;

import java.util.Stack;

class Solution {
	class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;

		public Node() {}

		public Node(int _val,Node _prev,Node _next,Node _child) {
			val = _val;
			prev = _prev;
			next = _next;
			child = _child;
		}
	};
	
	public Node head;
	
	public Node flatten(Node head) {
	    /* We can use a stack to store the next node when the current node has a child, 
	     * so we can go back to it when we reach to the end of the current list.
	     */
	    Stack<Node> stack = new Stack<>();
	    
		// Keep the head pointer and traverse the list using current node (curNode)
	    Node curr = head;
		
	    while(curr != null){
	        if(curr.child != null){
			    // if the current node has a child, then add the next node to the stack
	            stack.add(curr.next);
				// point the next node to the child
	            curr.next = curr.child;
				// remove the child's pointer
	            curr.child = null;
	        }
			
	        /* Determine which is the next node, if reached to the end of the current list 
	         * and the stack is NOT empty, then pop the lastest node to be the next. 
	         */
	        Node next = (curr.next == null && !stack.isEmpty()) ? stack.pop() : curr.next;
	        
	        if(next != null){
			    // make sure all the pointers are correct
	            next.prev = curr;   
	            curr.next = next;
	        }
	        curr = next;
	    }
	    return head;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		
	}
}
