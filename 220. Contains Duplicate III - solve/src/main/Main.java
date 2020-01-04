package main;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {
	/* bucket sort + sliding window
	 * to be in range t, create a bucket of size t+1(+1 to avoid div by 0 if t = 0)
	 * 0(N)
	 * https://leetcode.com/problems/contains-duplicate-iii/discuss/61645/AC-O(N)-solution-in-Java-using-buckets-with-explanation
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	    if (k < 1 || t < 0) return false;
	    Map<Long, Long> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        long num = (long) nums[i] - Integer.MIN_VALUE;
	     // why t+1 ? because, if t not plus 1, when t == 0, num divide by 0 will cause crash.
	        long bucket = num / ((long) t + 1); 
// means the key in the map duplicated, it means the must be exist two numbers that the different value between them are less than t
	     // if the two different numbers are located in two adjacent bucket, the value still might be less than t
	        if (map.containsKey(bucket) || (map.containsKey(bucket - 1) && num - map.get(bucket - 1) <= t)
	                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - num <= t))
	                        return true; // the same reason for -1
	        // maintain a bucket of size k
	        if (map.entrySet().size() >= k) { 
	            long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
	            map.remove(lastBucket);
	        }
	        map.put(bucket, num); //replace the duplicated key
	    }
	    return false;
	}
	
	
	
	/* https://leetcode.com/problems/contains-duplicate-iii/discuss/61655/Java-O(N-lg-K)-solution
	 * user8982
	 * change numbers to long to avoid overflow
	 */
	// 0(NlgK) balanced tree using treeset
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
    	if(nums == null || nums.length < 2 || k < 1)
    		return false;
    	TreeSet<Long> set = new TreeSet<>();
    	for(int i = 0;i < nums.length;i++) {
    		long num = (long) nums[i];
    		Long floor = set.floor(num), ceil = set.ceiling(num);
    		if((floor != null && num - floor <= t) || (ceil != null && ceil - num <= t))
    			return true;
    		set.add(num);
    		if(i >= k)
    			set.remove((long)nums[i-k]);
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {-1,2147483647};
		int k = 1, t = 2147483647;
		System.out.println(new Solution().containsNearbyAlmostDuplicate(nums, k, t));
	}
}
