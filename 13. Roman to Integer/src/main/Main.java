package main;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		//String s = "III";
		//String s = "IV";
		String s = "MCMXCIV";
		System.out.println(romanToInt(s));
	}
	
	public static int romanToInt(String s) {
        
		if(s.length() == 0)
			return 0;
		
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		char []arr = s.toCharArray();
		int num = map.get(arr[0]);
		for(int i = 1;i < arr.length;i++) {
			if(i-1>= 0 && (arr[i-1] == 'I' && (arr[i] == 'V' || arr[i] == 'X')) || 
					(arr[i-1] == 'X' && (arr[i] == 'L' || arr[i] == 'C')) ||
					(arr[i-1] == 'C' && (arr[i] == 'D' || arr[i] == 'M')))
				num += map.get(arr[i]) - 2 * map.get(arr[i-1]);
			else 
				num += map.get(arr[i]);
		}
		return num;
    }
	
}
