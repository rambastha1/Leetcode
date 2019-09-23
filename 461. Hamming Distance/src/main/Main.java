package main;

public class Main {

	public static void main(String[] args) {
		int x = 9;
		int y = 2;
		System.out.println(hammingDistance(x, y));
	}
	
	public static int hammingDistance(int x, int y) {
        /*
         * Short solution using inbuilt functions 
		 * int num = x ^ y;
         * return Integer.bitCount(num);
         */
		
		int result = 0;
        String x_binary = Integer.toBinaryString(x);
        String y_binary = Integer.toBinaryString(y);
        int diff = Math.abs(x_binary.length() - y_binary.length());
        //Padding with 0
        if(x_binary.length()<y_binary.length()){
            for(int i= 0;i<diff;i++){
                x_binary = "0" + x_binary;
            }
        }else if(x_binary.length()>y_binary.length()){
            for(int i= 0;i<diff;i++){
                y_binary = "0" + y_binary;
            }
        }
        
        //Check if characters are different
        for(int i = 0;i<x_binary.length();i++){
            if (x_binary.charAt(i) != y_binary.charAt(i)){
                result ++;
            }
        }
        return result;
    }
}