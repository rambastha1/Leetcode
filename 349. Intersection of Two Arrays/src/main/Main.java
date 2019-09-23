package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		int []nums1 = {4,9,5};
		//int []nums1 = {1,2,2,1};
		int []nums2 = {9,4,9,8,4};
		//int []nums2 = {2,2};
		int []res = intersection(nums1, nums2);
		for(int i = 0;i < res.length;i++)
			System.out.print(res[i] + " ");
		System.out.println();		
	}
	
	public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        int[] temp = new int[nums1.length];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int left = 0;
        int right = 0;
        int index = 0;
        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] == nums2[right]) {
                if (index == 0 || temp[index-1] != nums1[left]) {
                    temp[index] = nums1[left];
                    index++;
                }
                left++;
                right++;
            }
            else if (nums1[left] < nums2[right]) {
                left++;
            }
            else {
                right++;
            }
        }
        int[] result = new int[index];
        for (int i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }
} 