package main;

import java.util.LinkedList;

class MinStack {
	class Node {
		int val, min;
		Node next, prev;
		public Node(int val, int min) {
			this.min = min;
			this.val = val;
		}
	}
	
	LinkedList<Node> list;
	public Node head, tail;
    /** initialize your data structure here. */
    public MinStack() {
        list = new LinkedList<>();
        head = null;
        tail = null;
    }
    
    public void push(int x) {
        if(head == null || tail == null) {
        	Node newnode = new Node(x, x);
        	head = newnode;
        	tail = head;
        } else {
        	int min = tail.min < x?tail.min:x;
        	Node newnode = new Node(x, min);
        	newnode.prev = tail;
        	tail.next = newnode;
        	tail = tail.next;
        }
    }
    
    public void pop() {
        if(tail == null)
        	return;
        if(tail.prev == null) {
        	tail = null;
        	return;
        }
        Node temp = tail.prev;
        tail.prev = null;
        temp.next = null;
        tail = temp;
    }
    
    public int top() {
    	return tail!=null?tail.val:-1;
    }
    
    public int getMin() {
    	return tail!= null?tail.min:-1;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

public class Main {
	public static void main(String[] args) {
		MinStack m = new MinStack();
		m.push(-2);
		m.push(0);
		m.push(-3);
		System.out.println(m.getMin());
		m.pop();
		System.out.println(m.top());
		System.out.println(m.getMin());
	}
}
