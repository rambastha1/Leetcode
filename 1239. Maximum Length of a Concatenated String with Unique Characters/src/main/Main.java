package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/discuss/414172/PythonC%2B%2BJava-Set-Solution
	public int maxLength(List<String> arr) {
        int n = arr.size();
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        int maxlen = 0;
        for(int i = 0;i < n;i++) {
            String s = arr.get(i);
            int num = 0, dup = 0;
            for(char c : s.toCharArray()) {
            	dup |= num & (1 << (c-'a'));
                num |= 1 << (c-'a');
            }
            
            if(dup > 0)
            	continue;
            
            for(int j = temp.size()-1;j >= 0;j--) {
            	if((num & temp.get(j)) > 0)
            		continue;
            	temp.add(num | temp.get(j));
            	maxlen = Math.max(maxlen, Integer.bitCount(num | temp.get(j)));
            }
        }
        return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		List<String> arr = Arrays.asList("un", "iq", "ue");
		System.out.println(new Solution().maxLength(arr));
	}
}
