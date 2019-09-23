package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * We can reverse a number's binary format bitwisely to get its reversed binary representation (RPR). 
 * For this question, 10 bits are enough, as N is always less than 1024. In this case, 
 * RPR(1) = 0b1000000000, RPR(2) = 0b0100000000, RPR(12) = 0b0011000000.
 * 
 * It could be proved that: for any i < j < k, if i + k = j * 2, 
 * then RPR(j) is either less than or larger than both RPR(i) and RPR(k), or in another word, 
 * RPR(j) can not be between RPR(i) and RPR(k).
 * 
 * Prove:
 * Consider these four conditions:
 * 
 * 1)i, k is odd, j is even (e.g. 1+7=4*2). The first bit of RPR(i) and RPR(k) is 1, 
 * and the first bit of RPR(j) is 0, so RPR(j) is smaller than both RPR(i) and RPR(k).
 * 
 * 2)i, k is even, j is odd (e.g. 2+4=3*2). The first bit of RPR(i) and RPR(k) is 0, 
 * and the first bit of RPR(j) is 1, so RPR(j) is larger than both RPR(i) and RPR(k).
 * 
 * 3)i, k is odd, j is odd (e.g. 1+5=3*2). Define i1 = (i-1)/2, k1=(k-1)/2, j1 = (j-1)/2, 
 * then we have i1<j1<k1, and i1+k1=j1*2. i1, j1, k1 could be treated as new i, j, k.
 * 
 * 4)i, k is even, j is even (e.g. 2+6=4*2). Define i1=i/2, k1=k/2, j1=j/2, 
 * then we have i1<j1<k1, and i1+k1=j1*2. i1, j1, k1 could be treated as new i, j, k.
 * 
 * For case #3 and #4, it is reduced to the original proposition, and must be finally 
 * reduced to case #1 or #2, because i < j < k.
 * Based on this, the algorithm is simple: sort all the numbers for 1 to N by its RPR. 
 * The final array is a result.
 * 
 * Time complexity is O(N log N) and space complexity is O(N).
 */

class Solution {
	//https://leetcode.com/problems/beautiful-array/discuss/186679/Odd-%2B-Even-Pattern-O(N)
	public int[] beautifulArray(int N) {
		List<Integer> res = new ArrayList<>();
		res.add(1);
		while(res.size() < N) {
			List<Integer> temp = new ArrayList<>();
			for(int i : res) 
				if(i*2-1 <= N) temp.add(2*i-1);
			for(int i : res) 
				if(i*2 <= N) temp.add(2*i);
			res = temp;
		}
		
		int []ans = new int[res.size()];
		for(int i = 0;i < res.size();i++)
			ans[i] = res.get(i);
		return ans;
	}
	
	//Reverse Binary Method Understand
	/*int reverse(int N) {
		int res = 0;
		for(int i = 0;i <= 9;i++)
			res += ((N>>1) & 0x1) << (9-i);
		return res;
	}
	
    public int[] beautifulArray(int N) {
    	Integer []res = new Integer[N];
    	for(int i = 0;i < N;i++) {
    		res[i] = i+1;
    	}
    	
    	Arrays.sort(res, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return reverse(o1) - reverse(o2);
			}
    		
		});
    	int []ans = new int[res.length];
    	for(int i = 0;i < ans.length;i++)
    		ans[i] = res[i];
    	return ans;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int N = 5;
		int []res = new Solution().beautifulArray(N);
		for(int i = 0;i < res.length;i++)
			System.out.print(res[i] + " ");
		System.out.println();
	}	
}