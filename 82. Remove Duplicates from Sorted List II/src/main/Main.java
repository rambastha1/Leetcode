package main;

class Solution {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
    public ListNode deleteDuplicates(ListNode head) {
    	if(head == null || head.next == null)
    		return head;
    	
    	ListNode curr = head, next = null;
    	ListNode res = new ListNode(0), temp = res;
    	
    	while(curr != null) {
    		int val = curr.val;
    		next = curr.next;
    		if(next == null) {
    			temp.next = curr;
    			return res.next;
    		}
    		while(next != null && next.val == val)
    			next = next.next;
    		
    		if(next == null)
    			temp.next = null;
    		
    		if(next == curr.next) {
    			temp.next = curr;
    			temp = temp.next;
    		}
    		curr = next;
    	}
    	return res.next;
    }
    
    public ListNode head; 
    void display(ListNode head) {
    	while(head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
    	}
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(1);
		s.head.next = s.new ListNode(1);
		s.head.next.next = s.new ListNode(2);
		s.head.next.next.next = s.new ListNode(3);
		s.head.next.next.next.next = s.new ListNode(3);
		s.head.next.next.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next.next.next.next = s.new ListNode(5);
		s.head.next.next.next.next.next.next.next.next = s.new ListNode(5);
		s.display(s.head);
		s.head = s.deleteDuplicates(s.head);
		s.display(s.head);
	}
}
