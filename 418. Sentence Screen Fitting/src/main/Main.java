package main;

// https://massivealgorithms.blogspot.com/2016/10/leetcode-418-sentence-screen-fitting.html
/*
 * Given a rows x cols screen and a sentence represented by a list of words, 
 * find how many times the given sentence can be fitted on the screen.
 * 
 * Note:
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word won't exceed 10.
 * 1 ≤ rows, cols ≤ 20,000.
 * 
 * Example 1:
 * Input:
 * rows = 2, cols = 8, sentence = ["hello", "world"]
 * 
 * Output: 
 * 1
 * 
 * Explanation:
 * hello---
 * world---
 * 
 * The character '-' signifies an empty space on the screen.
 * Example 2:
 * Input:
 * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * 
 * Output: 
 * 2
 * 
 * Explanation:
 * a-bcd- 
 * e-a---
 * bcd-e-
 * 
 * The character '-' signifies an empty space on the screen.
 * Example 3:
 * Input:
 * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 * 
 * Output: 
 * 1
 * 
 * Explanation:
 * I-had
 * apple
 * pie-I
 * had--
 * 
 * The character '-' signifies an empty space on the screen.
 */

class Solution {
	// https://github.com/YaokaiYang-assaultmaster/LeetCode/blob/master/LeetcodeAlgorithmQuestions/418.%20Sentence%20Screen%20Fitting.md
	// Time 0(n+rows) n-> sentence length
	// https://www.youtube.com/watch?v=ZeLrhECnlF4
	/* count is counter of sentence characters
	 * if current character is space, it can be fitted in current row, increase counter
	 * else we are in middle of word, keep decreasing counter until we reach space
	 * in the end, count will be total length of characters that can be fit. divide by length gives 
	 * number of times whole sentence can be fitted
	 */
	public int wordsTyping(String[] sentence, int rows, int cols) {
		StringBuilder sb = new StringBuilder();
		for(String s : sentence) {
			sb.append(s);
			sb.append(" ");
		}
		int count = 0, n = sb.length();
		for(int i = 0;i < rows;i++) {
			// this is to check if whole sentence can be fit in one row else decrease count appropriately
			count += cols;
            while(count >= 0 && sb.charAt(count%n) != ' ')
                count--;
			count++;
		}
		return count/n;
	}
}

public class Main {
	public static void main(String[] args) {
		int rows = 3, cols = 6;
		String []sentence = {"a", "bcd", "e"};
		System.out.println(new Solution().wordsTyping(sentence, rows, cols));
	}
}
