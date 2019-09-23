package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* Design a Phone Directory which supports the following operations:
 * 
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * 
 * Example:
 * Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 * 
 * It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 * 
 * Assume it returns 1.
 * directory.get();
 * 
 * The number 2 is available, so return true.
 * directory.check(2);
 * 
 * It returns 2, the only number that is left.
 * directory.get();
 * 
 * The number 2 is no longer available, so return false.
 * directory.check(2);
 * 
 * Release number 2 back to the pool.
 * directory.release(2);
 * 
 * Number 2 is available again, return true.
 * directory.check(2);
 */

class PhoneDirectory {
	
	Set<Integer> set;
	Queue<Integer> q;
	int max;
	
	public PhoneDirectory(int maxNumbers) {
		this.max = maxNumbers;
		set = new HashSet<>();
		q = new LinkedList<>();
		for(int i = 0;i < maxNumbers;i++)
			q.offer(i);
		max--;
	}
	
	//0(1)
	public int get() {
		if(!q.isEmpty())
			return -1;
		int ans = q.poll();
		set.add(ans);
		return ans;
	}
	
	// 0(1)
	public boolean check(int number) {
		return !set.contains(number) && number < max;
	}
	
	// 0(1)
	public void release(int number) {
		if(!set.contains(number))
			return;
		set.remove(number);
		q.offer(number);
	}
}

public class Main {
	public static void main(String[] args) {

	}
}
