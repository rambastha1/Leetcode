package main;

class Solution {
    public int maxChunksToSorted(int[] arr) {
    	if(arr.length == 1)
    		return 1;
    	
    	int ans = 0, max = 0;
    	for(int i = 0;i < arr.length;i++) {
    		max = Math.max(max, arr[i]);
    		//This gives maximum subarray for which chunk should be 1 or it is decreasing subarray
    		if(max == i)
    			ans++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {2,0,1};
		System.out.println(new Solution().maxChunksToSorted(arr));
	}
}