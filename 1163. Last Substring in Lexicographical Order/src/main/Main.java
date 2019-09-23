package main;
// https://leetcode.com/problems/last-substring-in-lexicographical-order/discuss/362387/JavaPython-3-Two-short-O(n)-codes-language%3A-2-pointers-and-encoding.
class Solution {
	/* i and j pointes to maximum char
	 * minimum index means larger char
	 */
	public String lastSubstring(String s) {
        int i = 0, j = 1, offset = 0, len = s.length();
        while (i + offset < len && j + offset < len) {
            char c = s.charAt(i + offset), d = s.charAt(j + offset);
            if (c == d) {
                ++offset;
            }else {
                if (c < d)  { i += offset + 1; } // chars in [i, ..., i + offset] <= charAt(i) == charAt(j)
                else { j += offset + 1; } // c > d, chars in [j, ..., j + offset] <= charAt(i) == charAt(j)
                if (i == j) { ++i; } // avoid duplicate start indices. 
                offset = 0; // reset offset to 0.
            }
        }
        return s.substring(Math.min(i, j));
    }
}

public class Main {
	public static void main(String[] args) {
		//String s = "leetcode";
		String s = "zrziy";
		System.out.println(new Solution().lastSubstring(s));
	}
}
