package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
    	Set<String> set = new HashSet<>();
    	Map<String, String> lowercase = new HashMap<>(), vowel = new HashMap<>();
    	
    	for(String str : wordlist) {
    		set.add(str);

    		String lower = str.toLowerCase();
    		lowercase.putIfAbsent(lower, str);
    		
    		// lower is passed as argument to maintain that capitalization failed
    		String s = remove_vowel(lower);
    		vowel.putIfAbsent(s, str);
    	}
    	
    	String []res = new String[queries.length];
    	int index = 0;
    	for(String str : queries) {
    		if(set.contains(str))
    			res[index++] = str;
    		else if(lowercase.containsKey(str.toLowerCase()))
    			res[index++] = lowercase.get(str.toLowerCase());
    		else {
    			String s = remove_vowel(str.toLowerCase());
    			if(vowel.containsKey(s)) 
    				res[index++] = vowel.get(s);
    			else
    				res[index++] = "";
    		}
    	}    	
    	print(res);
    	return res;
    }
    
    String remove_vowel(String str) {
    	StringBuilder sb = new StringBuilder();
    	for(char c : str.toCharArray())
    		sb.append(isvowel(c)?'*':c);
    	return sb.toString();
    }
    
    boolean isvowel(char c) {
    	return c=='a' || c=='e' || c == 'i' || c == 'o' || c == 'u';
    }
    
    void print(String []res) {
    	for(String str : res)
    		System.out.print(str + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		/*String [] wordlist = {"KiTe","kite","hare","Hare"};
		String []queries = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};*/
		
		String [] wordlist = {"ae","aa"};
		String []queries = {"UU"};
		
		new Solution().spellchecker(wordlist, queries);
	}
}
