package main;

// https://leetcode.com/articles/utf-8-validation/

class Solution {
    public boolean validUtf8(int[] data) {
    	// Number of bytes in the current UTF-8 character
    	int bytestoprocess = 0;
    	
    	// Masks to check two most significant bits in a byte.
    	int mask1 = 1<<7, mask2 = 1<<6;
    	for(int i = 0;i < data.length;i++) {
    		 // If this is the case then we are to start processing a new UTF-8 character.
    		if(bytestoprocess == 0) {
    			int mask = 1<<7;
    			while((data[i] & mask) != 0) {
    				bytestoprocess++;
    				mask >>= 1;
    			}
    			// 1 byte characters
    			if(bytestoprocess == 0)
    				continue;
    			
    			// byte char > 4 or  1 byte character with leading 1
    			if(bytestoprocess > 4 || bytestoprocess == 1)
    				return false;
    		} else {
    			// data[i] should have most significant bit set and
                // second most significant bit unset. So, we use the two masks
                // to make sure this is the case.
    			if(!((data[i]&mask1)!= 0 && (data[i]&mask2)==0))
    				return false; 
    		}
    		bytestoprocess--;
    	}
    	// This is for the case where we might not have the complete data for
        // a particular UTF-8 character.
    	return bytestoprocess==0;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
