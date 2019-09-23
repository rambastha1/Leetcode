package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        while(index >= 0 && digits[index] == 9) {
            digits[index] = 0;
            index--;
        } 
        if(index == -1) {
            int[] array = new int[digits.length + 1];
            array[0] = 1;
            print(digits);
            return array;
        }
        digits[index]++;
        print(digits);
        return digits;
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int []digits = {9,2,8};
		new Solution().plusOne(digits);
	}
}
