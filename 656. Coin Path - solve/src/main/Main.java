package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. 
 * The integer B denotes that from any place (suppose the index is i) in the array A, 
 * you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. 
 * Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in
 * the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. 
You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the 
place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:

Input: [1,2,4,-1,2], 2
Output: [1,3,5]
 

Example 2:

Input: [1,2,4,-1,2], 1
Output: []
 

Note:

Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i 
where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
Length of A is in the range of [1, 1000].
B is in the range of [1, 100].
 * 
 */

/* I think using the backward approach in the dynamic programming solution is determined by the nature of the lexicographical order. 
 * In a path, elements in a lower position are more important.

Problems with the forward approach:
As it goes from 1 to n, we lose the prefix of the current optimal path for each possible previous jump. To avoid losing this 
information, we need to explicitly track all of the whole optimal paths for each previous index, which is not sufficient.

With the backward approach, we always focus on the left-most elements on a path. At index i, if we have two optimal paths 
path_1 and path_2 with the same cost, they have different next jumps jmp_i and jmp_j, we alway select the path with a 
lower index min(jmp_i, jmp_j) for the next jump.
 * It is OK to walk forward. But it is difficult to find the lexicographically smallest path when there are two or more paths 
 * having the same minimum cost.
 * https://leetcode.com/problems/coin-path/discuss/106295/C%2B%2B-DP-O(nB)-time-O(n)-space
 */

class Solution {
	
	// Time 0(N*B) Space 0(N)
    public List<Integer> cheapestJump(int[] A, int B) {
    	List<Integer> res = new ArrayList<>();
    	if(A == null || A.length < 1 || A[A.length-1] == -1)
    		return res;
    	int n = A.length;
    	
    	// forward stores the next index it can jump with minimum cost
    	// cost stores cost of reaching i from j where i < j working backwards
    	int []forward = new int[n], cost = new int[n];
    	Arrays.fill(forward, -1);
    	Arrays.fill(cost, Integer.MAX_VALUE);
    	
    	cost[n-1] = A[n-1];
    	for(int i = n-2;i >= 0;i--) {
    		if(A[i] == -1)
    			continue;
    		for(int j = i+1;j <= Math.min(i+B, n-1);j++) {
    			if(cost[i] > cost[j] + A[i] && cost[j] != Integer.MAX_VALUE) {
    				cost[i] = cost[j] + A[i];
    				forward[i] = j;
    			}
    		}
    	}
    	
    	if(cost[0] == Integer.MAX_VALUE)
    		return res;
    	
    	// since leftmost forward will store the rightmost you could move keep adding
    	int k = 0;
    	while(k != -1) {
    		res.add(k+1);
    		k = forward[k];
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {1,2,4,-1,2};
		int []A = {1};
		int B = 1;
		System.out.println(new Solution().cheapestJump(A, B));
	}
}