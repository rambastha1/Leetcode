package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	// 0(N*26 + 26^2 * lg26)
    public String rankTeams(String[] votes) {
    	int m = votes.length, n = votes[0].length();
    	Map<Character, int[]> map = new HashMap<>();
    	for(String vote : votes) {
    		for(int i = 0;i < n;i++) {
    			char c = vote.charAt(i);
    			if(!map.containsKey(c))
    				map.put(c, new int[n]);
    			map.get(c)[i]++;
    		}
    	}
    	
    	List<Character> list = new ArrayList<>(map.keySet());
    	Collections.sort(list, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				for(int i = 0;i < n;i++) {
					if(map.get(o1)[i] != map.get(o2)[i])
						return map.get(o2)[i] - map.get(o1)[i];
				}
				return o1 - o2;
			}
		});
    	
    	StringBuilder sb = new StringBuilder();
    	for(Character c : list)
    		sb.append(c);
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String []votes = {"WXYZ","XYZW"};
		System.out.println(new Solution().rankTeams(votes));
	}
}
