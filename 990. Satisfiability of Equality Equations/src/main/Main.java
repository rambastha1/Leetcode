package main;

class Solution {
	// Time 0 (N)
	public boolean equationsPossible(String[] equations) {
		if(equations == null || equations.length == 0)
			return false;
		int []parent = new int[26];
		for(int i = 0;i < 26;i++)
			parent[i] = i;
		
		for(String eq : equations) {
			if(eq.charAt(1) == '=') {
				int x = find(eq.charAt(0)-'a', parent);
				int y = find(eq.charAt(3)-'a', parent);
				parent[x] = y;
			}
		}
		
		for(String eq : equations) {
			if(eq.charAt(1) == '!') {
				int x = find(eq.charAt(0)-'a', parent);
				int y = find(eq.charAt(3)-'a', parent);
				if(x == y)
					return false;
			}
		}
		return true;
	}
	
	int find(int i, int []parent) {
		if(parent[i] != i)
			parent[i] = find(parent[i], parent);
		return parent[i];
	}
	
}

public class Main {
	public static void main(String[] args) {
		String []equations = {"b==a","a!=b"};
		//String []equations = {"b==a","a==b"};
		//String []equations = {"a==b","b==c","a==c"};
		//String []equations = {"a==b","b!=c","c==a"};
		//String []equations = {"c==c","b==d","x!=z"};
		//String []equations = {"a!=a"};
		//String []equations = {"e==e","d!=e","c==d","d!=e"};
		System.out.println(new Solution().equationsPossible(equations));
	}
}
