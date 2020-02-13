package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* arr[i]--; means we are using the i-th tile ('A'+i) as the current tile because there are still remaining ones.
sum++; means with these current tiles (not necessarily all the tiles given) we already have a valid combination.
sum += dfs(arr); means if we still want to add more tiles to the existing combination, we simply do recursion with the tiles left;
arr[i]++; is backtracking because we have finished exploring the possibilities of using the i-th tile and need to restore it 
and continue to explore other possibilities.
 * 
 */
class Solution {
	
	// faster way
	public int numTilePossibilities(String tiles) {
		int []count = new int[26];
		for(char c : tiles.toCharArray())
			count[c-'A']++;
		return dfs(count);
	}
	
	private int dfs(int []count) {
		int sum = 0;
		for(int i = 0;i < 26;i++) {
			if(count[i] == 0)
				continue;
			sum++;
			count[i]--;
			sum += dfs(count);
			count[i]++;
		}
		return sum;
	}
	
	public int numTilePossibilities1(String tiles) {
    	Map<Character, Integer> map = new HashMap<>();
    	for(char c : tiles.toCharArray())
    		map.put(c, map.getOrDefault(c, 0) + 1);
    	Set<String> set = new HashSet<>();
    	dfs1(set, map, new StringBuilder());
    	return set.size();
    }
    
    private void dfs1(Set<String> set, Map<Character, Integer> map, StringBuilder sb) {
    	
    	if(sb.length() > 0 && !set.contains(sb.toString()))
    		set.add(sb.toString());
    	for(Character c : map.keySet()) {
    		if(map.get(c) == 0)
    			continue;
    		map.put(c, map.get(c)-1);
    		sb.append(c);
    		dfs1(set, map, sb);
    		map.put(c, map.get(c)+1);
    		sb.setLength(sb.length()-1);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		String tiles = "AAB";
		System.out.println(new Solution().numTilePossibilities(tiles));
	}
}
