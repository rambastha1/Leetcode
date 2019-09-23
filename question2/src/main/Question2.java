package main;

import java.util.Scanner;

public class Question2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int []arr = new int[N];
		for(int i = 0;i < N;i++)
			arr[i] = sc.nextInt();
		int n = sc.nextInt();
		int []H = new int[n];
		for(int i = 0;i < H.length;i++)
			H[i] = sc.nextInt();
		CoolSort(arr, H);
		print(arr);
	}

	public static void CoolSort(int []arr, int []H) {
		
		if(arr.length == 0 || arr.length == 1 || H.length == 0)
			return;
		
		int i,j,k, key;
		for(i = 0;i < H.length;i++) {
			int step_size = H[i];
			for(j = step_size;j < arr.length;j++) {
				key = arr[j];
				k = j;
				while(k >= step_size && arr[k - step_size] > key) {
					arr[k] = arr[k - step_size];
					k -= step_size;
				}
				arr[k] = key;
			}
		}
	}
	
	public static void print(int []arr) {
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
}