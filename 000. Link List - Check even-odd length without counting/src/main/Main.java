package main;
class SLL {
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	ListNode head;
	
	public boolean check(ListNode node) {
		if(node == null)
			return true;
		ListNode temp = node;
		boolean res = false;
		while(true) {
			if(temp == null) {
				return true;
			} else if(temp.next == null) {
				return false;
			}
			temp = temp.next.next;
		}
	}
}

public class Main {
	public static void main(String[] args) {

	}
}
