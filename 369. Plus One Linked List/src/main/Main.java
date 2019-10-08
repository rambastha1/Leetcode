package main;

/*
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Example:
 * Input:
 * 1->2->3
 * 
 * Output:
 * 1->2->4
 * 
 * Basically return num + 1
 */

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	/* j will stop at last node
	 * i will stop at node before last 9
	 * increment i.val and set value from next nodes to end to 0
	 */
	public ListNode plusOne(ListNode head) {
		ListNode res = new ListNode(0);
		res.next = head;
		ListNode i = res, j = res;
		while(j.next != null) {
			j = j.next;
			if(j.val != 9)
				i = j;
		}
		
		if(j.val != 9) {
			j.val++;
		} else {
			i.val++;
			i = i.next;
			while(i != null) {
				i.val = 0;
				i = i.next;
			}
		}
		
		if(res.val == 0)
			return res.next;
		return res;
	}
	
	void print(ListNode head) {
		while(head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(9);
		s.head.next = s.new ListNode(3);
		s.head.next.next = s.new ListNode(9);
		s.head = s.plusOne(s.head);
		s.print(s.head);
	}
}