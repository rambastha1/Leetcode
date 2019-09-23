package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* https://leetcode.com/discuss/interview-question/373006
 * Given a map Map<String, List<String>> userMap, where the key is a username and the value is a list of user's songs.
Also given a map Map<String, List<String>> genreMap, where the key is a genre and the value is a list of songs belonging to this genre.
The task is to return a map Map<String, List<String>>, where the key is a username and the value is a list of the user's favorite genres. Favorite genre is a genre with the most song.

Example 1:

Input:
userMap = {  
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
},
genreMap = {  
   "Rock":    ["song1", "song3"],
   "Dubstep": ["song7"],
   "Techno":  ["song2", "song4"],
   "Pop":     ["song5", "song6"],
   "Jazz":    ["song8", "song9"]
}

Output: {  
   "David": ["Rock", "Techno"],
   "Emma":  ["Pop"]
}

Explanation:
David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.

Example 2:

Input:
userMap = {  
   "David": ["song1", "song2"],
   "Emma":  ["song3", "song4"]
},
genreMap = {}

Output: {  
   "David": [],
   "Emma":  []
}
 */

class Solution {
	public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
		Map<String, List<String>> res = new HashMap<>();
		Map<String, String> songstogenre = new HashMap<>();
		
		for(String genre : genreMap.keySet()) {
			List<String> songs = genreMap.get(genre);
			for(String song : songs) {
				songstogenre.put(song, genre);
			}
		}
		
		Map<String, Map<String, Integer>> usergenrecount = new HashMap<>();
		for(String user : userMap.keySet()) {
			if(!usergenrecount.containsKey(user))
				usergenrecount.put(user, new HashMap<>());
			List<String> songs = userMap.get(user);
			for(String song : songs) {
				String genre = songstogenre.get(song);
				int count = usergenrecount.get(user).getOrDefault(genre, 0) + 1;
				usergenrecount.get(user).put(genre, count);
			}
		}
		
		for(String user : usergenrecount.keySet()) {
			if(!res.containsKey(user))
				res.put(user, new ArrayList<>());
			Map<String, Integer> pair = usergenrecount.get(user);
			int max = 0;
			List<String> favgenre = new ArrayList<>();
			for(String genre : pair.keySet()) {
				if(favgenre.size() == 0) {
					favgenre.add(genre);
					max = pair.get(genre);
				} else if(pair.get(genre) > max) {
					favgenre.clear();
					favgenre.add(genre);
					max = pair.get(genre);
				} else if(pair.get(genre) == max)
					favgenre.add(genre);
			}
			res.put(user, favgenre);
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		/*Map<String, List<String>> userMap = new HashMap<>();
		List<String> list1 = Arrays.asList("song1", "song2", "song3", "song4", "song8");
		List<String> list2 = Arrays.asList("song5", "song6", "song7");
		userMap.put("David", list1);
		userMap.put("Emma", list2);
		
		Map<String, List<String>> genreMap = new HashMap<>();
		List<String> list3 = Arrays.asList("song1", "song3");
		List<String> list4 = Arrays.asList("song7");
		List<String> list5 = Arrays.asList("song2", "song4");
		List<String> list6 = Arrays.asList("song5", "song6");
		List<String> list7 = Arrays.asList("song8", "song9");
		genreMap.put("Rock", list3);
		genreMap.put("Dubstep", list4);
		genreMap.put("Techno", list5);
		genreMap.put("Pop", list6);
		genreMap.put("Jazz", list7);*/
		
		Map<String, List<String>> userMap = new HashMap<>();
		List<String> list1 = Arrays.asList("song1", "song2");
		List<String> list2 = Arrays.asList("song3", "song4");
		userMap.put("David", list1);
		userMap.put("Emma", list2);
		
		Map<String, List<String>> genreMap = new HashMap<>();
		
		System.out.println(new Solution().favoritegenre(userMap, genreMap));
	}
}
