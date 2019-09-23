package main;

/*
 * Given a list of words and two words  word1  and  word2 , 
 * return the shortest distance between these two words in the list.
 * 
 * For example, 
 * Assume that words =  ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given  word1  =  “coding”,  word2  =  “practice”, return 3. 
 * Given  word1  =  "makes",  word2  =  "coding", return 1.
 * 
 * Note: 
 * You may assume that  word1  does not equal to  word2 , and  word1  and  word2  are both in the list.
 */

class Solution {
	
	//Part I word1 and word2 are different
    public int shortestDistance(String[] words, String word1, String word2) {
    	int w1 = -1, w2 = -1, min = Integer.MAX_VALUE;;
    	for(int i = 0;i < words.length;i++) {
    		if(words[i].compareTo(word1) == 0)
    			w1 = i;
    		if(words[i].compareTo(word2) == 0)
    			w2 = i;
    		if(w1 != -1 && w2 != -1)
    			min = Math.min(min, Math.abs(w1-w2));
    	}    	
    	return min;
    }
    
    //Part III word1 and word2 could be same
    //Better way of writing
    public int shortestDistance3(String[] words, String word1, String word2) {
    	int w1 = -1, w2 = -1, min = Integer.MAX_VALUE;
    	
    	for(int i = 0;i < words.length;i++) {
    		if(word1.compareTo(word2) == 0) {
    			if(words[i].compareTo(word1) == 0) {
    				if(w2 < w1) w2 = i;
    				else w1 = i;
    			}
    		} else {
    			if(words[i].compareTo(word1) == 0) w1 = i;
    			if(words[i].compareTo(word2) == 0) w2 = i;
    		}
    		if(w1 != -1 && w2 != -1)
    			min = Math.min(min, Math.abs(w2-w1));
    	}
    	return min;
    }
    
    /* I wrote it
     * public int shortestDistance3(String[] words, String word1, String word2) {
    	int w1 = -1, w2 = -1, min = Integer.MAX_VALUE;    		
    	boolean flag = word1.compareTo(word2) == 0?true:false;    	
    	for(int i = 0;i < words.length;i++) {
    		if(words[i].compareTo(word1) == 0) {
    			if(!flag)
    				w1 = i;
    			if(flag && w1 == -1)
    				w1 = i;
    		}
    		
    		if(words[i].compareTo(word2) == 0) {
    			if(!flag)
    				w2 = i;
    			if(flag && w1 != -1 && w1 != i)
    				w2 = i;
    		}
    			
    		if(w1 != -1 && w2 != -1) {
    			min = Math.min(min, Math.abs(w2-w1));
    			if(flag) {
    				w1 = w2;
    				w2 = -1;
    			}
    		}
    	}
    	return min;
    }*/
}

public class Main {
	public static void main(String[] args) {
		String []words = {"practice", "makes", "perfect", "coding", "makes"};
		//String word1  =  "coding",  word2  =  "practice";
		String word1  =  "makes",  word2  =  "coding";
		System.out.println(new Solution().shortestDistance(words, word1, word2));
		
		//Part III
		String []words3 = {"practice", "makes", "perfect", "coding", "makes"};
		//String word13  =  "makes",  word23  =  "makes";
		String word13  =  "coding",  word23  =  "practice";
		System.out.println(new Solution().shortestDistance3(words3, word13, word23));
	}
}