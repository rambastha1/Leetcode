package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/* RANDOMLY Reorder Array in O(N).mp4 video
 * Fisher - Yates shuffle Algorithm
 */
class Solution {
	
	int []nums, temp;
    public Solution(int[] nums) {
    	this.nums = nums;
    	this.temp = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
    	return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	int n = temp.length;
        for(int i = temp.length-1;i >= 0;i--) {
        	int index = ThreadLocalRandom.current().nextInt(0, n);
        	swap(nums, n-1, index);
        	n--;
        }
        return temp;
    }
    
    void swap(int []nums, int a, int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
    
    void print(int []arr) {
    	for(int i : arr) 
    		System.out.print(i + " ");
    	System.out.println();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3};
		Solution obj = new Solution(nums);
		int[] param_1 = obj.shuffle();
		obj.print(param_1);
		int[] param_2 = obj.reset();
		obj.print(param_2);
		int[] param_3 = obj.shuffle();
		obj.print(param_3);
	}
}