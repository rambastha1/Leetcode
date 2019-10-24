package main;

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */

class Reader4 {
	int read4(char[] buf) {
		return -1;
	}
}
// https://leetcode.com/problems/read-n-characters-given-read4/discuss/49496/Another-accepted-Java-solution
class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
	
    public int read(char[] buf, int n) {
    	char []temp = new char[4];
    	int total = 0;
    	boolean eof = false;
    	
    	while(!eof && (total < n)) {
    		int count = read4(temp);
    		
    		eof = count < 4;
    		// get the actual count At the  end of file, it might not be 4
    		count = Math.min(count, n - total);
    		// copy from temp buffer to buf
    		for(int i = 0;i < count;i++) {
    			buf[total++] = temp[i];
    		}
    	}
    	return total;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}