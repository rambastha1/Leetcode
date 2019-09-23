package main;

class Solution {
    public int maxArea(int[] height) {
    	if(height.length <= 1)
    		return 0;
    	
    	int l = 0, r = height.length-1;
    	int max = 0, area = 0;
    	while(l < r) {
    		area = Math.min(height[l], height[r])*(r-l);
    		max = Math.max(max, area);
    		if(height[l] < height[r])
    			l++;
    		else
    			r--;
    	}
    	return max;
    }
}

public class Main {

	public static void main(String[] args) {
		int []height = {1,8,6,2,5,4,8,3,7};
		System.out.println(new Solution().maxArea(height));
	}
}