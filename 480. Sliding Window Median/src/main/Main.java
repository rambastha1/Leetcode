package main;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Supplier;
/* brute force 0(nk) for each window find len/2 smallest number using quick select for odd and two for even length window
 * we could use 2 pq as in 295 but pq.remove is 0(k) thus overall complexity is 0(nk)
 * use 2 treeset for small and large treeset remove is 0(lgn) thus overall complexity is 0(nlgk) 
 * add to large/small -> balance -> after window passes remove from small/large -> balance
 * median is similar peek of small or both/2
 * 
 * we need minimum of numbers larger than num and maximum of numbers smaller than num for median
 * https://leetcode.com/problems/sliding-window-median/discuss/96346/Java-using-two-Tree-Sets-O(n-logk)
 */
class Solution {
	public double[] medianSlidingWindow(int[] nums, int k) {
	    Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
	    TreeSet<Integer> small = new TreeSet<>(comparator.reversed());
	    TreeSet<Integer> big = new TreeSet<>(comparator);
	    
	    double[] res = new double[nums.length - k + 1];
	    
	    for (int i = 0; i < k; i++) {
	    	small.add(i);
	    }
	    balance(k, nums, small, big);
	    res[0] = median(k, nums, small, big);
	    
	    for (int i = k, index = 1; i < nums.length; i++, index++) {
	        // remove tail of window from either left or right
	        if(!small.remove(i - k)) 
	        	big.remove(i - k);

	        // add next num, this will always increase left size
	        big.add(i); 
	        small.add(big.pollFirst());
	        
	        // rebalance left and right, then get median from them
	        balance(k, nums, small, big);
	        res[index] = median(k, nums, small, big);
	    }
	    return res;
	}
	
	private void balance(int k, int []nums, TreeSet<Integer> small, TreeSet<Integer> big) {
		while(small.size() > big.size())
			big.add(small.pollFirst());
	}
	
	private double median(int k, int []nums, TreeSet<Integer> small, TreeSet<Integer> big) {
		if(k%2 == 1)
			return (double)nums[big.first()];
		double sum = (double)nums[big.first()] + (double)nums[small.first()]; // large int values
		return (double)(sum/2);
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		System.out.println(Arrays.toString(new Solution().medianSlidingWindow(nums, k)));
	}
}
