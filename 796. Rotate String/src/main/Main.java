package main;

public class Main {

	public static void main(String[] args) {
		String A = "abcde";
		String B = "abced";
		System.out.println(rotateString(A, B));		
	}
	
	public static boolean rotateString(String A, String B) {
		
		if(A.length() == 0 && B.length() == 0)
			return true;
		if(A.length() == 0 || B.length() == 0)
			return false;
		
		int i = 1;
		while(i <= A.length()) {
			A = rotate(A);
			if(A.equals(B))
				return true;
			i++;
		}
		return false;
    }
	
	public static String rotate(String s) {
		
		if(s.length() == 0)
			return null;
		if(s.length() == 1)
			return s;
		
		char []arr = s.toCharArray();
		char ch = arr[0];
		for(int i = 1;i < arr.length;i++)
			arr[i-1] = arr[i];
		arr[arr.length-1] = ch;
		return new String(arr);
	}

}
