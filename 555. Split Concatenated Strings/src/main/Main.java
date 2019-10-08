package main;

/* Given a list of strings, you could concatenate these strings together into a loop, 
 * where for each string you could choose to reverse it or not. Among all the possible loops, 
 * you need to find the lexicographically biggest string after cutting the loop, which will 
 * make the looped string into a regular one.
 * 
 * Specifically, to find the lexicographically biggest string, you need to experience two phases:
 * 
 * Concatenate all the strings into a loop, where you can reverse some strings or not 
 * and connect them in the same order as given.
 * Cut and make one breakpoint in any place of the loop, which will make the looped string 
 * into a regular one starting from the character at the cutpoint.
 * And your job is to find the lexicographically biggest one among all the possible 
 * regular strings.
 * 
 * Example:
 * 
 * Input: "abc", "xyz"
 * Output: "zyxcba"
 * Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
 * where '-' represents the looped status. 
 * The answer string came from the fourth looped one, 
 * where you could cut from the middle character 'a' and get "zyxcba".
 * Note:
 * 
 * The input strings will only contain lowercase letters.
 * The total length of all the strings will not over 1,000.
 * https://leetcode.com/problems/split-concatenated-strings/discuss/101804/Java-straight-forward-method-with-explanation
 */

class Solution {
	public String splitLoopedString(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            String rev = new StringBuilder(strs[i]).reverse().toString();
            if (strs[i].compareTo(rev) < 0)
                strs[i] = rev;
        }
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            String rev = new StringBuilder(strs[i]).reverse().toString();
            for (String st: new String[] {strs[i], rev}) {
                for (int k = 0; k < st.length(); k++) {
                    StringBuilder t = new StringBuilder(st.substring(k));
                    for (int j = i + 1; j < strs.length; j++)
                        t.append(strs[j]);
                    for (int j = 0; j < i; j++)
                        t.append(strs[j]);
                    t.append(st.substring(0, k));
                    if (t.toString().compareTo(res) > 0)
                        res = t.toString();
                }
            }
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
