/*
 * Implement an iterator to flatten a 2d vector.
 * For example, Given 2d vector =
 * [ [1,2],
 *   [3],
 *   [4,5,6] ]
 * 
 * By calling next repeatedly until hasNext returns false, 
 * the order of elements returned by next should be:  [1,2,3,4,5,6].
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java
 */

package main;
/*
 * Implement an iterator to flatten a 2d vector.
 * For example,
 * Given 2d vector =
 * [
 * [1,2],
 * [3],
 * [4,5,6]
 * ]
 * 
 * By calling next repeatedly until hasNext returns false, 
 * the order of elements returned by next should be: [1,2,3,4,5,6].
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Vector2D {
	private Iterator<Integer[]> i;
    private Iterator<Integer> j;
	
	public Integer next() {
        hasNext();
        return j.next();
    }
	
    public boolean hasNext() {
		//go to next row
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
	}
	
	public Vector2D(int[][] vec2d) {
        i = vec2d.iterator();
	}
}

public class Main {
	public static void main(String[] args) {

	}
}