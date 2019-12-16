package main;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region X contains another region Y then X is bigger than Y. Also by definition a region X contains itself.

Given two regions region1, region2, find out the smallest region that contains both of them.

If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.

It's guaranteed the smallest region exists.

 

Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"
 

Constraints:

2 <= regions.length <= 10^4
region1 != region2
All strings consist of English letters and spaces with at most 20 letters.
 * 
 */

class Solution {
	// Lowest Common Ancestor
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
    	Map<String, String> parent = new HashMap<>();
    	for(List<String> list : regions) {
    		for(int i = 1;i < list.size();i++) {
    			parent.put(list.get(i), list.get(0));
    		}
    	}
    	Set<String> lca = new HashSet<>();
    	
    	while(region1 != null) {
    		lca.add(region1);
    		region1 = parent.get(region1);
    	}
    	
    	while(region2 != null) {
    		if(lca.contains(region2))
    			return region2;
    		region2 = parent.get(region2);
    	}
    	return null;
    }
}

public class Main {
	public static void main(String[] args) {
		
		List<List<String>> regions = Arrays.asList(Arrays.asList("Earth", "North America", "South America"),
				Arrays.asList("North America","United States","Canada"), Arrays.asList("United States","New York","Boston"),
				Arrays.asList("Canada","Ontario","Quebec"), Arrays.asList("South America","Brazil"));
		String region1 = "Quebec", region2 = "Canada";
		System.out.println(new Solution().findSmallestRegion(regions, region1, region2));
	}
}