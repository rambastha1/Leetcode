package main;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387051/JavaC%2B%2BPython-Huffman's-Algorithm
// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
// https://www.youtube.com/watch?v=dM6us854Jk0&t=501s

/* We can model this entire question as a binary tree that we need to construct with a minimum max depth cost. 
 * Each of the blocks is a leaf node, with a cost of its face value. And then each inner node will be of cost split. 
 * nodes that are sitting at the same level represent work that is done in parallel. We know there will be len(blocks) - 1 
 * of these inner nodes, so the question now is how can we construct the tree such that it has the minimum depth.
 * how can we optimize the construction of this tree? Huffman's algorithm!
 */
class Solution {
    public int minBuildTime(int[] blocks, int split) {
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	for(int b : blocks)
    		pq.offer(b);
    	while(pq.size() > 1) {
    		int a = pq.poll();
    		int b = pq.poll();
    		pq.offer(Math.max(a, b) + split);
    	}
    	return pq.poll();
    }
}

public class Main {
	public static void main(String[] args) {
		int []blocks = {1,1,1,1};
		int split = 100;
		System.out.println(new Solution().minBuildTime(blocks, split));
	}
}