package main;

class Solution {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	ListNode head;
	/*
	 * 22 ms solution
	 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if(l1 == null && l2 == null)
    		return null;
    	if(l1 == null || l2 == null)
    		return (l1 == null)?l2:l1;
    	return addTwoNumbersUtil(l1, l2, 0);
    }
    
    public ListNode addTwoNumbersUtil(ListNode l1, ListNode l2, int carry) {
    	int sum = 0;
    	ListNode res = new ListNode(0);
    	ListNode temp = res;
    	
    	while (l1 != null && l2 != null) {
    		sum = l1.val + l2.val + carry;
    		carry = sum/10;
    		sum %= 10;
    		temp.next = new ListNode(sum);
    		temp = temp.next;
    		l1 = l1.next;
    		l2 = l2.next;
    	}
    	
    	while (l1 != null) {
    		sum = l1.val + carry;
    		carry = sum/10;
    		sum %= 10;
    		temp.next = new ListNode(sum);
    		temp = temp.next;
    		l1 = l1.next;
    	}
    	
    	while (l2 != null) {
    		sum = l2.val + carry;
    		carry = sum/10;
    		sum %= 10;
    		temp.next = new ListNode(sum);
    		temp = temp.next;
    		l2 = l2.next;
    	}
    	
    	if(carry != 0) {
    		temp.next = new ListNode(carry);
    	}
    	return res.next;
    }
    
    /*
     * 18 ms solution
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        
        ListNode top = new ListNode(-1);
        ListNode l3 = new ListNode(-1);
        
        top.next = l3;        
        int carry = 0;
        while (l1 != null || l2 != null){
            
            l3.next = new ListNode(0);
            l3 = l3.next;
            
            int a= 0;
            int b = 0;
            if (l1 == null){
                a = 0;
            } else {
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 == null){
                b = 0;
            } else {
                b = l2.val;
                l2 = l2.next;
            }
            //System.out.println(a + "," + b + "," + "," + carry);
            int result = (carry + a + b)%10;
            carry = (carry + a + b)/10;
            //System.out.println(result + "," + carry);
            l3.val = result;
        }
        if (carry == 1){
            l3.next = new ListNode(1);
        }
        return top.next.next;
    }
    
    
    void display(ListNode node) {
    	if(node == null)
    		return;
    	while(node != null) {
    		System.out.print(node.val + " ");
    		node = node.next;
    	}
    }
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		Solution s1 = new Solution();
		s.head = s.new ListNode(2);
		s.head.next = s.new ListNode(4);
		s.head.next.next = s.new ListNode(3);
		
		s1.head = s1.new ListNode(5);
		s1.head.next = s1.new ListNode(6);
		s1.head.next.next = s1.new ListNode(4);
		
		Solution.ListNode res = s.addTwoNumbers(s.head, s1.head);
		s.display(res);
	}
}