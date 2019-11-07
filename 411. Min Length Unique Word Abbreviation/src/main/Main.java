package main;

import java.util.ArrayList;
import java.util.List;

/* A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the 
smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 * 
 */

/*
 * The key idea of my solution is to preprocess the dictionary to transfer all the words to bit sequences (int):
Pick the words with same length as target string from the dictionary and compare the characters with target. 
If the characters are different, set the corresponding bit to 1, otherwise, set to 0.
Ex: "abcde", ["abxdx", "xbcdx"] => [00101, 10001]

The problem is now converted to find a bit mask that can represent the shortest abbreviation, so that for all the 
bit sequences in dictionary, mask & bit sequence > 0.
Ex: for [00101, 10001], the mask should be [00001]. if we mask the target string with it, we get "****e" ("4e"), 
which is the abbreviation we are looking for.

To find the bit mask, we need to perform DFS with some optimizations. But which bits should be checked? We can 
perform "or" operation for all the bit sequences in the dictionary and do DFS for the "1" bits in the result.
Ex: 00101 | 10001 = 10101, so we only need to take care of the 1st, 3rd, and 5th bit.
 */

class Solution {

    int n, cand, bn, minlen, minab;
    List<Integer> dict = new ArrayList<>();


    // Abbreviation for one digit is meaningless, thus at least two digits are used for abbreviation.
    private int abbrLen(int mask){
        int count = n;
        for (int b = 3; b < bn; b <<= 1)
            if ((mask & b) == 0)
                count --;
        return count;
    }

    private void dfs(int bit, int mask){
        int len = abbrLen(mask);
        if(len >= minlen) return;
        boolean match = true;
        for(Integer d : dict){
            if((mask & d) == 0){
                match = false;
                break;
            }
        }
        // a mask which can cover all differences, no need to find more.
        if(match){
            minlen = len;
            minab = mask;
        }
        // No ? Then has to add more masks to cover all differences.
        else{
            for(int b = bit; b < bn; b <<= 1){
                if((cand & b) != 0) dfs(b << 1, mask + b);
            }
        }
    }

    String minAbbreviation(String target, String[] dictionary){
        n = target.length(); bn = 1 << n; cand = 0; minlen = Integer.MAX_VALUE;
        StringBuilder res = new StringBuilder();

        for(String s : dictionary){
            int word = 0;
            if(s.length() != n) continue;
            for(int i = 0; i < n; i++)
                if(target.charAt(i) != s.charAt(i)) word |= 1 << i;
            dict.add(word);
            cand |= word;
        }

        dfs(1, 0);  // DFS : 1 -> 1010 -> 10101
                    //                 -> 10100
                    //         -> 1011 -> 10110       
                                    
        for(int i = 0; i < n;){
            if((minab & (1 << i)) != 0){
                res.append(target.charAt(i++));
            }else{
                int j = i;
                while(i < n && (minab & (1 << i)) == 0)i++;
                res.append(i - j);
            }
        }

        return res.toString();
    }

}

public class Main {
	public static void main(String[] args) {

	}
}