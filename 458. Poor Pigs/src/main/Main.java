package main;

// https://leetcode.com/problems/poor-pigs/discuss/94266/Another-explanation-and-solution

class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
    	int pigs = 0;
    	while(Math.pow(minutesToTest/minutesToDie + 1, pigs) < buckets)
    		pigs++;
    	return pigs;
    }
}
public class Main {
	public static void main(String[] args) {
		int buckets = 1000, minutesToDie = 15*60, minutesToTest = 60*60;
		System.out.println(new Solution().poorPigs(buckets, minutesToDie, minutesToTest));
	}
}
