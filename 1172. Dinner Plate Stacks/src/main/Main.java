package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode.com/problems/dinner-plate-stacks/discuss/366368/Java-straightforward-HashMap-%2B-2-Pointers
class DinnerPlates {
    /*
    Maintain 2 pointers to keep track of where to push/pop an element
    */
    
    //map of stacks 
    Map<Integer, Stack<Integer>> map; 
    int capacity, push_index, pop_index, count; 
    
    //initialize the object with the max capacity of the stacks 
    public DinnerPlates(int capacity) {
        this.capacity = capacity; 
        this.push_index = 0; //where to push
        this.pop_index = 0; //where to pop
        this.count = 0; //number of elements 
        map = new HashMap<>(); 
        map.put(push_index, new Stack<>()); 
    }
    
    //pushes the given positive integer val into the leftmost stack 
    //with size less than capacity 
    public void push(int val) {
        //move to the first index whose stack has capacity to be pushed
        while (map.containsKey(push_index) && map.get(push_index).size() == capacity) {
            push_index++; 
        }
        if (!map.containsKey(push_index)) {
            map.put(push_index, new Stack<>()); 
        }
        map.get(push_index).push(val); 
        //update pop index to be the last one after push
        pop_index = Math.max(pop_index, push_index); 
        count++;
    }
    
    //returns the value at the top of the rightmost non-empty stack
    //and removes it from the stack. Returns -1 if all stacks are empty. 
    public int pop() {
        if (count == 0) return -1; 
        //move to the first index whose stack is non-empty
        while (pop_index >= 0 && map.get(pop_index).isEmpty()) {
            pop_index--;
        }
        count--; 
        //update push index to be the first one after pop
        push_index = Math.min(push_index, pop_index); 
        return map.get(pop_index).pop(); 
    }
    
    //returns the value at the top of the stack with the given index
    //and removes it from that stack, and returns -1 if the stack with
    //that given index is empty. 
    public int popAtStack(int index) {
        if (!map.containsKey(index) || map.get(index).isEmpty()) return -1; 
        count--; 
        //update push index to be the first one after pop
        push_index = Math.min(push_index, index); 
        return map.get(index).pop(); 
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */

public class Main {
	public static void main(String[] args) {

	}
}
