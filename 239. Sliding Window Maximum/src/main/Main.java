package main;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
	
	//Using Array deque
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if(nums == null || nums.length == 0 || nums.length < k)
    		return new int[0];
    	
    	Deque<Integer> deque = new ArrayDeque<>();
    	int index = 0;
    	int []res = new int[nums.length-k+1];
    	for(int i = 0;i < k;i++) {
    		while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
    			deque.removeLast();
    		deque.offerLast(i);
    	}
    	
    	for(int i = k;i < nums.length;i++) {
    		res[index++] = nums[deque.peekFirst()];
    		while(!deque.isEmpty() && i - deque.peekFirst() > k)
    			deque.pollFirst();
    		while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
    			deque.pollLast();
    		deque.offerLast(i);
    	}
    	res[index] = deque.peekFirst();
    	return res;
    }
	
	
	//Using array only
	/*public int[] maxSlidingWindow(int[] nums, int k) {
        
        - Maximum in a set of numbers : One pass. index of it.
        - If index, 0, next time recalualate
        - If index not 0, next time only check the next number.
        - Keep storing max numbers in an array.
        
        
        //Edge cases 0 and 1 
        if(k == 0 || nums.length < k) {
            return new int [0];
        } else if (nums.length == 1 && k==1) {
            return nums;
        }
        
        int[] maxs = new int [nums.length - k + 1];
        int indexOfMax=0;
        int max = Integer.MIN_VALUE;
        int start=0,end=k-1;
            
        // Full Pass of window
        for(int i = 0;i <= end;i++) {
            if(nums[i] > max) {
                max = nums[i];
                indexOfMax = i;
            } 
        }
        
        maxs[0] = max;
        start++;
        end++;
        
        for( ; end < nums.length ; start++,end++) {
            if(start > indexOfMax) {
                // Full Pass
                if (k == 1) { // Egde cases all around
                    max = nums[start];
                    indexOfMax = start;
                } else {
                    max = Integer.MIN_VALUE; // For a fresh pass, i had to reset max
                    for(int i=start;i <=end;i++) {
                        if(nums[i] > max) {
                            max = nums[i];
                            indexOfMax = i;
                        } 
                    }
                }
            } else {
                // Only check the newest entry in window
                if(nums[end] > max) {
                    max = nums[end];
                    indexOfMax = end;
                }
            }
            maxs[start] = max;
        }
        
        return maxs;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		int []res = new Solution().maxSlidingWindow(nums, k);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}