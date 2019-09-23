package main;

class Solution {
	public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int satisfy = 0, max = 0;
        for (int i = 0, grump = 0; i < grumpy.length; ++i) {
            if (grumpy[i] == 0) 
            	satisfy += customers[i];
            else 
            	grump += customers[i];
            /*
             * grumpy[i-X] will be 0 or 1
             * if i is outside window reduce number of unsatisfied by cust[index] only if index = 1
             * if index was 0, there was no addition at that time 
             */
            
            if (i >= X) {
                grump -= grumpy[i - X] * customers[i - X];
            }
            max = Math.max(grump, max);
        }
        //ans is customers when grumpy[i] == 0 + maximum of sliding window
        return satisfy + max;        
    }
}

public class Main {
	public static void main(String[] args) {
		int []customers = {1,0,1,2,1,1,7,5};
		int []grumpy = {0,1,0,1,0,1,0,1};
		int X = 3;
		System.out.println(new Solution().maxSatisfied(customers, grumpy, X));
	}
}