package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* https://leetcode.com/problems/design-log-storage-system/discuss/105006/Java-range-query-using-TreeMap.subMap()
 * 
 * You are given several logs that each log contains a unique id and timestamp. 
 * Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, 
 * for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 * 
 * Design a log storage system to implement the following functions:
 * 
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, 
 * store the log in your storage system.
 * 
 * int[] Retrieve(String start, String end, String granularity): 
 * Return the id of logs whose timestamps are within the range from start to end. 
 * Start and end all have the same format as timestamp. 
 * However, granularity means the time level for consideration. 
 * For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", 
 * it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 * 
 * Example 1:
 * put(1, "2017:01:01:23:59:59");
 * put(2, "2017:01:01:22:59:59");
 * put(3, "2016:01:01:00:00:00");
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], 
 * because you need to return all logs within 2016 and 2017.
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); 
 * return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, 
 * where log 3 is left outside the range.
 * 
 * Note:
 * There will be at most 300 operations of Put or Retrieve.
 * Year ranges from [2000,2017]. Hour ranges from [00,23].
 * Output for Retrieve has no order required.
 */

class LogSystem {
	private String min, max;
	// granularity -> index of :
	private Map<String, Integer> indexmap;
	// timestamp -> keys
	private TreeMap<String, List<Integer>> keymap; 
	
	public LogSystem() {
		// given in question
		this.min = "2000:01:01:00:00:00";
        this.max = "2017:12:31:23:59:59";
        indexmap = new HashMap<>();
        /* indices of the ':' after each time granularity (Year, or Month or Day), 
         * so that we could use this index to cut off part of the timestamp to make up the real start and ending boundary.
         * year is four digit 0-3, month 2 digit 1-12 etc
         */
        indexmap.put("Year", 4);
        indexmap.put("Month", 7);
        indexmap.put("Day", 10);
        indexmap.put("Hour", 13);
        indexmap.put("Minute", 16);
        indexmap.put("Second", 19);
        keymap = new TreeMap<>();
    }
	
	void put(int id, String timestamp) {
		if(!this.keymap.containsKey(timestamp))
			this.keymap.put(timestamp, new ArrayList<>());
		this.keymap.get(timestamp).add(id);
	}
	
	public List<Integer> retrieve(String s, String e, String gra) {
		int index = indexmap.get(gra);
		
		String start = s.substring(0, index) + min.substring(index);
		String end = e.substring(0, index) + max.substring(index);
		// returns a submap from key start to end both inclusive
		NavigableMap<String, List<Integer>> map = keymap.subMap(start, true, end, true);
		List<Integer> res = new ArrayList<>();
		for(Map.Entry<String, List<Integer>> entry : map.entrySet()) {
			res.addAll(entry.getValue());
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		LogSystem log = new LogSystem();
		log.put(1, "2017:01:01:23:59:59");
		log.put(2, "2017:01:01:22:59:59");
		log.put(3, "2016:01:01:00:00:00");
		//int []res = log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour");
		System.out.println(log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"));
	}
}