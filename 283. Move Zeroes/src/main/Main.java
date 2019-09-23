package main;

public class Main {

	public static void main(String[] args) {

		int []nums = {0,1,0,3,12};
		//int []nums = {1,0,1};
		//int []nums = {1,1,0};
		//int []nums = {1,0};
		print(nums);
		moveZeroes(nums);
		print(nums);
	}
	
	public static void print(int []nums) {
		for(int i : nums)
			System.out.print(i + " ");
		System.out.println();
	}
	
	public static void moveZeroes(int[] nums) {
        
		if(nums.length == 0 || nums.length == 1)
			return;
		
		int non_zero = 0, curr = 0;
		for(non_zero = 0,curr = 0;curr < nums.length;curr++) {
			if(nums[curr] != 0)
				swap(nums, non_zero++, curr);
		}
    }
	
	public static void swap(int []nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

}