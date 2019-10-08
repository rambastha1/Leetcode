package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. 
 * Reconstruction means building a shortest common supersequence of the sequences in seqs 
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 * Example 1:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 * Output:
 * false
 * 
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a 
 * valid sequence that can be reconstructed.
 * 
 * Example 2:
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 * Output:
 * false
 * 
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * Example 3:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 * Output:
 * true
 * 
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 * Output:
 * true
 */

class Solution {
	public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		int n = org.length;
		int []indegree = new int[n+1];
		int count = 0;
		
		for(List<Integer> seq : seqs) {
			if(seq.size() == 1) {
				if(!graph.containsKey(seq.get(0)))
					graph.put(seq.get(0), new HashSet<>());
				continue;
			}
			for(int i = 0;i < seq.size()-1;i++) {
				if(!graph.containsKey(seq.get(i)))
					graph.put(seq.get(i), new HashSet<>());
				if(!graph.containsKey(seq.get(i+1)))
					graph.put(seq.get(i+1), new HashSet<>());
				
				indegree[seq.get(i+1)]++;
				graph.get(seq.get(i)).add(seq.get(i+1));
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		// node from 1 to n
		for(int i = 1;i < indegree.length;i++) {
			if(indegree[i] == 0)
				q.offer(i);
		}
		
		count = 0;
		while(!q.isEmpty()) {
			if(q.size() > 1)
				return false;
			int node = q.poll();
			if(org[count] != node)
				return false;
			if(!graph.containsKey(node))
				continue;
			for(int next : graph.get(node)) {
				indegree[next]--;
				if(indegree[next] == 0)
					q.offer(next);
			}
			count++;
		}
		return count == n && count == graph.size();
	}
}

public class Main {
	public static void main(String[] args) {
		/*int []org = {1,2,3};
		int [][]seqs = {{1,2}, {1,3}};*/
		
		int []org = {1,2,3};
		List<List<Integer>>seqs = Arrays.asList(Arrays.asList(1,2), Arrays.asList(1,2));
		System.out.println(new Solution().sequenceReconstruction(org, seqs));
	}
}
