package main;

//Tried with Singly Link List Delete Front is 0(N)
//Use DLL for 0(1)

class MyCircularDeque {
	
	//Keeps count of number of elements
	int count = 0, k;
	class ListNode {
		int val;
		ListNode next;
		ListNode prev;
		
		public ListNode(int val) {
			this.val = val;
		}
	}
	public ListNode front, rear;
	
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
    	this.k = k;
        front = rear = null;
        
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
    	if(count == k)
    		return false;
    	
    	ListNode node = new ListNode(value);
    	if(front == null) {
    		front = node;
    		rear = node;
    	} else {
    		front.next = node;
    		node.prev = front;
    		front = node;
    	}
    	count++;
    	return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
    	if(count == k)
    		return false;
    	
    	ListNode node = new ListNode(value);
    	if(rear == null) {
    		front = node;
    		rear = node;
    	} else {
	    	node.next = rear;
	    	rear.prev = node;
	    	rear = node;
    	}
    	count++;
    	return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
    	if(count == 0)
    		return false;
    	if(front == rear) {
    		front = null;
    		rear = null;
    	} else {
			front = front.prev;
			front.next = null;
    	}
    	count--;
    	return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
    	if(count == 0)
    		return false;
    	if(front == rear) {
    		front = null;
    		rear = null;
    	} else {
    		rear = rear.next;
    		rear.prev = null;
    	}
    	count--;
    	return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
    	if(count == 0)
    		return -1;
    	return front.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
    	if(count == 0)
    		return -1;
    	return rear.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
    	return count==0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
    	return count==k;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */

public class Main {
	public static void main(String[] args) {
		/*int k = 3;
		MyCircularDeque circularDeque = new MyCircularDeque(k);
		System.out.println(circularDeque.insertLast(1));
		System.out.println(circularDeque.insertLast(2));
		System.out.println(circularDeque.insertFront(3));
		System.out.println(circularDeque.insertFront(4));
		System.out.println(circularDeque.getRear());
		System.out.println(circularDeque.isFull());
		System.out.println(circularDeque.deleteLast());
		System.out.println(circularDeque.insertFront(4));
		System.out.println(circularDeque.getFront());*/
		
		
		int k = 8;
		MyCircularDeque circularDeque = new MyCircularDeque(k);
		System.out.println(circularDeque.insertFront(5));
		System.out.println(circularDeque.getFront());
		System.out.println(circularDeque.isEmpty());
		System.out.println(circularDeque.deleteFront());
		System.out.println(circularDeque.insertLast(3));
		System.out.println(circularDeque.getRear());
		System.out.println(circularDeque.insertLast(7));
		System.out.println(circularDeque.insertFront(7));
		System.out.println(circularDeque.deleteLast());
		System.out.println(circularDeque.insertLast(4));
		System.out.println(circularDeque.isEmpty());
		
		
	}
}