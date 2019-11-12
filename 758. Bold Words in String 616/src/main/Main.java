package main;

/* Given a set of keywords words and a string S, make all appearances of all keywords in S bold. 
 * Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". 
Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.
 * 
 */

class Solution {
	public String boldWords(String[] words, String S) {
	    int[] s = new int[S.length()+1];
	    for(String w : words) {
	        int i = 0;
	        while ((i = S.indexOf(w, i)) >= 0) {
	            s[i]++;
	            s[i + w.length()]--;
	            i++;
	        }
	    }
	    StringBuilder sb = new StringBuilder();
	    int pre = 0, sum = 0;
	    for(int i = 0; i <= S.length(); i++) {
	        sum += s[i];
	        if (sum > 0 && pre == 0) sb.append("<b>");
	        if (sum == 0 && pre > 0) sb.append("</b>");
	        if (i < S.length()) sb.append(S.charAt(i));
	        pre = sum;
	    }
	    return sb.toString();
	}
}

public class Main {
	public static void main(String[] args) {
		String []words = {"ab", "bc"};
		String S = "aabcd";
		System.out.println(new Solution().boldWords(words, S));
	}
}