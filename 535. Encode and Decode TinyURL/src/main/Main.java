package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Must read
// https://leetcode.com/articles/encode-and-decode-tinyurl/

class Codec {
	String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Map<String, String> map = new HashMap<>();
	Random rand = new Random();
	String key = getRand(); 
			
	private String getRand() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < 6;i++)
			sb.append(alphabet.charAt(rand.nextInt(62)));
		return sb.toString();
	}
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
    	while(map.containsKey(key))
    		key = getRand();
    	map.put(key, longUrl);
    	return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

public class Main {
	public static void main(String[] args) {
		String longUrl = "https://leetcode.com/problems/design-tinyurl";
		Codec c = new Codec();
		String shortUrl = c.encode(longUrl);
		System.out.println(shortUrl);
		System.out.println(c.decode(shortUrl));
	}
}