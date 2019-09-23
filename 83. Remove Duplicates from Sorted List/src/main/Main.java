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
    	
    	ListNode pre = null, curr = head;
    	while(curr != null) {
    		if(pre == null || pre.val != curr.val) {
    			if(pre == null)
    				pre = curr;
    			else {
    				pre.next = curr;
    				pre = pre.next;
    			}
    		}
    		curr = curr.next;
    	}
    	pre.next = null;
    	return head;
    }
    
    public ListNode head; 
    
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
		s.head.next = s.new ListNode(1);
		s.head.next.next = s.new ListNode(2);
		s.head.next.next.next = s.new ListNode(3);
		s.head.next.next.next.next = s.new ListNode(3);
		s.head = s.deleteDuplicates(s.head);
		s.display(s.head);
	}
}
