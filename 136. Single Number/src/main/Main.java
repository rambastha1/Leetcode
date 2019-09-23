package main;

public class Main {

	public static void main(String[] args) {
		int []nums = {4,1,2,1,2};
		System.out.println(singleNumber(nums));
	}
	
	public static int singleNumber(int[] nums) {
		int num = nums[0];
		for(int i = 1;i < nums.length;i++)
			num ^= nums[i]; 
		return num;
    }
}
