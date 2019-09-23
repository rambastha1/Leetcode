package main;

import java.util.LinkedList;
import java.util.List;

class Solution {
	public List<String> ambiguousCoordinates(String S) {
        S = S.substring(1, S.length() - 1);
        List<String> result = new LinkedList<>();
        for (int i = 1; i < S.length(); i++) {
            List<String> left = allowed(S.substring(0, i));
            List<String> right = allowed(S.substring(i));
            for (String ls : left) {
                for (String rs : right) {
                    result.add("(" + ls + ", " + rs + ")");
                }
            }
        }
        return result;
    }
    private List<String> allowed(String s) {
        int l = s.length();
        char[] cs = s.toCharArray();
        List<String> result = new LinkedList<>();
        if (cs[0] == '0' && cs[l - 1] == '0') { // "0xxxx0" Invalid unless a single "0"
            if (l == 1) {
                result.add("0");
            }
            return result;
        }
        if (cs[0] == '0') { // "0xxxxx" The only valid result is "0.xxxxx"
            result.add("0." + s.substring(1));
            return result;
        }
        if (cs[l - 1] == '0') { // "xxxxx0" The only valid result is itself
            result.add(s);
            return result;
        }
        result.add(s); // Add itself
        for (int i = 1; i < l; i++) { // "xxxx" -> "x.xxx", "xx.xx", "xxx.x"
            result.add(s.substring(0, i) + '.' + s.substring(i));
        }
        return result;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "(123)";
		System.out.println(new Solution().ambiguousCoordinates(S));
	}
}
