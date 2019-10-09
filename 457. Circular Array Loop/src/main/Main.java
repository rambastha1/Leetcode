package main;

class Solution {
	/* same logic as slow and fast pointer of linked list. 
	 * multiplication is used for same direction as multiplication of positive*positive and -ve*-ve > 0
	 * if loop is not found element is set to 0 thereby marking that no loop can be found once we reach this element (kinda dp)
	 * 
	 * To those wondering why this is linear O(n) time complexity and not O(n^2): 
	 * A nested loop structure does not necessarily imply O(n^2) time complexity. We can apply amortized analysis: 
	 * the algorithm visits each index a maximum of 4 times (visit fast, visit fast, mark zero, skip zero), 
	 * and because 4 is a constant factor we have O(4n) -> O(n) time complexity. Hope that helps.
	 */
	
	// Amortized 0(N) time constant space
	public boolean circularArrayLoop(int[] nums) {
		int n = nums.length;
    	if(n <= 1)
            return false;
    	for(int i = 0;i < n;i++) {
    		if(nums[i] == 0)
    			continue;
    		int j = i, k = getindex(nums, n, i);
    		// multiplication takes care of direction
    		while(nums[i]*nums[k] > 0 && nums[i]*nums[getindex(nums, n, k)] > 0) {
    			if(j == k) {
    				if(j == getindex(nums, n, j))
    					break;
    				return true;
    			}
    			j = getindex(nums, n, j);
    			int temp = getindex(nums, n, k);
    			k = getindex(nums, n, temp);
    		}
    		
    		// no loop found
    		j = i;
    		int val = nums[i];
    		while(nums[j]*val > 0) {
    			int next_index = getindex(nums, n, j);
    			nums[j] = 0;
    			j = next_index;
    		}
    	}
		return false;
	}
	
	private int getindex(int []nums, int n, int i) {
		// no need to take abs and sub from n if (i+nums[i])< 0
		// add n to it, gives same result
		return (i+nums[i])>=0?(i+nums[i])%n:n+((i+nums[i])%n);
	}
	
	// Time 0(n^2) constant space
	public boolean circularArrayLoop1(int[] nums) {
    	int n = nums.length;
    	if(n == 1)
            return false;
    	for(int i = 0;i < n;i++) {
    		boolean dir = nums[i]>0?true:false;
    		int len = 1, index = (i+nums[i])<0?n-(Math.abs(i+nums[i])%n):Math.abs(i+nums[i])%n;
    		while(true) {
    			if(index == i && len > 1)
    				return true;
    			if((dir && nums[index] < 0) || (!dir && nums[index]>0) || len > n || (index == i && len == 1))
    				break;
    			index = (index+nums[index])<0?n-(Math.abs(index+nums[index])%n):Math.abs(index+nums[index])%n;
    			len++;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {2,-1,1,2,2};
		//int []nums = {-1,2};
		//int []nums = {-2,1,-1,-2,-2};
		int []nums = {-1,-1,-3};
		System.out.println(new Solution().circularArrayLoop(nums));
	}
}
