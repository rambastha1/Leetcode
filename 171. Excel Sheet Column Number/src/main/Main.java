package main;

public class Main {

	public static void main(String[] args) {
		String s = "AAB";
		System.out.println(titleToNumber(s));

	}
	public static int titleToNumber(String s) {
        int res = 0;
        for(char c : s.toCharArray())
            res = res*26 + c - 'A' + 1;
        return res;            
    }
}
