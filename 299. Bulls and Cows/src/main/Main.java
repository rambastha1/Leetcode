package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
	
	public String getHint(String secret, String guess) {
        int len = secret.length();
        int[] count = new int[10];
        int bulls = 0;
        int cows = 0;
        
        for (int i = 0; i < len; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls += 1;
            } else {
            	// if seen earlier it's a cow
            	/* seen in guess before secret
            	 * count[guess.charAt(i) - '0']--
            	 */
                if (count[secret.charAt(i) - '0']++ < 0) {
                    cows += 1;
                }
                /* seen in secret before guess
            	 * count[secret.charAt(i) - '0']++
            	 */
                if (count[guess.charAt(i) - '0']-- > 0) {
                    cows += 1;
                }
            }
        }
        
        return bulls + "A" + cows + "B";
    }
	
    /*public String getHint(String secret, String guess) {
    	if(secret == null || secret.length() == 0 || guess == null || guess.length() == 0)
    		return "";
    	Map<Integer, Set<Integer>> map = new HashMap<>();
    	for(int i = 0;i < secret.length();i++) {
    		if(!map.containsKey(secret.charAt(i)-'0'))
    			map.put(secret.charAt(i)-'0', new HashSet<>());
    		map.get(secret.charAt(i)-'0').add(i);
    	}
    	
    	int []count = new int[10];
    	for(char c : guess.toCharArray())
    		count[c-'0']++;
    	
    	int bulls = 0, cows = 0;
    	for(int i = 0;i < guess.length();i++) {
    		char c = guess.charAt(i);
    		if(!map.containsKey(c-'0'))
    			continue;
    		
    		if(map.get(c-'0').contains(i)) {
    			bulls++;
    			map.get(c-'0').remove(i);
    		} else if(count[c-'0'] <= map.get(c-'0').size())
    			cows++;
    		count[c-'0']--;
    	}
    	return bulls + "A" + cows + "B";
    }*/
}

public class Main {
	public static void main(String[] args) {
		//String secret = "1807", guess = "7810";
		String secret = "1123", guess = "0111";
		System.out.println(new Solution().getHint(secret, guess));
	}
}