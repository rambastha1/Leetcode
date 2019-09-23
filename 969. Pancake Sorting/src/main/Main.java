package main;
import java.util.*;

class Solution {
    public List<Integer> pancakeSort(int[] A) {
    	int size = A.length-1;
    	List<Integer> res = new ArrayList<>();
    	while(size > 0) {
    		int index = 0;
    		for(int j = 0;j <= size;j++)
    			if(A[j] > A[index])
    				index = j;
    		flip(A, index);
    		flip(A, size);
    		res.add(index+1);
    		res.add(size+1);
    		size--;
    	}
    	return res;
    }
    
    void flip(int []A, int r) {
    	int l = 0;
    	while(l < r) {
    		int temp = A[l];
    		A[l] = A[r];
    		A[r] = temp;
    		l++;
    		r--;
    	}
    	
    }
    
    void print(int []A) {
    	for(int i : A)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {23, 10, 20, 11, 12, 6, 7};
		int []A = {3,2,4,1};
		System.out.println(new Solution().pancakeSort(A)); 
	}
}