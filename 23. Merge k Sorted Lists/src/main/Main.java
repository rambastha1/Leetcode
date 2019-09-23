package main;

class Solution {
    
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode mergelist(ListNode node1, ListNode node2) {
		if(node1 == null && node2 == null)
			return null;
		if(node1 == null)
			return node2;
		if(node2 == null)
			return node1;
		
		ListNode temp1 = new ListNode(0);
		ListNode temp2 = temp1;
		
		while(node1 != null && node2 != null) {
			if(node1.val <= node2.val) {
				temp2.next = node1;
				node1 = node1.next;
			} else {
				temp2.next = node2;
				node2 = node2.next;
			}
			temp2 = temp2.next;
		}
		
		while(node1 != null) {
			temp2.next = node1;
			node1 = node1.next;
			temp2 = temp2.next;
		}
		
		while(node2 != null) {
			temp2.next = node2;
			node2 = node2.next;
			temp2 = temp2.next;
		}
		return temp1.next;
	}
	
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0)
			return null;
		if(lists.length == 1)
			return lists[0];
        int last = lists.length-1;
        while(last != 0) {
        	int start = 0, end = last;
	        while(start < end) {
	        	lists[start] = mergelist(lists[start], lists[end]);
	        	start++;end--;
	        	if(start >= end)
	        		last = end;
	        }
        }
		return lists[0];
    }
	
	void print(ListNode node) {
		if(node == null)
			return;
		ListNode temp = node;
		while(temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		int len = 3;
		Solution.ListNode []lists = new Solution.ListNode[len];
		for(int i = 0;i < len;i++)
			lists[i] = null;
		
		lists[0] = s.new ListNode(1);
		lists[0].next = s.new ListNode(4);
		lists[0].next.next = s.new ListNode(5);
		
		lists[2] = s.new ListNode(2);
		lists[2].next = s.new ListNode(6);
		
		lists[1] = s.new ListNode(1);
		lists[1].next = s.new ListNode(3);
		lists[1].next.next = s.new ListNode(4);
		
		Solution.ListNode node = s.mergeKLists(lists);
		
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}
}