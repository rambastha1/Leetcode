package main;

//https://www.youtube.com/watch?v=cOBcekmPWaM

class Solution {
	/*
	 * Counting sort takes 0(N) time and space is 0(max-min)
	 * create a count array of size max-min+1 and store frequency of each element in it
	 * now loop through count array, get actual element using min + i as (a[i]-min) is used
	 * to decide index of count array. while count > 0 keep insert actual data in the list 
	 */
	public int[] sort(int []a) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i = 0;i < a.length;i++) {
			min = Math.min(min, a[i]);
			max = Math.max(max, a[i]);
		}
		
		int []count = new int[max-min+1];
		for(int i = 0;i < a.length;i++) {
			count[a[i]-min]++;
		}
		
		/* Worst time could be 0(N^2)
		 * It is for this reason count array is modified to get actual index
		 * and putput array is created
		 * Here we are iterating through count array get num and storing in actual array
		 * 
		 * Instead modify count array to get actual index
		 * loop through actual array get index and store in output array to keep to 0(N)
		 */
		
		//0(N^2 method)
		/*int index = 0;
		for(int i = 0;i < count.length;i++) {
			int num = min + i;
			while(count[i] > 0) {
				a[index++] = num;
				count[i]--;
			}
		}*/
		for(int i = 1;i < count.length;i++)
			count[i] += count[i-1];
		
		int []output = new int[a.length];
		for(int i = 0;i < a.length;i++) {
			//-1 is used because last index of modified count array can go to max element
			//in that case output[max] will give null pointer exception
			output[count[a[i] - min]-1] = a[i];
			count[a[i]-min]--;
		}
		return output;
	}
}

public class Main {
	public static void main(String[] args) {
		//int []a = {1, 4, 1, 2, 7, 5, 2};
		int []a = {-5, -10, 0, -3, 8, 5, -1, 10};
		a = new Solution().sort(a);
		for(int i : a)
			System.out.print(i + " ");
		System.out.println();
	}
}