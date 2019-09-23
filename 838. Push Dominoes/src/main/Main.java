package main;
// https://leetcode.com/problems/push-dominoes/discuss/132332/C%2B%2BJavaPython-Two-Pointers
class Solution {
	// 'R......R' => 'RRRRRRR'
    // 'R......L' => 'RRRRLLLL' or 'RRR.LLL'
    // 'L......R' => 'L.....R'
    // 'L......L' => 'LLLLLLLL'
	
	public String pushDominoes(String d) {
        d = 'L' + d + 'R';
        int start = 0;
        StringBuilder res = new StringBuilder();
        for (int end = 1; end < d.length(); ++end) {
            if (d.charAt(end) == '.') continue;
            int middle = end - start - 1;
            if (start > 0) res.append(d.charAt(start));
            
            if (d.charAt(start) == d.charAt(end))
                for (int k = 0; k < middle; k++) res.append(d.charAt(start));
            else if (d.charAt(start) == 'L' && d.charAt(end) == 'R')
                for (int k = 0; k < middle; k++) res.append('.');
            else {
                for (int k = 0; k < middle / 2; k++) res.append('R');
                if (middle % 2 == 1) res.append('.');
                for (int k = 0; k < middle / 2; k++) res.append('L');
            }
            start = end;
        }
        return res.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String dominoes = ".L.R...LR..L..";
		//String dominoes = "RR.L";
		System.out.println(new Solution().pushDominoes(dominoes));
	}
}
