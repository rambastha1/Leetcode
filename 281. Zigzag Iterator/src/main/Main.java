package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * For example, given two 1d vectors:
 * v1 = [1, 2]
 * v2 = [3, 4, 5, 6]
 * By calling next repeatedly until hasNext returns false, the order of elements 
 * returned by next should be: [1, 3, 2, 4, 5, 6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 */

class ZigzagIterator {
	
	/* Amazing Solution
	 * Store iterators of each list
	 * If iterator is present in list, it means it has itr.next()
	 * for next, remove iterator, get element using itr.next()
	 * Now if it is not end of list add it back to the list or don't add if that list is exhausted
	 * Works for k lists as well
	 */
	
	LinkedList<Iterator<Integer>> list;
	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		list = new LinkedList<>();
		if(!v1.isEmpty())
			list.add(v1.iterator());
		if(!v2.isEmpty())
			list.add(v2.iterator());
	}
	
	public int next() {
		Iterator iterator = list.remove();
		int result = (Integer)iterator.next();
		if(iterator.hasNext())
			list.add(iterator);
		return result;
		
	}
	
	public boolean hasNext() {
		return !list.isEmpty();
	}
	
	//My solution working
	/*List<Integer> v1 = new ArrayList<>();
	List<Integer> v2 = new ArrayList<>();;
	
	public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
		this.v1 = v1;
		this.v2 = v2;				
	}
	
	private int c1 = 0, c2 = 0;
	public int next() {
		if((c1 <= c2 && c1 < v1.size()) || c2 >= v2.size()) {
			return v1.get(c1++);
		} else
			return v2.get(c2++);
	}
	
	public boolean hasNext() {
		if(v1 == null || v2 == null || c1+c2 >= v1.size()+v2.size())
			return false;		
		return true;
	}*/
}

public class Main {
	public static void main(String[] args) {
		List<Integer> v1 = Arrays.asList(1,2);
		
		List<Integer> v2 = Arrays.asList(3,4,5,6);
		ZigzagIterator z = new ZigzagIterator(v1, v2);
		while(z.hasNext()) 
			System.out.print(z.next() + " ");		
	}
}