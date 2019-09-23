package main;

/* best one is geeksforgeeks
 * https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
 * https://stackoverflow.com/questions/16605991/number-of-subarrays-divisible-by-k
 */

/* why (Mod[i]*(Mod[i] – 1))/2 number of ways
 * Well to find the answer we need to find to indices i and j such that 
 * sum_till(i-1)%k = sum_till(j)%k . (As we've assumed (arr[i]+....+arr[j])%k = 0)
 * Now if we know that n indices have the same value for sum_till() function, 
 * we can choose any two from those n.( Any one can be i , other can be j) so C(n,2) combinations
 */

class Solution {
	
	//Time 0(N+K) Space 0(K)
    public int subarraysDivByK(int[] A, int K) {
    	if(A == null || A.length == 0 || K == 0)
    		return 0;
    	if(A.length == 1)
    		return A[0]%K==0?1:0;
    	int mod[] = new int[K];
    	//check for k = 1 this question doesn't require it
    	int sum = 0;
    	for(int i = 0;i < A.length;i++) {
    		sum += A[i];
    		// as the sum can be negative, taking modulo twice 
    		mod[((sum%K)+K)%K]++;
    	}
    	
    	int ans = 0;
    	for(int i = 0;i < K;i++) {
    		if(mod[i] > 0)
    			ans += (mod[i]*(mod[i]-1))/2;
    	}
    	ans += mod[0];
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {4,5,0,-2,-3,1};
		int K = 5;
		System.out.println(new Solution().subarraysDivByK(A, K));
	}
}