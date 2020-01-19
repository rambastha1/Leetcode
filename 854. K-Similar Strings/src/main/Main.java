package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* The trick is that you just need to swap the first (a,b)->(b,a) pair every round, instead of other pairs like (c,d)->(d,c) 
 * (otherwise it would cause TLE), and BFS would guarantee the shortest path.
 * 
 * its like start moving from 0 to n, find first unmatched character, fill up using possible outcomes and process again
 * every step i we are making character @ i, correct if not correct
 * double ended BFS will also work
 * https://leetcode.com/problems/k-similar-strings/discuss/140099/JAVA-BFS-32-ms-cleanconciseexplanationwhatever
 */
class Solution {
    public int kSimilarity(String A, String B) {
    	int n = A.length();
    	if(A.equals(B))
    		return 0;
    	Queue<String> q = new LinkedList<>();
    	Set<String> visited = new HashSet<>();
    	q.offer(A);
    	visited.add(A);
    	int step = 0;
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			String str = q.poll();
    			if(str.equals(B))
    				return step;
    			int index = 0;
    			while(str.charAt(index) == B.charAt(index))
    				index++;
    			for(int j = index+1;j < n;j++) {
    				char c = str.charAt(j);
    				// if(A[j] == B[j] it is in-place don't swap or A[j] is not required character
    				if(B.charAt(j) == c || B.charAt(index) != c)
    					continue;
    				String temp = swap(str, index, j);
    				if(!visited.contains(temp)) {
    					visited.add(temp);
    					q.offer(temp);
    				}
    			}
    		}
    		step++;
    	}
    	return step;
    }
    
    private String swap(String str, int i, int j) {
    	char []arr = str.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
    	return new String(arr);
    }
}

public class Main {
	public static void main(String[] args) {
		String A = "bccaba", B = "abacbc";
		System.out.println(new Solution().kSimilarity(A, B));
	}
}
