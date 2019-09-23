package main;

import java.util.Stack;

class Solution {
    public int clumsy(int N) {
    	Stack<Integer> s = new Stack<>();
    	char []op = {'*', '/', '+', '-'};
    	s.push(N--);
    	int index = 0;
    	while(N > 0) {
    		if(op[index] == '*')
    			s.push(s.pop()*N--);
    		else if(op[index] == '/')
    			s.push(s.pop()/N--);
    		else if(op[index] == '+')
    			s.push(N--);
    		else
    			s.push((-1) * N--);
    		index = (index+1)%4;
    	}
    	int ans = 0;
    	while(!s.isEmpty()) {
    		ans+= s.pop();
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 4;
		System.out.println(new Solution().clumsy(N));
	}
}