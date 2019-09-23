package main;

import java.util.HashMap;
import java.util.Map;

class RLEIterator {
	
	int []A;
    public RLEIterator(int[] A) {
        this.A = A;
    }
    
    public int next(int n) {
    	if(n <= 0)
    		return -1;
    	int count = 0;
    	for(int i = 0;i < A.length;i+= 2) {
    		if(count == n)
    			return A[i-1];
    		if(A[i] == 0)
    			continue;
    		
    		if(A[i] + count >= n) {
    			A[i] -= n - count;
    			return A[i+1];
    		}
    		
    		count += A[i];
    		A[i] = 0;
    		
    	}
    	return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */

public class Main {
	public static void main(String[] args) {
		int []A = {3,8,0,9,2,5};
		RLEIterator obj = new RLEIterator(A);
		System.out.println(obj.next(2));
		System.out.println(obj.next(1));
		System.out.println(obj.next(1));
		System.out.println(obj.next(2));
	}
}