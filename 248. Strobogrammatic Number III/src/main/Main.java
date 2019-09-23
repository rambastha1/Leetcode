package main;

/* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */

class Solution {
	char [][]pairs = {{'0','0'}, {'1','1'}, {'8','8'}, {'6','9'}, {'9','6'}};
	int count = 0;
	public int strobogrammaticInRange(String low, String high) {
		
		for(int len = low.length();len <= high.length();len++) {
			char []c = new char[len];
			dfs(low, high, c, 0, len-1);
		}
		return count;
	}
	/* Keep adding pairs characters at either end and shrink length by 2 i.e left+1 and right-1
	 * when overlap get string value and check whether it lies within range
	 * if yes increment count
	 */
	void dfs(String low, String high, char []c, int left, int right) {
		if(left > right) {
			String s = String.valueOf(c);
			if(s.length() == low.length() && s.compareTo(low) < 0 || s.length() == high.length() &&
					s.compareTo(high) > 0) {
				return;
			}			
			count++;
			return;	
		}
		
		for(char []p : pairs) {
			// add characters at either end
			c[left] = p[0];
			c[right] = p[1];
			// two or more digits with most significant 0 not valid digit
			if(c.length != 1 && c[0] == '0')
				continue;
			// one digit 6 and 9 don't add
			if(left == right && p[0] != p[1])
				continue;
			dfs(low, high, c, left+1, right-1);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		String low = "50", high = "100";
		System.out.println(new Solution().strobogrammaticInRange(low, high));
	}
}
