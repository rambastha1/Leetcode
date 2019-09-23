package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation

class Solution {
	/* put first k elements from nums1 into pq, now increase index in nums2 of elements in pq based on smaller sum
	 */
	// Time 0(KlgK)
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> res = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0 || k==0) return res;
		
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]+o1[1]-o2[0]-o2[1];
			}
		});
        
        for(int i=0; i<nums1.length && i<k; i++) 
        	pq.offer(new int[]{nums1[i], nums2[0], 0});
        
        while(k-- > 0 && !pq.isEmpty()){
            int[] pair = pq.poll();
            res.add(Arrays.asList(pair[0], pair[1]));
            if(pair[2] == nums2.length-1) continue;
            pq.offer(new int[]{pair[0],nums2[pair[2]+1], pair[2]+1});
        }
        return res;
	}
}
public class Main {
	public static void main(String[] args) {
		/*int []nums1 = {1,7,11}, nums2 = {2,4,6};
		int k = 3;*/
		/*int []nums1 = {1,1,2}, nums2 = {1,2,3};
		int k = 2;*/
		int []nums1 = {1,2}, nums2 = {3};
		int k = 3;
		System.out.println(new Solution().kSmallestPairs(nums1, nums2, k));
	}
}
