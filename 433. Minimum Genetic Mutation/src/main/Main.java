package main;

import java.util.HashSet;
import java.util.Set;

class Solution {
	
	public int minMutation(String start, String end, String[] bank) {
    	if(bank == null || bank.length == 0)
    		return -1;
    	
    	Set<String> valid = new HashSet<>();
    	for(String str : bank)
    		valid.add(str);
    	if(!valid.contains(end))
    		return -1;
    	valid.add(start);
    	
    	char []dir = {'A', 'C', 'G', 'T'};
    	Set<String> begin = new HashSet<>(), last = new HashSet<>();
    	begin.add(start);
    	last.add(end);
    	int level = 0;
    	
    	while(!begin.isEmpty() && !last.isEmpty()) {
    		Set<String> temp = new HashSet<>();
    		for(String str : begin) {
    			
    			char []arr = str.toCharArray();
    			for(int i = 0;i < arr.length;i++) {
    				char old = arr[i];
    				for(int j = 0;j < dir.length;j++) {
    					if(dir[j] == old)
    						continue;
    					
    					arr[i] = dir[j];
    					String dest = new String(arr);
    					/*
    					 * can't put this outside loop because valid strings are provided not 
    					 * invalid ones. Can't check outside..have to check here
    					 * it might not get added to temp
    					 */
    					if(last.contains(dest))
    						return level+1;
    					if(valid.contains(dest)) {
    						temp.add(dest);
	    					// works as visited this will never be visited again 
	    					valid.remove(dest);
    					}
    					arr[i] = old;
    				}
    			}
    		}
    		level++;
    		// direct swap or swap based on size
    		begin = last;
    		last = temp;
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		String start = "AAAAACCC", end = "AACCCCCC";
		String []bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
		System.out.println(new Solution().minMutation(start, end, bank));
	}
}
