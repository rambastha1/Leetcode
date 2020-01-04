package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* idea is to sort workers by wage/quality ratio, 0th index will have lowest amount and nth will be highest
 * if we give minimum wage to higher ratio worker, lower ratio worker will already be satisfied by its minimum wage
 * since Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group,
 * we want workers with lower quality than current one
 * 
 * 1- expect[i] = wage[i]/quality[i]: meaning i-th worker claims expect[i] money per each unit of its quality. 
 * Therefore, if expect[i] > expect[j], that means if we pay j-th worker quality[j]*expect[i] he/she would be more than happy 
 * and it's more than its minimal requested wage.

2- Therefore, for k workers sorted by their expect values, if we pay each worker q[i]*expect[k], both rules are satisfied. 
The total needed money = (sum(q_1 + q_2 + ... + q_k) * expect[k]). Note that this is the minimum money for this k workers, 
since you have to pay the k-th worker at least q[k]*expect[k].

3- To recap, we sort workers based on their expect values. Say we are at worker i and want to form a k-group and we already 
know it would cost sum*expect[i]. To pay the minimum money we should minimum the sum, which can be found using a 
maxHeap (to replace the max value with a smaller one) to keep the smaller q's as we move forward.

https://leetcode.com/problems/minimum-cost-to-hire-k-workers/discuss/141768/Detailed-explanation-O(NlogN)
https://leetcode.com/articles/minimum-cost-to-hire-k-workers/
 */

class Solution {
	
	// Time 0(nlgn) Space 0(n)
	class Worker {
		int wage, quality;
		double ratio;
		public Worker(int wage, int quality) {
			this.wage = wage;
			this.quality = quality;
			// be careful about int = int / int, double = double / int
			this.ratio = (wage*1.0)/quality;
		}
	}
	
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    	
    	int n = quality.length;
    	Worker []worker = new Worker[n];
    	for(int i = 0;i < n;i++)
    		worker[i] = new Worker(wage[i], quality[i]);
    	
    	Arrays.sort(worker, new Comparator<Worker>() {
			@Override
			public int compare(Worker o1, Worker o2) {
				return Double.compare(o1.ratio, o2.ratio);
			}
		});
    	
    	// create max- heap of qualities
    	PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
    	
    	/* sum is quality ratio is wage/quality
    	 * sum*ratio -> wage
    	 */
    	double ans = Integer.MAX_VALUE;
    	int sum = 0;
    	for(Worker w : worker) {
    		sum += w.quality;
    		pq.offer(w.quality);
    		// this will remove earlier highest quality worker
    		if(pq.size() > K) {
    			sum -= pq.poll();
    		}
    		
    		if(pq.size() == K) {
    			ans = Math.min(ans, sum*w.ratio);
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []quality = {10,20,5}, wage = {70,50,30};
		int K = 2;
		System.out.println(new Solution().mincostToHireWorkers(quality, wage, K));
	}
}
