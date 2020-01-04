package main;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* https://www.geeksforgeeks.org/introduction-to-combinatorial-game-theory/
 * https://www.geeksforgeeks.org/combinatorial-game-theory-set-3-grundy-numbersnimbers-and-mex/
 * Read all examples carefully
 * 
 * https://www.geeksforgeeks.org/combinatorial-game-theory-set-4-sprague-grundy-theorem/
 * 
 *
 *  Sprague Grundy is used to predict which player wins the impartial game beforehand
 *  It can be used to determine optimal step forwerd
 */ 

class Solution {
	// DP ways to calculate grundy numbers Top Down with Memoization
	int getMex(Set<Integer> set) {
		int mex = 0;
		while(set.contains(mex))
			mex++;
		return mex;
	}
	
	// Take any number between 1 - N Example 1
	int getGrundy(int n, int []grundy) {
		if(n == 0)
			return 0;
		if(grundy[n] != -1)
			return grundy[n];
		grundy[0] = 0;
		Set<Integer> set = new HashSet<>();
		for(int i = 0;i <= n-1;i++)
			set.add(getGrundy(i, grundy));
		grundy[n] = getMex(set); 
		return grundy[n];
	}
	
	// Take only 1, 2, 3 Example 2
	int getGrundy1(int n, int []grundy) {
		grundy[0] = 0;
		grundy[1] = 1;
		grundy[2] = 2;
		grundy[3] = 3;
		
		if(grundy[n] != -1)
			return grundy[n];
		Set<Integer> set = new HashSet<>();
		for(int i = 1;i <= 3;i++)
			set.add(getGrundy1(n-i, grundy));
		grundy[n] = getMex(set); 
		return grundy[n];
	}
	
	// Divide by 2,3 or 6 Example 3
	int getGrundy2(int n, int []grundy) {
		if (n == 0)
			return 0;
		if(grundy[n] != -1)
			return grundy[n];
		Set<Integer> set = new HashSet<>();
		set.add(getGrundy2(n/2, grundy));
		set.add(getGrundy2(n/3, grundy));
		set.add(getGrundy2(n/6, grundy));
		grundy[n] = getMex(set); 
		return grundy[n];
	}
	
	int winner(int []piles, int []grundy, int player) {
		for(int i = 0;i <= piles.length-1;i++)
			getGrundy1(piles[i], grundy);
		int xor = grundy[piles[0]];
		for(int i = 1;i < piles.length;i++) {
			xor ^= grundy[piles[i]];
		}		
		return (xor!=0)?player:player^1;
	}
	
}

public class Main {
	public static void main(String[] args) {
		int n = 10;
		Solution s = new Solution();
		int []grundy = new int[n+1];
		
		/*Arrays.fill(grundy, -1);
		System.out.println(s.getGrundy(n, grundy));
		Arrays.fill(grundy, -1);
		System.out.println(s.getGrundy1(n, grundy));*/
		Arrays.fill(grundy, -1);
		System.out.println(s.getGrundy2(n, grundy));
		
		/*Arrays.fill(grundy, -1);
		//int []piles = {3,4,5};
		int []piles = {1,4,5};
		System.out.println(s.winner(piles, grundy, 0));*/		
	}
}