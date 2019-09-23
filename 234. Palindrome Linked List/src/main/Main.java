package main;

class List {
	class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
			this.next = null;
		}
	}
	
	Node head;
	public Node insert(int val) {
		Node newnode = new Node(val);
		if(head == null)
			head = newnode;
		else {
			newnode.next = head;
			head = newnode;
		}
		return head;
	}
	
	public void display(Node head) {
		if(head == null)
			return;
		Node temp = head;
		while(temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
	}
	
	Node front = null;
	public boolean palindrome(Node node) {
		front = node;
		return check(node);
	}
	
	public boolean check(Node node) {
		if(node == null)
			return true;
		
		if(check(node.next) && front.val == node.val) {
			front = front.next;
			return true;
		}
		return false;
	}
}


public class Main {
	
	public static void main(String[] args) {	
		
	}
}