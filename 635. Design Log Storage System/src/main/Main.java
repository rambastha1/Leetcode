package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
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
	
	//Even store log as this
	/*class Log {
		int year, month, day, hour, min, sec;
		public Log(int year, int month, int day, int hour, int min, int sec) {
			this.year = year;
			this.month = month;
			this.day =day;
			this.hour = hour;
			this.min = min;
			this.sec =sec;
		}
	}*/
	
	public LogSystem() {
        
    }
	
	Map<String, Integer> map = new HashMap<>();
	List<String> granularity_list = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
	
	void put(int id, String timestamp) {
		String []str = timestamp.split(":");
		map.put(timestamp, id);
	}
	
	public List<Integer> retrieve(String s, String e, String gra) {
		String []s1 = s.split(":"), s2 = e.split(":");
		int index = granularity_list.indexOf(gra);
		
		List<Integer> ans = new ArrayList<>();
		for(String str : map.keySet()) {
			String []node = str.split(":");
			if((node[index].compareTo(s1[index]) >= 0) && (node[index].compareTo(s2[index]) <= 0))
				ans.add(map.get(str));
		}
		return ans;
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