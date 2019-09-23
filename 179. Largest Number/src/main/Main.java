package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
	/*
	 * Time 0(NlgN) Space 0(N)
	 */
	public String largestNumber(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return "";
    	String []str = new String[nums.length];
    	for(int i = 0;i < nums.length;i++) {
    		str[i] = String.valueOf(nums[i]);
    	}
    	Arrays.sort(str, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String temp1 = o1+o2;
				String temp2 = o2+o1;
				return temp2.compareTo(temp1);
			}
    		
		});
    	if(Integer.valueOf(str[0]) == 0)
    		return "0";
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0;i < str.length;i++)
    		sb.append(str[i]);
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {0,0};
		System.out.println(new Solution().largestNumber(nums));
	}
}