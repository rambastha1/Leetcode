package main;

class Solution {
	
	public boolean search(int[] nums, int target) {
        if(nums == null){
            return false;
        }
        return search(nums, 0, nums.length - 1, target);
    }
    public boolean search(int[] nums, int left, int right, int target){
        if(left > right){
            return false;
        }
        int mid = (left + right) / 2;
        if(nums[mid] == target){
            return true;
        }
        if(nums[left] < nums[right]){
            // unrotated
            if(nums[mid] < target){
                return search(nums, mid + 1, right, target);
            }else{
                return search(nums, left, mid - 1, target);
            }
        }else{
            if(search(nums, mid + 1, right, target)){
                return true;
            }
            if(search(nums, left, mid - 1, target)){
                return true;
            }
        }
        return false;
    }
	
    public boolean search1(int[] nums, int target) {
    	if(nums == null || nums.length == 0)
    		return false;
    	if(nums.length == 1)
    		return nums[0] == target;
    	int l = 0, r = nums.length-1;
    	while(l < r) {
    		int m = l + (r-l)/2;
    		if(nums[m] == target)
    			return true;
    		if(nums[l] <= target && target < nums[m])
    			r = m;
    		else if(nums[l] < nums[m])
    			l = m + 1;
    		else if(nums[m] < target && target <= nums[r])
    			l = m+1;
    		else if(nums[m] < nums[r])
    			r = m;
    		else if(nums[r] < target)
    			r--;
    		else
    			l++;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {2,5,6,0,0,1,2};
		int []nums = {1,3,1,1,1};
		int target = 3;
		System.out.println(new Solution().search(nums, target));
	}
}
