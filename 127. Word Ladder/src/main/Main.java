package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	Set<String> valid = new HashSet<>();
    	for(String str : wordList)
    		valid.add(str);
    	if(!valid.contains(endWord))
    		return 0;
    	
    	Set<String> begin = new HashSet<>(), end = new HashSet<>();
    	begin.add(beginWord);
    	end.add(endWord);
    	int len = 1;
    	char []dir = new char[26];
    	for(int i = 0;i < 26;i++) {
    		dir[i] = (char)('a'+i);
    	}
    	
    	while(!begin.isEmpty() && !end.isEmpty()) {
    		Set<String> temp = new HashSet<>();
    		for(String str : begin) {
    			char []arr = str.toCharArray();
    			for(int i = 0;i < arr.length;i++) {
    				char old = arr[i];
    				for(int j = 0;j < 26;j++) {
    					char c = (char)('a' + j);
    					if(c == old)
    						continue;
    					arr[i] = c;
    					String dest = String.valueOf(arr);
    					if(end.contains(dest))
        					return len+1;
        				if(valid.contains(dest)) {
        					temp.add(dest);
        					valid.remove(dest);
        				}
    				}
    				arr[i] = old;
    			}
    		}
    		len++;
    		begin = end;
    		end = temp;
    	}
    	return 0;
    }
}

public class Main {
	public static void main(String[] args) {
		String beginWord = "hit", endWord = "cog";
		List<String>wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		System.out.println(new Solution().ladderLength(beginWord, endWord, wordList));
	}
}
