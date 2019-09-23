package main;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Java Iterator interface reference:
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

// iter.hasNext() is different with PeekingIterator.hasNext()
class PeekingIterator implements Iterator<Integer> {

	Iterator<Integer> itr;
	Integer peek;
	
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    itr = iterator;
	    if(itr.hasNext())
	    	peek = itr.next();
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		// you should confirm with interviewer what to return/throw 
		// if there are no more values
		return peek;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if(!hasNext())
			throw new NoSuchElementException();
		Integer res = peek;
		if(itr.hasNext())
			peek = itr.next();
		else
			peek = null;
		return res;
	}

	@Override
	public boolean hasNext() {
	    return peek != null;
	}
}

public class Main {
	public static void main(String[] args) {

	}
}
