package main;

class Solution {
	
    public int search(int[] nums, int target) {
		if(nums==null||nums.length==0) return -1;
		int start=0;
		int end = nums.length-1;
		while(start<=end){
		     int mid = start+(end-start)/2;
		     if(nums[mid]==target) return mid;
		     if(nums[mid]<nums[end]){
		         if(nums[mid]<target&&target<=nums[end])
		             start=mid+1;
		         else end=mid-1;
		     }else{
		         if(nums[start]<=target&&target<nums[mid])
		             end=mid-1;
		         else start=mid+1;
		     }
		 }
		 return -1;
    }
    
	 // Other approach could be find min using problem 153
	 // iterate on both sides
    
    /*public int search(int[] nums, int target) {
        int minIdx = findMinIdx(nums);
        if (target == nums[minIdx]) return minIdx;
        int m = nums.length;
        int start = (target <= nums[m - 1]) ? minIdx : 0;
        int end = (target > nums[m - 1]) ? minIdx : m - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (target > nums[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    public int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end -  start) / 2;
            if (nums[mid] > nums[end]) start = mid + 1;
            else end = mid;
        }
    	return start;
    }*/
    
}

public class Main {
	public static void main(String[] args) {
		int nums[] = {4,5,6,7,0,1,2};
		System.out.println(new Solution().search(nums, 7));
	}
}