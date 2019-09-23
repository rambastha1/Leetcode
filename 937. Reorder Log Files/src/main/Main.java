package main;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/reorder-log-files/discuss/193872/Java-Nothing-Fancy-15-lines-5ms-all-clear.
class Solution {
    public String[] reorderLogFiles(String[] logs) {
    	Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// index of first space
				int s1si = o1.indexOf(' ');
				int s2si = o2.indexOf(' ');
				char s1fc = o1.charAt(s1si+1); 
				char s2fc = o2.charAt(s2si+1); 
				
				// first is numbers
				if(s1fc <= '9') {
					if(s2fc <= '9')
						return 0;
					// if first digit second letter digit comes after
					else
						return 1;
				}
				// first letter second digit same order
				if(s2fc <= '9')
					return -1;
				
				// both letter 
				int precompute = o1.substring(s1si+1).compareTo(o2.substring(s2si+1));
				// both same check identifier
				if(precompute == 0) {
					return o1.substring(0, s1si).compareTo(o2.substring(0, s2si));
				}
				return precompute;
			}
		};
		Arrays.sort(logs, comp);
    	return logs;
    }
}

public class Main {
	public static void main(String[] args) {
		String[] logs = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
		System.out.println(Arrays.toString(new Solution().reorderLogFiles(logs)));
	}
}
