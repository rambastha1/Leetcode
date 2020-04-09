package main;

class Solution {
	/* odd in two cases 0(s[i]) + 1(carry)
	 * or               1         0
	 * requires two steps add and divide carry will be 1 after addition
	 * 
	 * 
	 * if even requires one step divide
	 */
	public int numSteps(String s) {
		int n = s.length();
		int ans = 0, carry = 0;
		for(int i = s.length()-1;i > 0;i--) {
			if(s.charAt(i) - '0' + carry == 1) {
				carry = 1;
				ans += 2;
			} else {
				ans++;
			}
		}
		return ans + carry;
	}
	
	
    public int numSteps1(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        int ans = 0;
        while(sb.length() != 1) {
            char c = sb.charAt(sb.length()-1);
            if(c == '0')
                sb = new StringBuilder(sb.substring(0, sb.length()-1));
            else {
                int carry = 1;
                for(int i = sb.length()-1;i >= 0;i--) {
                    char ch = sb.charAt(i);
                    if(carry == 0 && ch == '0')
                    	break;
                    if(carry == 1) {
                    	if(ch == '1') {
                    		sb.setCharAt(i, '0');
                    	} else {
                    		sb.setCharAt(i, '1');
                    		carry = 0;
                    	}
                    }
                }
                
                if(carry == 1)
                	sb.insert(0, '1');
            }
            ans++;
        }
        return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "1101";
		System.out.println(new Solution().numSteps(s));
	}
}
