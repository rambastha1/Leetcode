package main;

import java.util.Arrays;

class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
		int []count = new int[1001];
		for(int i : arr1)
			count[i]++;
		int index = 0;
		for(int i : arr2) {
			while(count[i] > 0) {
				arr1[index++] = i;
				count[i]--;
			}
		}
		for(int i = 0;i <= 1000;i++) {
			while(count[i] > 0) {
				arr1[index++] = i;
				count[i]--;
			}
		}
		return arr1;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
		System.out.println(Arrays.toString(new Solution().relativeSortArray(arr1, arr2)));
	}
}
