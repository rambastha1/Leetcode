package main;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-k-closest-elements/discuss/106419/O(log-n)-Java-1-line-O(log(n)-%2B-k)-Ruby

class Solution {
	// Time 0(lgN + k) constant space
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
    	// l will merge to index with lowest distance from x
    	int l = 0, r = arr.length-1;
    	while(l <= r) {
// for subarray starting at mid with size k+1, we compare element of two ends to eliminate the loser
    		int m = l + (r-l)/2;
    		if((m+k <= arr.length-1) && Math.abs(arr[m]-x) > Math.abs(arr[m+k]-x))
    			l = m+1;
    		else
// eliminate r even if Math.abs(arr[m]-x) == Math.abs(arr[m+k]-x) we need smallest numbers
    			r = m-1;
    	}
    	// res will be a consecutive subarray of k size
    	List<Integer> res = new ArrayList<>();
    	// Time 0(k)
    	for(int i = 0;i < k;i++)
    		res.add(arr[l+i]);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {1,2,3,4,5};
		int k = 4, x = 3;
		System.out.println(new Solution().findClosestElements(arr, k, x));
	}
}
