package main;

import java.util.Arrays;
/* ( -> +1 ) -> -1
 * distribute brackets alternately
 */
class Solution {
	// time 0(n)
    public int[] maxDepthAfterSplit(String seq) {
    	int A = 0, B = 0, n = seq.length();
    	int []res = new int[n];
    	for(int i = 0;i < n;i++) {
    		char c = seq.charAt(i);
    		if(c == '(') {
    			if(A <= B) 
    				A++;
    			else {
    				B++;
    				res[i] = 1;
    			}
    		} else {
    			if(A >= B)
    				A--;
    			else {
    				B--;
    				res[i] = 1;
    			}
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String seq = "()(())()";
		System.out.println(Arrays.toString(new Solution().maxDepthAfterSplit(seq)));
	}
}
