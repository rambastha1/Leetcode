package main;

class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int currx = 0, curry = 0;
        /* ordering is important as down should be last to avoid out of boundary
         * 
         */
        for(char c : target.toCharArray()) {
            int nextx = (c-'a')/5, nexty= (c-'a')%5;
            while(nextx < currx) {
            	sb.append("U");
            	currx--;
            }
            
            while(curry < nexty) {
            	sb.append("R");
            	curry++;
            }
            
            while(curry > nexty) {
            	sb.append("L");
            	curry--;
            }
            
            while(currx < nextx) {
            	sb.append("D");
            	currx++;
            }
            sb.append("!");
            currx = nextx;
            curry = nexty;
        }
        return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String target = "zdz";
		System.out.println(new Solution().alphabetBoardPath(target));
	}
}
