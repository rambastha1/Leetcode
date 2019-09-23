package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {

	// @return true if this NestedInteger holds a single integer, rather than a nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds, if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
	
	List<Integer> list;
	int index = 0;
	// Recursive
	public NestedIterator(List<NestedInteger> nestedList) {
		list = new ArrayList<>();
		flatten(nestedList);
	}
	
	public void flatten(List<NestedInteger> nestedList) {
		for(NestedInteger nested : nestedList) {
			if(nested.isInteger())
				list.add(nested.getInteger());
			else {
				flatten(nested.getList());
			}
		}
	}
	
	@Override
    public Integer next() {
		return list.get(index++);
	}
	
	
	@Override
    public boolean hasNext() {
		return index < list.size();
	}
	
	//Iterative Approach
	/*Stack<NestedInteger> stack = new Stack<>();
    
	public NestedIterator(List<NestedInteger> nestedList) {
		for(int i = nestedList.size()-1;i >= 0;i--)
			stack.push(nestedList.get(i));
    }

    @Override
    public Integer next() {
    	return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
    	while(!stack.isEmpty()) {
    		NestedInteger curr = stack.peek();
    		if(curr.isInteger())
    			return true;
    		
    		stack.pop();
    		for(int i = curr.getList().size()-1;i >= 0;i--)
    			stack.push(curr.getList().get(i));
    	}
    	return false;
    }*/
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class Main {
	public static void main(String[] args) {
		 
	}
}