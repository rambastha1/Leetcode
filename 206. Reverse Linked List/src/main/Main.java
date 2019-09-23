package main;

class SLL {
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
			this.next = null;
		}
		
		ListNode head;
		
		public ListNode reverse(ListNode node) {
			
			if(node == null || node.next == null)
				return node;
			
			ListNode prev= null, curr = node, next = null;
			while(curr != null) {
				next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			return prev;			
		}
	}
}

public class Main {

	public static void main(String[] args) {

		
	}

}
