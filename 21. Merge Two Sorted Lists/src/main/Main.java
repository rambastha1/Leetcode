package main;

class List {
	public class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
			this.next = null;
		}
	}
	
	Node head;
	public void display(Node node) {
		if(node == null)
			return;
		Node temp = node;
		while(temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}
	
	public Node merge(Node node1, Node node2) {
		if(node1 == null && node2 == null)
			return node1;
		if(node1 == null)
			return node2;
		if(node2 == null)
			return node1;
		
		Node temp1 = node1, temp2 = node2,res= null, temp = null;
		if(temp1.val <= temp2.val) {
			res = temp = temp1;
			temp1 = temp1.next;
		}
		else {
			res = temp = temp2;
			temp2 = temp2.next;
		}
		
		while(temp1 != null && temp2 != null) {
			
			if(temp1.val <= temp2.val) {
				temp.next = temp1;
				temp = temp.next;
				temp1 = temp1.next;
			} else {
				temp.next = temp2;
				temp = temp.next;
				temp2 = temp2.next;
			}
		}
		
		if(temp1 != null)
			temp.next = temp1;
		
		if(temp2 != null)
			temp.next = temp2;
		
		return res;
	}
}

public class Main {

	public static void main(String[] args) {
		
		List l1 = new List();
		l1.head = l1.new Node(1);
		l1.head.next = l1.new Node(2);
		l1.head.next.next = l1.new Node(4);
		
		List l2 = new List();
		l2.head = l2.new Node(1);
		l2.head.next = l2.new Node(3);
		l2.head.next.next = l2.new Node(4);
		
		l1.display(l1.head);
		l2.display(l2.head);
		
		List l3 = new List();
		l3.head = l1.merge(l1.head, l2.head);
		l3.display(l3.head);
	}
}
