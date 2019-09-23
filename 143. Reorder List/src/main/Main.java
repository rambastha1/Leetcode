package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
    public void reorderList(ListNode head) {
    	if(head == null || head.next == null)
    		return;
    	ListNode slow = head, fast = head.next;
    	while(slow != null && fast != null) {
    		if(fast.next != null)
    			fast = fast.next.next;
    		else
    			break;
    		slow = slow.next;
    	}
    	
    	fast = slow.next;
    	slow.next = null;
    	slow = head;
    	
    	fast = reverse(fast);
    	ListNode res = new ListNode(0), temp = res;
    	while(slow != null && fast != null) {
    		temp.next = slow;
    		slow = slow.next;
    		temp = temp.next;
    		temp.next = fast;
    		fast = fast.next;
    		temp = temp.next;
    	}
    	
    	if(slow != null)
    		temp.next = slow;
    	if(fast != null)
    		temp.next = fast;
    	
    }
    
    ListNode reverse(ListNode head) {
    	if(head == null || head.next == null)
    		return head;
    	
    	ListNode prev = null, curr = head, next = null;
    	while(curr != null) {
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    	}
    	return prev;
    }
    
    public ListNode head;
    
    public void display(ListNode head) {
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
		s.reorderList(s.head);
		s.display(s.head);
	}
}
