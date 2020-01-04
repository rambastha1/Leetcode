package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* It is find number of Hamiltonian Paths
 * https://www.hackerearth.com/practice/algorithms/graphs/hamiltonian-path/tutorial/
 * Held-Karp Algorithm to find Number of Paths is 0(n^2 * 2^n) to check whether Hamiltonian Path exists or not
 * Can also be used to find number of paths
 * 943
 * Problem 847 DP method is Held-Karp Algorithm
 * https://leetcode.com/problems/find-the-shortest-superstring/discuss/194932/Travelling-Salesman-Problem
 * 
 * Here DFS is 0(N*N!) DP is 0(n^2 * 2^n)  
 */

class Solution {
	
	// DP Bellman Held Karp Algorithm
	// https://www.hackerearth.com/practice/algorithms/graphs/hamiltonian-path/tutorial/
	// https://leetcode.com/problems/number-of-squareful-arrays/discuss/238871/Java-DP-7ms
	// read it
	public int numSquarefulPerms(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        
        boolean[][] s = new boolean[n][n];
        // check which sums are square
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum = A[i] + A[j];
                int sqrt = (int)(Math.sqrt(sum)+0.1);
                s[i][j] = sqrt * sqrt == sum;
            }
        }
        
        int ans = 0;
        int[][] dp = new int[1<<n][n];
        for(int i = 0;i < n;i++) {
            dp[1<<i][i] = 1;
            while(i+1 < n && A[i+1] == A[i]) 
            	i++;
        }
        
        for(int i = 0;i < (1<<n);i++) {
            for(int j = 0;j < n;j++) {
                if(dp[i][j] > 0) {
                    for(int k = 0;k < n;k++) {
                    	// kth number not present in subset and sum is square
                    	/* check two nodes A[j] and A[k]
                    	 * since sorted check duplicates A[k-1]
                    	 * or is there a path in subset {S-k} ending at A[k-1]
                    	 * subset i | (1<<k) can end at k, its sum += dp[i][j]
                    	 */
                        if((i & (1<<k)) == 0 && s[j][k]){
                            if(k == 0 || A[k] != A[k-1] || (i & (1<<(k-1))) > 0) {
                                dp[i|(1<<k)][k] += dp[i][j];
                            }
                        }
                    }
                }
            }
        }
        
        // check for each path ending at A[i]
        for(int i = 0;i < n;i++) 
        	ans += dp[(1<<n)-1][i];
        return ans;
    }
	
	
	// DFS 0(N * N!) like Permutation 2 47
	int count = 0;
    public int numSquarefulPerms1(int[] A) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int a : A)
    		map.put(a, map.getOrDefault(a, 0) + 1);
    	Arrays.sort(A);
    	dfs(A, new ArrayList<>(), map);
    	return count;
    }
    
    void dfs(int []A, List<Integer> curr, Map<Integer, Integer> map) {
    	if(curr.size() == A.length) {
    		System.out.println(curr);
    		this.count++;
    	}
    	
    	for(int num : map.keySet()) {
    		if(curr.size() == 0 || (map.get(num) > 0 && issquare(curr.get(curr.size()-1) + num))) {
    			map.put(num, map.get(num) - 1);
    			curr.add(num);
    			dfs(A, curr, map);
    			map.put(num, map.get(num) + 1);
    			curr.remove(curr.size()-1);
    		}
    	}
    }
    
    private boolean issquare(int a) {
    	if(a == 1 || a == 0)
    		return true;
    	for(int i = 2;i <= (int)Math.sqrt(a);i++) {
    		if(i*i == a)
    			return true;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,17,8};
		System.out.println(new Solution().numSquarefulPerms(A));
	}
}
