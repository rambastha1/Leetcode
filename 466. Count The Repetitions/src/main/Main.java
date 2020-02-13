package main;
// https://leetcode.com/problems/count-the-repetitions/discuss/95398/C%2B%2B-solution-inspired-by-%4070664914-with-organized-explanation
class Solution {
	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();
        int[] repeatCount = new int[len2 + 1];
        int[] nextIndex   = new int[len2 + 1];
        int j = 0, cnt = 0;
        for (int k = 1; k <= n1; k++){
            for (int i = 0; i < len1; i++){
                if (s1.charAt(i) == s2.charAt(j)){
                    j++;
                    if (j == len2){
                        j = 0; 
                        cnt++;
                    }
                }
            }
            repeatCount[k] = cnt;
            nextIndex[k] = j;
            for (int start = 0; start < k; start++) {
            	// see if you have met this nextIndex before
                if (nextIndex[start] == j) {
                	// if found, you can calculate the 3 parts
                	// prefixCount is the start-th repeatCount
                    int prefixCount = repeatCount[start];
                    // (repeatCount[k] - prefixCount) is the repeatCount of one occurrance of the pattern
                    // There are (n1 - start) / (k - start) occurrances of the pattern
                    // So (n1 - start) / (k - start) * (repeatCount[k] - prefixCount) is the repeatCount of the repetitive part
                    int patternCount = (n1 - start) / (k - start) * (repeatCount[k] - prefixCount);
                    // The suffix contains the incomplete repetitive remnant (if any)
                    // Its length is (n1 - start) % (k - start)
                    // So the suffix repeatCount should be repeatCount[start + (n1 - start) % (k - start)] - prefixCount
                    int suffixCount = repeatCount[start + (n1 - start) % (k-start)]- prefixCount;
                    return (prefixCount + patternCount + suffixCount) / n2;
                }
            }
        }
        return repeatCount[n1] / n2;
    }
}

public class Main {
	public static void main(String[] args) {
		String s1="acb", s2="ab";
		int n1=4, n2=2;
		System.out.println(new Solution().getMaxRepetitions(s1, n1, s2, n2));
	}
}
