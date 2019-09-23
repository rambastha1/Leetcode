package main;

import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)

class Solution {
	// Time 0(N^2)
    public String getPermutation(int n, int k) {
    	StringBuilder sb = new StringBuilder();
    	List<Integer> list = new ArrayList<>();
    	int factorial = 1;
    	
    	for(int i = 1;i <= n;i++) {
    		factorial *= i;
    		list.add(i);
    	}
    	
    	k--;
    	for(int i = 0;i < n;i++) {
    		factorial /= (n-i);
    		int index = k / factorial;
    		sb.append(list.remove(index));
    		k -= index *factorial;
    	}
    	return sb.toString();
    }
    
}

public class Main {
	public static void main(String[] args) {
		int n = 4, k = 14;
		System.out.println(new Solution().getPermutation(n, k));
	}	
}
