package main;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
    	
    	if(deck == null || deck.length == 0)
    		return new int[0];
    	
    	Deque<Integer> d = new ArrayDeque<>();
    	int []res = new int[deck.length];
    	for(int i = 0;i < deck.length;i++)
    		d.add(i);
    	Arrays.sort(deck);
    	for(int card : deck) {
    		res[d.pollFirst()] = card;
    		if(!d.isEmpty())
    			d.add(d.pollFirst());
    	}    	
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []deck = {17,13,11,2,3,5,7};
		int []res = new Solution().deckRevealedIncreasing(deck);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}