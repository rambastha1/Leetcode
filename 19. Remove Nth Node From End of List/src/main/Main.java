package main;

class Solution {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	ListNode head;
	/*
	 * traverse fast by N, whatever left is difference (length - N)
	 * traverse fast and slow till fast becomes null, at this point since its traverse only diff
	 * slow.next is nth node from end
	 * perform slow.next = slow.next.next as final step, return head
	 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if(head == null || (head.next == null && n == 1))
    		return (head = null);
    	ListNode slow = head, fast = head;
    	for(int i = 0;i <= n;i++) {
    		if(fast == null)
    			return head.next;
    		fast = fast.next;
    	}
    	while(fast != null) {
    		slow = slow.next;
    		fast = fast.next;
    	}
    	slow.next = slow.next.next;
    	return head;
    }
    
    void display(ListNode head) {
    	while(head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
    	}
    }
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(1);
		s.head.next = s.new ListNode(2);
		s.head.next.next = s.new ListNode(3);
		s.head.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next = s.new ListNode(5);
		s.head.next.next.next.next.next = s.new ListNode(6);
		s.display(s.head);
		System.out.println();
		s.head = s.removeNthFromEnd(s.head, 2);
		s.display(s.head);
		System.out.println();
	}
}