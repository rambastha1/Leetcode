package main;

/* https://leetcode.com/articles/max-stack/
 * 
 */

class MaxStack {
	
	class Node {
		int val, max;
		Node prev, next;
		public Node(int val, int max) {
			this.max = max;
			this.val = val;
		}
	}
	
	public Node head, tail;
	
	public MaxStack() {
		head = null;
		tail = null;
	}

	public void push(int x) {
		if(head == null && tail == null) {
			Node newnode = new Node(x, x);
			head = tail = newnode;
		} else {
			int max = Math.max(tail.max, x);
			Node newnode = new Node(x, max);
			newnode.prev = tail;
			tail.next = newnode;
			tail = tail.next;
		}
	}

	public int pop() {
		int res = 0;
		if(tail == null)
			return -1;
		else {
			res = tail.val;
			if(head == tail) {
				head = tail = null;
			} else {
				Node temp = tail.prev;
				temp.next = null;
				tail.prev = null;
				tail = temp;
			}
		}
		return res;
	}

	public int top() {
		if(tail == null)
			return -1;
		return tail.val;
	}

	public int peekMax() {
		if(tail == null)
			return -1;
		return tail.max;
	}

	public int popMax() {
		if(tail == null)
			return -1;
		Node prev = null, curr = tail;
		while(curr != null && curr.val != curr.max) {
			prev = curr.prev;
			curr = prev;
		}
		if(prev == null)
			return -1;
		Node next = curr.next;
		prev.next = next;
		if(next!= null)
			next.prev = prev;
		return curr.max;
	}
}


public class Main {
	public static void main(String[] args) {

	}
}
