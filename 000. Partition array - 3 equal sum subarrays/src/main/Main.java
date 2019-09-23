package main;

// https://www.geeksforgeeks.org/split-array-three-equal-sum-subarrays/
//https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-an-array-into-three-contiguous-parts-having-equal-sum/

class Solution {
	
	// Get indices
	int [] partition(int []A) {
		int []res = {-1,-1};
		int sum = 0;
		for(int a : A)
			sum += a;
		if(sum%3 != 0) return res;
		sum /= 3;
		int n = A.length, ind1 = -1, ind2 = -1, curr = 0;
		
		for(int i = 0;i < n;i++) {
			curr += A[i];
			if(curr == sum && ind1 == -1)
				ind1 = i;
			else if(curr == 2*sum && ind2 == -1) {
				ind2 = i;
			}
			
			if(ind1 != -1 && ind2 != -1) {
				res[0] = ind1;
				res[1] = ind2;
				return res;
			}
		}
		return res;
	}
	
	//Count number of ways
	int partition(int []A, int k) {
		int n = A.length;
		int []count = new int[n];
		int sum = 0;
		for(int a : A)
			sum += a;
		
		if(sum%3!=0) return 0;
		sum /= 3;
		
		int curr = 0;
		for(int i = n-1;i >= 0;i--) {
			curr += A[i];
			if(curr == sum)
				count[i] = 1;
		}
		// Understand this part
		for(int i = n-2;i >= 0;i--) {
			count[i] += count[i+1];
		}
		
		int ans = 0;
		curr = 0;
		for(int i = 0;i < n-1;i++) {
			curr += A[i];
			if(curr == sum)
				ans += count[i+2];
		}
		
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,3,4,0,4};
		int []res = new Solution().partition(A);
		System.out.println(new Solution().partition(A, 3));
		System.out.println(res[0] + " " + res[1]);
	}
}
