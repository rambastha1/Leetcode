package main;

// https://www.geeksforgeeks.org/number-of-ways-to-divide-an-array-into-k-equal-sum-sub-arrays/

class Solution {
	boolean partition(int []A, int k) {
		int sum = 0, n = A.length;
		for(int a: A)
			sum += a;
		if(sum % k != 0) return false;
		sum /= k;
		
		int count = 0, curr = 0;
		for(int i = 0;i < n;i++) {
			curr += A[i];
			if(curr == sum) {
				count++;
				curr = 0;
			}
		}
		return count==k;
	}
	
	int ways(int []A, int k) {
		int n = A.length;
		int []count = new int[n];
		
		int sum = 0;
		for(int a : A)
			sum += a;
		if(sum % k != 0) return 0;
		sum /= k;
		
		int curr = 0;
		for(int i = n-1;i >=0;i--) {
			curr += A[i];
			if(curr == sum)
				count[i] = 1;
		}
		
		for(int i = n-k;i >= 0;i--)
			count[i] += count[i+1];
		
		int ans = 0;
		curr = 0;
		
		for(int i = 0;i < n-k+1;i++) {
			curr += A[i];
			if(curr == sum) {
				ans += count[i+k];
			}
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		/*int []A = {1, 4, 2, 3, 5};
		int k = 3;*/
		
		int []A = {1, -1, 1, -1, 1, -1};
		int k = 2;
		System.out.println(new Solution().partition(A, k));
		System.out.println(new Solution().ways(A, k));
	}
}
