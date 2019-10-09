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
	String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;
        dfs(digits, "", 0);
        return res;
    }
    
    private void dfs(String d, String prefix, int index) {
        if (index == d.length()) {
            res.add(prefix);
            return;
        }
        String curr = map[d.charAt(index) - '0'];
        for (int i = 0; i < curr.length(); i++) {
            dfs(d, prefix + curr.charAt(i), index + 1);
        }
    }
}

public class Main {

	public static void main(String[] args) {
		String digits = "23";
		List<String> res = new Solution().letterCombinations(digits);
		System.out.println(res);
	}
}