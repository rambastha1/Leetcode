package main;

class Solution {
	void merge(int []arr1, int []arr2) {
		if(arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0)
			return;
		for(int i = arr2.length-1;i >= 0;i--) {
			int j = arr1.length-2, last = arr1[arr1.length-1];
			while(j >= 0 && arr1[j] > arr2[i]) {
				arr1[j+1] = arr1[j];
				j--;
			}
			if(j != arr1.length-2 || last > arr2[i]) {
				arr1[j+1] = arr2[i];
				arr2[i] = last;
			}
		}
	}
	
	void print(int []arr) {
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		int []arr1 = {1, 5, 9, 10, 15, 20};
		int []arr2 = {2, 3, 8, 13};
		Solution s = new Solution();
		s.print(arr1);
		s.print(arr2);
		s.merge(arr1, arr2);
		s.print(arr1);
		s.print(arr2);
	}
}