package main;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// https://www.youtube.com/watch?time_continue=2&v=Fqal25ZgEDo&feature=emb_logo
class Solution {
	// Time 0(M * NlgN)
	/* From my understanding, assume there are n arrays with m elements in each array in this nums matrix. 
	 * Heap operation poll is logn, and we need to poll nm times for the worst case. So the total time should be O(nm*logn).
	 */
	public int[] smallestRange(List<List<Integer>> nums) {
    	int m = nums.size();
    	PriorityQueue<int []> pq = new PriorityQueue<>((a,b) -> (a[0]-b[0]));
    	int max = nums.get(0).get(0);
    	for(int i = 0;i < m;i++) {
    		pq.offer(new int[] {nums.get(i).get(0), i, 0});
    		max = Math.max(max, nums.get(i).get(0));
    	}
    	
    	int start = -1, minrange = Integer.MAX_VALUE;
    	// because it needs to contain atleast one element from each list
    	while(pq.size() == m) {
    		int []node = pq.poll();
    		int val = node[0], aindex = node[1], index = node[2];
    		if(max - val < minrange) {
    			minrange = max - val;
    			start = val;
    		}
    		
    		if(index < nums.get(aindex).size()-1) {
    			index++;
    			pq.offer(new int[] {nums.get(aindex).get(index), aindex, index});
    			max = Math.max(max, nums.get(aindex).get(index));
    		}
    	}
    	return new int[] {start, start+minrange};
    }
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> nums = Arrays.asList(Arrays.asList(4,10,15,24,26), Arrays.asList(0,9,12,20), Arrays.asList(5,18,22,30));
		System.out.println(Arrays.toString(new Solution().smallestRange(nums)));
	}
}
