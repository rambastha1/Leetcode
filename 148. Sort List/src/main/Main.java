package main;

import java.util.Arrays;
import java.util.List;

class Solution {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
	/*
	 * Amazing Solution
	 * Find length of list, create auxiliary array of size length
	 * store values in that array - sort array
	 * start storing values from beginning of array 
	 * Time 0(NlgN)
	 * Space 0(N)
	 */
	
	public ListNode sortList(ListNode head) {
		ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        
        int[] nums = new int[count];
        node = head;
        count = 0;
        while (node != null) {
            nums[count++] = node.val;
            node = node.next;
        }
        
        Arrays.sort(nums);
        node = head;
        count = 0;
        while (node != null) {
            node.val = nums[count++];
            node = node.next;
        }
        return head;
	}
	
	
	/*public ListNode sortList(ListNode head) {
		
		if(head == null || head.next == null)
			return head;
		
		ListNode slow = head, fast = head.next;
		while(fast != null) {
			fast = fast.next;
			if(fast != null) {
				slow = slow.next;
				fast = fast.next;
			}
		}
		
		fast = slow.next;
		slow.next = null;
		slow = head;
		print(slow);
		print(fast);
		
		ListNode node1 = sortList(slow);
		ListNode node2 = sortList(fast);
		ListNode mergenode = merge(node1, node2);
		return mergenode;
	}
	
	ListNode merge(ListNode node1, ListNode node2) {
		ListNode res = null;
		if(node1 == null)
			return node2;
		if(node2 == null)
			return node1;
		if(node1.val <= node2.val) {
			res = node1;
			res.next = merge(node1.next, node2);
		} else {
			res = node2;
			res.next = merge(node1, node2.next);
		}
		return res;
	}*/
    
    public void print(ListNode head) {
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
		s.head = s.new ListNode(4);
		s.head.next = s.new ListNode(2);
		s.head.next.next = s.new ListNode(1);
		s.head.next.next.next = s.new ListNode(3);
		s.print(s.head);
		s.head = s.sortList(s.head);
		s.print(s.head);
	}
}