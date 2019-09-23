package main;
// simialr to 1011
class Solution {
	public int minEatingSpeed(int[] piles, int H) {
    	if(piles == null || piles.length == 0 || H == 0)
    		return 0;
    	
    	int min = 1, max = (int)Math.pow(10, 9);
    	while(min < max) {
    		int mid = min + (max-min)/2;
    		int h = 0;
    		for(int pile : piles) {
    			h += (pile%mid == 0)?(pile/mid):(pile/mid)+1;
    		}
    		if(h <= H)
    			max = mid;
    		else
    			min = mid+1;
    	}
    	return max;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []piles = {3,6,7,11}; 
		int H = 8;*/
		
		int []piles = {312884470}; 
		int H = 968709470;
		System.out.println(new Solution().minEatingSpeed(piles, H));
	}
}