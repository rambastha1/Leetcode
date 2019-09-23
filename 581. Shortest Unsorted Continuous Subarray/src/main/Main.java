package main;

//https://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/

class Solution {
	public int findUnsortedSubarray(int[] nums) {
        int start=0; 
        int end=nums.length-1;
        
        // if curr > next break; note go till length-2 -> <len-1        
        for (start=0; start < nums.length-1;start++){
            if (nums[start]> nums[start+1])
                 break;
        }
        //array is sorted
        if(start== nums.length-1)
            return 0;
        
        //if curr < leftwala elt break;
        for (end=nums.length-1; end > 0;end--){
            if (nums[end]< nums[end-1])
                break;
        }
        
        
        int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        //find min and max in index range of start and end
        for (int i=start+1;i<=end;i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        // u found a number which is smaller than min of identified range; 
        //we need to sort from this index now
        for(int i=0;i<start;i++){
            if(min<nums[i]){
                start=i;
                break;
            }
        }
        // identify if there is any number to right of end & smaller than end index elt
        // note ; vv imp start from tail of array towards end.
          for(int i=nums.length-1; i>end;i--){
            if(nums[i]<max){
                end=i;
                break;
            }
        }
        System.out.println("start: " + start + "\tend: " + end);
        return end-start+1;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		//int []nums = {2, 6, 4, 8, 10, 9, 15};
		//int []nums = {1,2,3,4};
		System.out.println(new Solution().findUnsortedSubarray(nums));
	}
}