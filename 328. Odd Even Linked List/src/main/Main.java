package main;

class Solution {
	
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
    public ListNode oddEvenList(ListNode head) {
    	if (head != null) {
            ListNode odd = head, even = head.next, evenHead = even; 
            while (even != null && even.next != null) {
                odd.next = odd.next.next; 
                even.next = even.next.next; 
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead; 
        }
        return head;
    }
	
	void display(ListNode head) {
    	if(head == null)
    		return;
    	while(head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
    	}
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		/*SLL s = new SLL();
		s.head = s.new ListNode(1);
		s.head.next = s.new ListNode(2);
		s.head.next.next = s.new ListNode(3);
		s.head.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next = s.new ListNode(5);
		s.head.next.next.next.next.next = s.new ListNode(6);
		
		System.out.println(s.check(s.head));*/
		Solution s = new Solution();
		s.head = s.new ListNode(1);
		s.head.next = s.new ListNode(2);
		s.head.next.next = s.new ListNode(3);
		s.head.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next = s.new ListNode(5);
		
		s.display(s.head);
		s.head = s.oddEvenList(s.head);
		s.display(s.head);
	}
}