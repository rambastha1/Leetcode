package main;


/* Sliding window we keep track of number of flips. flip ith element means flip i-(i+k-1) elements
 * we need to flip when A[i] = 0 and flip at ith index = 0 and 
 * when A[i] = 1 and flip at ith index = 1(flip black first to 0 then 1)
 * this means A[i] is 1 and we flipped it, thus flipped it back meaning 0 flip
 * flip == A[i] will satisfy both the equations
 * 
 * if flip from last iteration is same as isFlipped[i - k], it means we did even number of flip in between, 
 * which would also make the number at index i APPEAR unaffected.
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/discuss/238609/JavaC%2B%2BPython-One-Pass-and-O(1)-Space
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/discuss/238538/Python-O(n)-using-queue-and-how-to-get-rid-of-the-queue
 */
class Solution {
	
	/* Similar idea to flip array but original array is modified to adjust it.
	 * If cur is even and A[i] is 0, we need to flip.
	 * If cur is odd and A[i] is 1, we need to flip.
	 * If we want to flip A[i], we add 2 to it.
The flipped 0 is 2 and flipped 1 is 3 now.
	 */
	
	// only if modifying original array is allowed
	public int minKBitFlips(int[] A, int K) {
		int n = A.length;
		int flip = 0, ans = 0;
		for(int i = 0;i < n;i++) {
			if(i-K >= 0 && A[i-K] > 1) {
				flip--;
				A[i-K] -= 2;
			}
			// since adding and subtracting 2, A[i] == flip won't hold
			if(flip % 2 == A[i]) {
				if(i+K > n)
					return -1;
				A[i] += 2;
				flip++;
				ans++;
			}
		}
		return ans;
	}
	
    public int minKBitFlips1(int[] A, int K) {
    	int n = A.length;
    	// flip[i] = 1 if we flip K consecutive bits A[i] A[i+1] ..... A[i+K-1].
    	int []flip = new int[n];
    	int ans = 0, isflipped = 0;
    	
    	for(int i = 0;i < n;i++) {
    		// Find flips were odd or even
// we compare the flipped status of current index's left neighbor with i - k to determine if current index i appears flipped or not.
    		if(i >= K)
    			isflipped ^= flip[i-K];
    		if(isflipped == A[i]) {
    			if(i+K > n)
    				return -1;
    			flip[i] = 1;
    			isflipped ^= 1;
    			ans++;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {0,0,0,1,0,1,1,0};
		int K = 3;
		System.out.println(new Solution().minKBitFlips1(A, K));
		System.out.println(new Solution().minKBitFlips(A, K));
	}
}
