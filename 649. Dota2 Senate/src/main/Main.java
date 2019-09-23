package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	public String predictPartyVictory(String senate) {
		char []arr = senate.toCharArray();
		int n = arr.length;
		int r = 0, d = 0;
		for(char c : arr) {
			if (c == 'R') r++;
			else d++;
		}
		// how many of r to skip -> skipr
		int skipr =0, skipd = 0;
		// true for multiple rounds
		while(true) {
			for(int i = 0;i < arr.length;i++) {
				if(arr[i] == 'N')
					continue;
				else if(arr[i] == 'R') {
					if(skipr > 0) {
						arr[i] = 'N';
						r--;
						skipr--;
					} else
						skipd++;
				} else {
					if(skipd > 0) {
						arr[i] = 'N';
						d--;
						skipd--;
					} else skipr++;
				}
			}
			if(r <= 0)
				return "Dire";
			if(d <= 0)
				return "Radiant";
		}
	}
	
	// Using Queue learn this processs
	// the operation plus n means the corresponding senate can keep "alive" to the next round.
    public String predictPartyVictory1(String senate) {
    	if(senate.length() == 1)
    		return senate.charAt(0)=='R'?"Radiant":"Dire";
    	int n = senate.length();
    	Queue<Integer> r = new LinkedList<>(), d = new LinkedList<>();
    	for(int i = 0;i < senate.length();i++) {
    		char c = senate.charAt(i);
    		if(c == 'R')
    			r.offer(i);
    		else
    			d.offer(i);
    	}
    	
    	while(!r.isEmpty() && !d.isEmpty()) {
    		int rindex = r.poll(), dindex = d.poll();
    		if(rindex < dindex)
    			r.offer(rindex + n);
    		else
    			d.offer(dindex + n);
    	}
    	// In the end size of queues can never be equal
    	// one will be empty other not both cannot be empty at same point
    	return r.size() > d.size()?"Radiant":"Dire";
    }
}

public class Main {
	public static void main(String[] args) {
		//String senate = "RRDDD";
		String senate = "RDD";
		System.out.println(new Solution().predictPartyVictory(senate));
	}
}
