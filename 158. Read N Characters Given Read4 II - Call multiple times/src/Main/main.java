package Main;

// https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/49607/The-missing-clarification-you-wish-the-question-provided
abstract class Reader4 {
	public abstract int read4(char[] buf, int n);
}
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
	public int read4(char[] buf, int n) {
		return -1;
	}
	
	public int read4(char[] buf) {
		return -1;
	}
	
	private char []tempbuffer = new char[4];
	private int bufferindex = 0, chartoread = 0;
	/* read4 returns 4 or count of letter read
	 * just like jump game, read store and increment the counter bufferindex
	 * if it matches chartoread, reset it to 0 and read again
	 * 
	 */
    public int read(char[] buf, int n) {
    	int index = 0;
    	while(index < n) {
    		if(this.bufferindex == this.chartoread) {
    			this.bufferindex = 0;
    			this.chartoread = read4(this.tempbuffer);
    			if(this.chartoread == 0)
    				break;
    		}
    		buf[index++] = this.tempbuffer[this.bufferindex++]; 
    	}
        return index;
    }
}

public class main {
	public static void main(String[] args) {

	}
}