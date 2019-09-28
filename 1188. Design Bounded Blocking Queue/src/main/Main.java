package main;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/design-bounded-blocking-queue/discuss/385708/Java-7ms-simulate-thread-wait-and-synchronize-on-the-queue-object
class BoundedBlockingQueue {
	
	private volatile Queue<Integer> queue = null;
	private int capacity = 0;
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }
    
    public void enqueue(int element) throws InterruptedException {
    	// mutex
        boolean produced = false;
        // spin lock
        while(!produced) {
        	synchronized (queue) {
				if(this.queue.size() < this.capacity) {
					queue.offer(element);
					produced = true;
				}
			}
        }
    }
    
    public int dequeue() throws InterruptedException {
    	// mutex
    	boolean consumed = false;
    	Integer ans = null;
    	// spin lock
    	while(!consumed) {
    		synchronized (queue) {
				if(!this.queue.isEmpty()) {
					ans = queue.poll();
					consumed = true;
				}
			}
    	}
    	return ans;
    }
    
    public int size() {
    	return this.queue.size();
    }
}

public class Main {
	public static void main(String[] args) {

	}
}