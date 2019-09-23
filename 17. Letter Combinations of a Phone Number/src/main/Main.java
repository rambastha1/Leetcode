// https://www.youtube.com/watch?v=a-sMgZ7HGW0
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
	
	/*
	 * Time Complexity : 0(N4^N) 4 = max digit fpr any number in numpad
	 * Space : 0(N)
	 * It removes ans ("") with length = 0, digits.charAt(remove.length()) = abc, adds abc to ans
	 * removes 'a', adds "def", and concatenate with 'a' -> ad, ae, af
	 * add these three to ans
	 * removes 'b', adds "def", and concatenate with 'b' -> bd, be, bf
	 * removes 'c', adds "def", and concatenate with 'c' -> cd, ce, cf
	 * final ans contains  [ad, ae, af, bd, be, bf, cd, ce, cf]
	 */
    public List<String> letterCombinations(String digits) {
    	LinkedList<String> ans = new LinkedList<String>();
		if(digits.isEmpty()) return ans;
		String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		while(ans.peek().length()!=digits.length()){
			String remove = ans.remove();
			String map = mapping[digits.charAt(remove.length())-'0'];
			System.out.println("remove:" + remove + "\tmap:" + map);
			for(char c: map.toCharArray()){
				ans.addLast(remove+c);
			}
			//System.out.println(ans);
		}
		return ans;
    }
}

public class Main {

	public static void main(String[] args) {
		String digits = "23";
		List<String> res = new Solution().letterCombinations(digits);
		System.out.println(res);
	}
}