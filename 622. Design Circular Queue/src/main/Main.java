package main;

class MyCircularQueue {
	int size, counter;
	
	class Node {
		int val;
		Node prev, next;
		public Node(int val) {
			this.val = val;
		}
	}
	public Node head, tail;
	
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.size = k;
        this.counter = 0;
        this.head = this.tail = null;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
    	if(counter == size)
    		return false;
    	Node newnode = new Node(value);
    	if(head == null && tail == null) {
    		head = tail = newnode;
    		counter++;
    		return true;
    	}
    	tail.next = newnode;
    	newnode.prev = tail;
    	tail = newnode;
    	counter++;
    	return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
    	if(counter == 0)
    		return false;
    	if(head == tail) {
    		head = tail = null;
    		counter--;
    		return true;
    	}
    	Node temp = head.next;
    	head.next = null;
    	temp.prev = null;
    	head = temp;
    	counter--;
    	return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return head!= null?head.val:-1;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
    	return tail!= null?tail.val:-1;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
    	return counter == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
    	return counter == size;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

public class Main {
	public static void main(String[] args) {
		int k = 3;
		MyCircularQueue obj = new MyCircularQueue(k);
		System.out.print(obj.enQueue(1) + " ");
		System.out.print(obj.enQueue(2) + " ");
		System.out.print(obj.enQueue(3) + " ");
		System.out.print(obj.enQueue(4) + " ");
		System.out.print(obj.Rear() + " ");
		System.out.print(obj.isFull() + " ");
		System.out.print(obj.deQueue() + " ");
		System.out.print(obj.enQueue(4) + " ");
		System.out.println(obj.Rear());
	}
}
