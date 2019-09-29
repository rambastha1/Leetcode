package main;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387051/JavaC%2B%2BPython-Huffman's-Algorithm
// https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
// https://www.youtube.com/watch?v=dM6us854Jk0&t=501s

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