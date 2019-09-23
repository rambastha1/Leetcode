package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	ListNode tail = null;
    public ListNode rotateRight(ListNode head, int k) {
    	if(head == null || head.next == null || k == 0)
    		return head;
    	ListNode curr = head, prev = null;
    	int n = getlen(head);
    	k = n - k%n;
    	for(int i = 0;i < k;i++) {
    		ListNode next = curr.next;
    		prev = curr;
    		prev.next = null;
    		tail.next = prev;
    		tail = tail.next;
    		curr = next;
    	}
    	return curr;
    }
    
    private int getlen(ListNode head) {
    	if(head == null)
    		return 0;
    	ListNode temp = head;
    	int count = 0;
    	while(temp != null) {
    		count++;
    		if(temp.next == null)
    			tail = temp;
    		temp = temp.next;
    	}
    	return count;
    }
    
    void print(ListNode head) {
    	ListNode temp = head;
    	while(temp != null) {
    		System.out.print(temp.val + " ");
    		temp = temp.next;
    	}
    	System.out.println();
    }
    
    public ListNode head;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(1);
		s.head.next = s.new ListNode(2);
		s.head.next.next = s.new ListNode(3);
		s.head.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next = s.new ListNode(5);
		s.print(s.head);
		int k = 2;
		s.head = s.rotateRight(s.head, k);
		s.print(s.head);
	}
}
