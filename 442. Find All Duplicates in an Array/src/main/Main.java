package main;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
    	List<Integer> res = new ArrayList<>();
    	if(nums == null || nums.length <= 1)
    		return res;
    	int n = nums.length;
    	for(int i = 0;i < nums.length;i++) {
    		int index = Math.abs(nums[i])-1;
    		if(nums[index] < 0)
    			res.add(Math.abs(index+1));
    		nums[index] = -nums[index];
    	}    	 
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {4,3,2,7,8,2,3,1};
		int []nums = {10,2,5,10,9,1,1,4,3,7};
		System.out.println(new Solution().findDuplicates(nums));
	}
}