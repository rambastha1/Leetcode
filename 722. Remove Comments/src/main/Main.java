package main;
import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/remove-comments/discuss/109197/One-pass-solution-in-Java
class Solution {
	public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();     
        boolean multiline = false;
        
        for (String s : source) {
            for (int i = 0; i < s.length(); i++) {
                if (multiline) {
                    if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        multiline = false;
                        i++;        //skip '/' on next iteration of i
                    }
                }
                else {
                    if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        break;      //ignore remaining characters on line s
                    }
                    else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                        multiline = true;
                        i++;           //skip '*' on next iteration of i
                    }
                    else    sb.append(s.charAt(i));     //not a comment
                }
            }
            if (!multiline && sb.length() > 0) {
                res.add(sb.toString());
                // don't reset at every line.. reset only when its added to res
                sb = new StringBuilder();
            }
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String []source = {"a/*comment", "line", "more_comment*/b"};
		System.out.println(new Solution().removeComments(source));
	}
}
