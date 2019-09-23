package main;

public class Main {

	public static void main(String[] args) {
		int []arr = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(lis(arr));		
	}
	
	public static int lis(int []arr) {
		if(arr.length == 0)
			return 0;
		int sum = 0, maxS = arr[0];
		
		for(int a : arr) {
			sum = sum + a > a?sum+a:a;
			maxS = maxS > sum?maxS:sum;
		}
		return maxS;
	}

}
