package main;

import java.util.Arrays;
import java.util.Stack;

/* leetcode 996
 * https://www.hackerearth.com/practice/algorithms/graphs/hamiltonian-path/tutorial/
 * 
 * The i of dp[i][j] and path[i][j] represents the set of nodes we want to travel
Assume we want to travel nodes: {n1, n2, n3, n4}
i = 2 ^ n1 +2 ^ n2 +2 ^ n3 +2 ^ n4;
In other words, we use every bit of a binary number(this number is i) to represent the status of 
each node( if i = 10011(binary number), means the node set = {0,1,4});
j of dp[i][j] and path[i][j] means the last node we travelled.
dp[i][j] = the min length if we travel all nodes in the node set i and the last travelled node is j.
path[i][j] = the node before j(j is the last one).

Example:
Assume we want to travel points:{0,2,3,5}
i = (2^0+ 2^2 +2^3 + 2^5) = 44;
dp[44][2] means "the min length when we travel the points{0,2,3,5} and the last one is 2";
path[44][2] means "the last node before we travelled 2. In other words,the last node when travelling{0,3,5}"
 */
class Solution {
    public String shortestSuperstring(String[] A) {
    	int n = A.length;
    	int [][]graph = new int[n][n];
    	// build graph
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < n;j++) {
    			graph[i][j] = calc(A[i], A[j]);
    			graph[j][i] = calc(A[j], A[i]);
    		}
    	}
    	
    	int [][]dp = new int[1<<n][n];
    	int [][]path = new int[1<<n][n];
    	// last is used for circuit construction
    	int last = -1, min = Integer.MAX_VALUE;
    	// for all the combinations of the nodes
    	for(int i = 1;i < (1<<n);i++) {
    		Arrays.fill(dp[i], Integer.MAX_VALUE);
    		 //for each node 
    		for(int j = 0;j < n;j++) {
    			// if the node is in the set. Assume i = 10010(18), j = 100(4), then set={1,4}, the node is 2. The node is not in this set
    			if((i & (1<<j)) == 1) {
    				// the set without j. Assume i = 10010, j = 10 then pre = 10000
    				int prev = i - (1<<j);
    				// if j is the only one
    				if(prev == 0) {
    					// the whole word
    					dp[i][j] = A[j].length();  
    				} else {
    					//try all the possible nodes before j
    					for(int k = 0;k < n;k++) {
    						// if k is valid and the length could be reduced
    						if(dp[prev][k] < Integer.MAX_VALUE && dp[prev][k] + graph[k][j] < dp[i][j]) {
    							//update the result
    							dp[i][j] = dp[prev][k] + graph[k][j];
    							// update the node before j
    							path[i][j] = k;
    						}
    					}
    				}
    			}
    			// if i == 11...1111 means the node set contains all the nodes, and the length is smaller
    			if(i == (1<<n) - 1 && dp[i][j] < min) {
    				//update the result
    				min = dp[i][j];
    				//update the last node
    				last = j;
    			}
    		}
    	}
    	
    	// build path
    	StringBuilder sb = new StringBuilder();
    	int curr = (1<<n) - 1;
    	Stack<Integer> stack = new Stack<>();
    	while(curr > 0) {
    		stack.push(last);
    		int temp = curr;
    		// delete last state
    		curr -= (1<<last);
    		last = path[temp][last];
    	}
    	
    	//build result
    	int i = stack.pop();
    	sb.append(A[i]);
    	while(!stack.isEmpty()) {
    		int j = stack.pop();
    		sb.append(A[j].substring(A[j].length() - graph[i][j]));
    		i = j;
    	}
    	return sb.toString();
    }
    
    private int calc(String a, String b) {
    	for(int i = 1;i < a.length();i++) {
    		if(b.startsWith(a.substring(i))) {
    			return b.length() - a.length() + i;
    		}
    	}
    	return b.length();
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
