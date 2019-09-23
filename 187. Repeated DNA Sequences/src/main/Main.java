package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/repeated-dna-sequences/discuss/53867/Clean-Java-solution-(hashmap-%2B-bits-manipulation)

class Solution {
	
	// using two sets one for first matching and other for 2 Time 0(N) space 0(N)
	public List<String> findRepeatedDnaSequences(String s) {
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }
	
	// Using hashmap Time 0(N) space 0(N)
    public List<String> findRepeatedDnaSequences1(String s) {
    	List<String> res = new ArrayList<>();
    	if(s == null || s.length() < 10)
    		return res;
    	Map<String, Integer> map = new HashMap<>();
    	
    	int start = 0, end = 10;
    	while(end <= s.length()) {
    		String str = s.substring(start, end);
    		map.put(str, map.getOrDefault(str, 0)+1);
    		end++;
    		start++;
    	}
    	
    	for(String str : map.keySet()) {
    		if(map.get(str) > 1)
    			res.add(str);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		String s = "AAAAAAAAAAA";
		System.out.println(new Solution().findRepeatedDnaSequences(s));
	}
}
