package main;

import java.util.Random;

// Proof in NPTEL video 

class Solution {
	void reservoir(int []stream, int k) {
		int []reservoir = new int[k];
		for(int i = 0;i < k;i++)
			reservoir[i] = stream[i];
		
		Random random = new Random();
		int i = k;
		for(;i < stream.length;i++) {
			int j = random.nextInt(i+1);
			if(j < k)
				reservoir[j] = stream[i];
		}
		print(reservoir);
	}
	
	void print(int []reservoir) {
		for(int i : reservoir) 
			System.out.print(i + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		int stream[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};  
        int k = 5; 
        new Solution().reservoir(stream, k); 
	}
}