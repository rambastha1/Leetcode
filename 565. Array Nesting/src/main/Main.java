package main;

class Solution {
    
	
	
	//Just change the array withour using visited array
	public int arrayNesting(int[] nums) {
    	int maxlen = Integer.MIN_VALUE;
        //boolean []visited = new boolean[nums.length];
    	for(int i = 0;i < nums.length;i++) {
    		int len = 0;
    		/*while(!visited[i]) {
                visited[i] = true;
    			i = nums[i];
    			len++;
    		}*/
    		while(nums[i] != -1) {
    			//swapping the value i will contain dest index earlier nums[i] is marked visited
    			int temp = nums[i];
    			nums[i] = -1;
    			i = temp;    			
    			len++;
    		}
    		maxlen = Math.max(maxlen, len);
    	}
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {5,4,0,3,1,6,2};
		System.out.println(new Solution().arrayNesting(nums));
	}
}
