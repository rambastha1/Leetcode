package main;

class Result {
	
	/*public boolean check(int []freq, int k) {
		for(int i = 0;i < 26;i++) {
			if(freq[i] != 0 && freq[i] != k)
				return false;
		}
		return true;
	}*/
	
    /*public int perfectSubstring(String s, int k) {
    	int res = 0;
    	
    	char []arr = s.toCharArray();
    	for(int i = 0;i < s.length();i++) {
    		int []freq = new int[26];
    		for(int j = i;j < s.length();j++) {
    			int index = s.charAt(j) - 'a';
    			freq[index]++;
    			if(freq[index] > k)
    				break;
    			else if(freq[index] == k && check(freq, k))
    				res++;
    		}
    	}
    	return res;
    }*/
	
	public boolean check(int []freq, int k) {
		for(int i = 0;i < 10;i++) {
			if(freq[i] != 0 && freq[i] != k)
				return false;
		}
		return true;
	}
	
	public int perfectSubstring(String s, int k) {
		int res = 0;
		for(int i = 0;i < s.length();i++) {
			int []freq = new int[10];
			for(int j = i;j < s.length();j++) {
				int index = s.charAt(j) - '0';
				freq[index]++;
				if(freq[index] > k)
					break;
				else if(freq[index] == k && check(freq, k))
					res++;
			}
		}		
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "1020122"; 
	    int k = 2;
	    System.out.println(new Result().perfectSubstring(s, k));
	}
}