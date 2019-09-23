package main;

class Solution {
	public int minCut(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		/*
		 * cut[i] denotes number of partitions required to convert this string from [0, i] into
		 * palindrome including both 0 and i.
		 */
		int[] cut = new int[s.length()];
		for(int i = 0; i < s.length(); i++) {
			cut[i] = i;
		}
		for(int i = 0; i < s.length(); i++) {
			cutPalindrome(s, i, i, cut);
			cutPalindrome(s, i, i + 1, cut);
		}
		return cut[s.length() - 1];
	}

	private void cutPalindrome(String s, int i, int j, int[] cut) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			// if i = 0 it means s.substring(i, j + 1) is palindrome
			// thus no need to cut and since i can't be decremented thus return
			if (i == 0) {
				cut[j] = 0;
				return;
			} else {
				/*
				 * found a part to be palindrome in this subpart
				 * partition so that both sub parts are palindromes
				 */
				int tmpCutCount = cut[i-1] + 1;
				if (cut[j] > tmpCutCount) {
					cut[j] = tmpCutCount;
				}
			}
			j++;
			i--;
		}
	}    
}

public class Main {
	public static void main(String[] args) {
		String s = "ababbbabbababa";
		//String s = "aab";
		System.out.println(new Solution().minCut(s));
	}
}