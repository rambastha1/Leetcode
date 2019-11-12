package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* Design and implement a data structure for a compressed string iterator. 
 * It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer 
representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are 
persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
 * 
 * https://leetcode.com/problems/design-compressed-string-iterator/discuss/103828/Java-Concise-Single-Queue-Solution
 */

class StringIterator {
	
	Queue<int[]> q;
    public StringIterator(String compressedString) {
        this.q = new LinkedList<>();
        int i = 0, n = compressedString.length();
        while(i < n) {
        	int j = i+1;
        	while(j < n && Character.isDigit(compressedString.charAt(j)))
        		j++;
        	int val = Integer.parseInt(compressedString.substring(i+1, j));
        	// store character as number
        	q.offer(new int[] {compressedString.charAt(i) - 'A', val});
        	i = j;
        }
    }
    
    public char next() {
        if(!hasNext())
        	return ' ';
        int []node = q.peek();
        if(node[1] == 1)
        	q.poll();
        node[1]--;
        return (char)('A' + node[0]);
    }
    
    public boolean hasNext() {
        return !this.q.isEmpty();
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

public class Main {
	public static void main(String[] args) {
		String str = "L1e2t1C1o1d1e1";
		StringIterator itr = new StringIterator(str);
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
	}
}