package main;

public class Main {

	public static void main(String[] args) {
		System.out.println(check_perfect(10));
		System.out.println(issquare(10));
	}
	
	public static boolean issquare(int num) {
		double sqrt = Math.sqrt(num);
		int a = (int)sqrt;
		return (a==sqrt);
	}
	
	//Sum of first N odd numbers is N^2.
	public static boolean check_perfect(int num) {
		
		for(int i = 1, sum = 0;sum < num;i += 2) {
			sum += i;
			if(sum == num)
				return true;
		}
		return false;
	}
}