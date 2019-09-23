package main;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> res = new ArrayList<>();
    	if(nums == null || nums.length == 0)
    		return res;
    	
    	int ele1 = 0, ele2 = 0, c1 = 0, c2 = 0;
    	int k = (int)Math.floor(nums.length/3);
    	// find two most common elements
    	for(int num : nums) {
    		if(ele1 == num)
    			c1++;
    		else if(ele2 == num)
    			c2++;
    		else if(c1 == 0) {
    			ele1 = num;
    			c1++;
    		} else if(c2 == 0) {
    			ele2 = num;
    			c2++;
    		} else {
    			c1--;
    			c2--;
    		}
    	}
    	
    	c1 = 0;
    	c2 = 0;
    	// check if these two elements satisfy the criteria
    	for(int num : nums) {
    		if(num == ele1)
    			c1++;
    		else if(num == ele2)
    			c2++;
    	}
    	
    	if(c1 > k)
    		res.add(ele1);
    	if(c2 > k)
    		res.add(ele2);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,1,1,3,3,2,2,2};
		System.out.println(new Solution().majorityElement(nums));
	}
}
