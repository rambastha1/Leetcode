package main;

/* Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. 
 * There is no limit on how many times a digit can be reused.
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all 
 * valid. "1:34", "12:9" are all invalid.
 * 
 * Example 1:
 * Input: "19:34"
 * Output: "19:39"
 * 
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 
 * minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * 
 * Example 2:
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. 
 * It may be assumed that the returned time is next day's time since it is smaller than the 
 * input time numerically.
 */

class Solution {
	
	// Time 0(10) = constant time 
	public String nextClosestTime(String time) {
		char []t = time.toCharArray();
		int []arr = new int[10];
		char min = '9';
		// find min number
		for(char c : t) {
			if(c == ':')
				continue;
			arr[c-'0']++;
			if(c < min)
				min = c;
		}
		/* if lower min number is less than 9 and there is number greater than in the string replace
		 * it with that number and return 
		 */
		for(int i = t[4]-'0'+1;i <= 9;i++) {
			if(arr[i] != 0) {
				t[4] = (char)(i + '0');
				return new String(t);
			}
		}
		/* if lower min number is already highest number replace with lowest number
		 * for higher minute number it can be at max 5
		 */
		t[4] = min;
		for(int i = t[3] - '0' + 1;i <= 5;i++) {
			if(arr[i] != 0) {
				t[3] = (char) (i + '0');
				return new String(t);
			}
		}
		
		/* for lower hour number, it can be 0-9 if higher hour number is 1
		 * else at max 3 - 20,21,22, 23
		 */
		
		t[3] = min;
		int stop = t[0] < '2'?9:3;
		for(int i = t[1] - '0' + 1;i <= stop;i++) {
			if(arr[i] != 0) {
				t[1] = (char) (i + '0');
				return new String(t);
			}
		}
		
		t[1] = min;
		for(int i = t[0]-'0'+1;i <= 2;i++) {
			if(arr[i] != 0) {
				t[0] = (char)(i + '0');
				return new String(t);
			}
		}
		t[0] = min;
		return new String(t);
	}
}

public class Main {
	public static void main(String[] args) {
		String time = "23:59";
		System.out.println(new Solution().nextClosestTime(time));
	}
}
