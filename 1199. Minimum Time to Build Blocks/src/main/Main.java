package main;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387051/JavaC%2B%2BPython-Huffman's-Algorithm
// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
// https://www.youtube.com/watch?v=dM6us854Jk0&t=501s

/* You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built. 
 * A block can only be built by exactly one worker.

A worker can either split into two workers (number of workers increases by one) or build a block then go home. 
Both decisions cost some time.

The time cost of spliting one worker into two workers is given as an integer split. Note that if two workers split at the same time, 
they split in parallel so the cost would be split.

Output the minimum time needed to build all blocks.

Initially, there is only one worker.

Example 1:

Input: blocks = [1], split = 1
Output: 1
Explanation: We use 1 worker to build 1 block in 1 time unit.
Example 2:

Input: blocks = [1,2], split = 5
Output: 7
Explanation: We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.
Example 3:

Input: blocks = [1,2,3], split = 1
Output: 4
Explanation: Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
Then, use the two unassigned workers to build the first two blocks.
The cost is 1 + max(3, 1 + max(1, 2)) = 4.
Constraints:

1 <= blocks.length <= 1000
1 <= blocks[i] <= 10^5
1 <= split <= 100
 * 
 */

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