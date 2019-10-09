package main;

import java.util.HashMap;
import java.util.Map;

/* An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
 * a) it                      --> it    (no abbreviation)
 * 
 *      1
 * b) d|o|g                   --> d1g
 * 
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. 
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 * Example: 
 * Given dictionary = [ "deer", "door", "cake", "card" ]
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 */

class ValidWordAbbr {
	//abbr -> word
	Map<String, String> map;
	public ValidWordAbbr(String[] dictionary) {
		map = new HashMap<>();
		for(String word : dictionary) {
			String abbr = toAbbr(word);
			if(!map.containsKey(abbr)) {
				map.put(abbr, word);
			} else {
				if(!map.get(abbr).equals(word))
					map.put(abbr, "");
			}
		}
	}
	
	 public boolean isUnique(String word) {
		 String abbr = toAbbr(word);
		 if(!map.containsKey(abbr) || map.get(abbr).equals(word))
			 return true;
		 return false;
	 }
	 
	 private String toAbbr(String s) {
		 if(s == null || s.length() <= 2)
			 return s;
		 int n = s.length();
		 String res = s.charAt(0) + String.valueOf(n-2) + s.charAt(n-1);
		 return res;
	 }
}

public class Main {
	public static void main(String[] args) {
		String []dictionary = {"deer", "door", "cake", "card"};
		ValidWordAbbr v = new ValidWordAbbr(dictionary);
		System.out.println(v.isUnique("dear"));
		System.out.println(v.isUnique("cart"));
		System.out.println(v.isUnique("cane"));
		System.out.println(v.isUnique("make"));
	}
}
