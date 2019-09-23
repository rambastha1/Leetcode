package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] shortestToChar(String S, char C) {
    	if(S == null || C == ' ' || S.length() == 0)
    		return new int[] {};
    	int n = S.length();
    	int []res = new int[n];
		
    	// Store indices of C in S
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0;i < n;i++)
    		if(S.charAt(i) == C)
    			list.add(i);
		
    	// Counter to fill result array
    	int index = 0;
		
    	// For the first occurrence of C, fill res array
    	while(index < list.get(0)) {
    		res[index] = list.get(0) - index;
    		index++;
    	}
    	/* For rest all we need to do is store the minimum of difference
		 * between that index and nearest C's index on either side as C's indices 
		 * outside this window won't affect its distance
		 * 
		 * For eg 
		 * 0 1 2 3 4 5 6 7 8 9 10 11
		 * l o v e l e e t c o d  e
		 * 
		 * for indices 0-2 above loop will update distance
		 * for indices 4, 7-10 we need to compare its nearest occurrence of C i.e
		 * (3 and 5 for 4) and (6 and 11 for 7-10)
		 */
    	for(int i = 1;i < list.size();i++) {
    		if(S.charAt(index) == C) index++;
    		while(index < list.get(i)) {
    			res[index] = Math.min(index-list.get(i-1), list.get(i) - index);
    			index++;
    		}
    	}
    	// At the end if characters are left after last occurrence of C in S, update their distances
    	index++;
    	while(index < n) {
    		res[index] = res[index-1] + 1;
    		index++;
    	} 
    	return res;
    }
    
    void print(int []dp) {
    	for(int i : dp)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		/*String S = "loveleetcode";
		char C = 'e';*/
		String S = "aaba";
		char C = 'b';
		new Solution().shortestToChar(S, C);
	}
}
