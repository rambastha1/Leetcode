
package main;

class Solution {
	/*
	 * https://www.youtube.com/watch?v=pq7Xon_VXeU
	 * Time 0(N) Space 0(1)
	 */
    public int trap(int[] height) {
    	int low = 0, high = height.length-1, left_max = 0, right_max = 0;
    	int ans = 0;
    	while(low < high) {
    		if(height[low] < height[high]) {
    			if(height[low] > left_max)
    				left_max = height[low];
    			else
    				ans += left_max - height[low];
    			low++;
    		} else {
    			if(right_max < height[high])
    				right_max = height[high];
    			else
    				ans += right_max - height[high];
    			high--;
    		}
    	}    	
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []height = {0,1,0,2,1,0,1,3,2,1,2,1};
		//int []height = {4,2,3};
		System.out.println(new Solution().trap(height));
	}
}