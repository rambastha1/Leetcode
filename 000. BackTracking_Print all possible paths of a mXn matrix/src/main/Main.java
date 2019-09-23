package main;

public class Main {

	public static void main(String[] args) {
		int arr[][] = { {1 , 2 , 3} , 
			       {4 , 5 , 6}} ;
		printpath(arr, 1, 2);
	}
	
	public static void printpath(int [][]arr, int m, int n) {
		String str = "";
		pathUtil(arr, m, n, 0, 0, str);
	}
	
	public static void pathUtil(int [][]arr, int m,int n, int i, int j,String str) {
		if(i > m || j > n)
			return;
		
		if(i == m && j == n) {
			str += Integer.toString(arr[i][j]) + " ";
			System.out.println(str);
			return;
		}
		str += Integer.toString(arr[i][j]) + " ";
		pathUtil(arr, m, n, i+1, j, str);
		pathUtil(arr, m, n, i, j+1, str);
	}
}