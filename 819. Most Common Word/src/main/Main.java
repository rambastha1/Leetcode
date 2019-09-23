package main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
	
	// keep adding alphabet, once its not and sb.length() > 0, means its a word put in map and check frequency
	public String mostCommonWord(String paragraph, String[] banned) {
		paragraph += ".";
		HashMap<String, Integer> map = new HashMap<>();
		HashSet<String> banSet = new HashSet<>();

		for (String ban : banned)
			banSet.add(ban.toLowerCase());

		String res = "";
		int max = 0;
		StringBuilder sb = new StringBuilder();
		
		for (char c : paragraph.toCharArray()) {
			if (Character.isLetter(c)) {
				sb.append(Character.toLowerCase(c));
			} else {
				if (sb.length() > 0 && !banSet.contains(sb.toString())) {
					//System.out.println("find");
					String word = sb.toString();                   
					map.put(word, map.getOrDefault(word, 0) + 1);

					if (map.get(word) > max) {
						res = word;
						max = map.get(word);
					}
				}        
				//System.out.println(banSet.size());
				// new object as previous one is used for previous word
				sb = new StringBuilder();
			}
		}
		return res;
	}
	
	
    public String mostCommonWord1(String paragraph, String[] banned) {
    	Set<String> ban = new HashSet<>();
    	for(String str : banned) {
    		ban.add(str.toLowerCase());
    	}
    	
    	// string -> count
    	Map<String, Integer> map = new HashMap<>();
    	String []arr = paragraph.toLowerCase().split("\\W+");
    	for(int i = 0;i < arr.length;i++) {
    		//String str = arr[i].toLowerCase();
    		if(!ban.contains(arr[i]))
    			map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
    	}
    	
    	int max = 0;
    	String ans = "";
    	
    	for(String str : map.keySet()) {
    		if(map.get(str) > max) {
    			max = map.get(str);
    			ans = str;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String []banned = {"hit"};
		System.out.println(new Solution().mostCommonWord(paragraph, banned));
	}
}
