package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int []nums = {4,3,2,7,8,2,3,1};
		List<Integer> list = findDisappearedNumbers(nums);
		Iterator<Integer> itr = list.iterator();
		while(itr.hasNext())
			System.out.print(itr.next() + " ");
		System.out.println();
	}
	
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		if(nums.length == 0)
			return null;
		
		List<Integer> list = new ArrayList<>();
		
		for(int num : nums) {
			int index = Math.abs(num) - 1;
 			if(nums[index] > 0)
				nums[index] = -nums[index];
		}
		
		for(int i = 0;i < nums.length;i++) {
			if(nums[i] > 0)
				list.add(i+1);
		}
		return list;
	}
}
