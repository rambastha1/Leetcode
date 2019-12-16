package main;

/* Given an array consisting of n integers, find the contiguous sub array whose length is greater than or equal to k that 
 * has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
 * 
 */

/* To understand the idea behind this method, let's look at the following points.

Firstly, we know that the value of the average could lie between the range (min, max)(min,max). 
Here, minmin and maxmax refer to the minimum and the maximum values out of the given numsnums array. 
This is because, the average can't be lesser than the minimum value and can't be larger than the maximum value.

But, in this case, we need to find the maximum average of a subarray with atleast kk elements. 
The idea in this method is to try to approximate(guess) the solution and to try to find if this solution really exists.

If it exists, we can continue trying to approximate the solution even to a further precise value, 
but choosing a larger number as the next approximation. But, if the initial guess is wrong, 
and the initial maximum average value(guessed) isn't possible, we need to try with a smaller number as the next approximate.

Now, instead of doing the guesses randomly, we can make use of Binary Search. With minmin and maxmax 
as the initial numbers to begin with, we can find out the midmid of these two numbers given by (min+max)/2(min+max)/2. Now, 
we need to find if a subarray with length greater than or equal to kk is possible with an average sum greater than this midmid value.

To determine if this is possible in a single scan, let's look at an observation. Suppose, there exist jj 
elements, a_1, a_2, a_3..., a_j in a subarray within numsnums such that their average is greater than midmid. 
In this case, we can say that

(a_1+a_2+ a_3...+a_j)/j≥mid or

(a_1+a_2+ a_3...+a_j)≥j∗mid or

(a_1-mid) +(a_2-mid)+ (a_3-mid) ...+(a_j−mid)≥0

Thus, we can see that if after subtracting the midmid number from the elements of a subarray with more than k-1k−1 elements, 
within numsnums, if the sum of elements of this reduced subarray is greater than 0, we can achieve an average value greater 
than midmid. Thus, in this case, we need to set the midmid as the new minimum element and continue the process.

Otherwise, if this reduced sum is lesser than 0 for all subarrays with greater than or equal to kk elements, 
we can't achieve midmid as the average. Thus, we need to set midmid as the new maximum element and continue the process.

In order to determine if such a subarray exists in a linear manner, we keep on adding nums[i]-midnums[i]−mid to 
the sumsum obtained till the ith element while traversing over the numsnums array. If on traversing the first kk elements, 
the sumsum becomes greater than or equal to 0, we can directly determine that we can increase the average beyond midmid. 
Otherwise, we continue making additions to sumsum for elements beyond the kth element, making use of the following idea.

If we know the cumulative sum upto indices i and j, say sum_i and sum_j respectively, 
we can determine the sum of the subarray between these indices(including jj) as sum_j - sum_i. 
In our case, we want this difference between the cumulative sums to be greater than or equal to 0 as discusssed above.

Further, for sum_i as the cumulative sum upto the current(ith) index, all we need is sumj−sumi ≥0 such that j - i ≥ k

To achive this, instead of checking with all possible values of sumi, we can just consider the 
minimum cumulative sum upto the index j - kj−k. This is because if the required condition can't be sastisfied with the minimum sumi
, it can never be satisfied with a larger value.

To fulfil this, we make use of a prevprev variable which again stores the cumulative sums but, 
its current index(for cumulative sum) lies behind the current index for sumsum at an offset of kk units. 
Thus, by finding the minimum out of prevprev and the last minimum value, we can easily find out the required minimum sum value.

Every time after checking the possiblility with a new midmid value, at the end, 
we need to settle at some value as the average. But, we can observe that eventually, we'll reach a point, 
where we'll keep moving near some same value with very small changes. In order to keep our precision in control, 
we limit this process to 10^-510 
−
 5 precision, by making use of errorerror and continuing the process till errorerror becomes lesser than 0.00001 .
 * 
 */

/* Similar to Sliding Window technique, for first k elements, we get the cumulative sum among them, cur. 
 * If it is smaller than 0, we slide the window. Attention that, prev keeps track of cumulative sum in front of the current window. 
 * If prev is smaller than 0, the cumulative sum of current window, which equals to cur - prev, becomes 
 * bigger, i.e., is more possible to >= 0.
 * 
 * https://leetcode.com/problems/maximum-average-subarray-ii/discuss/132164/Java-Clear-Code-using-Binary-Search-with-Detailed-Explanations
 */

class Solution {
    public double findMaxAverage(int[] nums, int k) {
    	// max average lie between minimum and maximum element of nums
    	double l = -10001, r = 10001;
    	while(r-l > 0.00001) {
    		double m = l + (r-l)/2;
    		if(canfindlargerAvg(nums, k, m))
    			l = m;
    		else
    			r = m;
    	}
    	return l;
    }
    
    private boolean canfindlargerAvg(int []nums, int k, double avg) {
    	int n = nums.length;
    	double []sum = new double[n];
    	// add this average to array to check if >= 0
    	for(int i = 0;i < n;i++)
    		sum[i] = nums[i] - avg;
    	
    	double curr = 0, prev = 0;
    	for(int i = 0;i < k;i++)
    		curr += sum[i];
    	// can find larger average no need to check larger sub array
    	if(curr >= 0)
    		return true;
    	
    	// check for larger than k size sub array
    	for(int i = k;i < n;i++) {
    		curr += sum[i];
    		prev += sum[i-k];
    		// if previous subarray sum < 0, then curr - prev might be >= 0, check if true return true no need to check further
    		if(prev < 0) {
    			curr -= prev;
    			prev = 0;
    		}
    		if(curr >= 0)
    			return true;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,12,-5,-6,50,3};
		int k = 4;
		System.out.println(new Solution().findMaxAverage(nums, k));
	}
}