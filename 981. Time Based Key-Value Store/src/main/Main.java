package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Time 0(lgN) Space constant
 */
class TimeMap {
	
	class Data {
		String value;
		int timestamp;
		public Data(String value, int timestamp) {
			this.value = value;
			this.timestamp = timestamp;
		}
	}
    /** Initialize your data structure here. */
	Map<String, List<Data>> map;
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key))
        	map.put(key, new ArrayList<Data>());
        map.get(key).add(new Data(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
    	if(!map.containsKey(key))
    		return "";
    	return search(map.get(key), timestamp);
    }
    
    public String search(List<Data> data, int timestamp) {
    	int low = 0, high = data.size()-1;
    	while(low < high) {
    		int mid = low + (high-low)/2;
    		Data d = data.get(mid);    				
    		if(d.timestamp < timestamp) { 
    			if(data.get(mid+1).timestamp > timestamp) {
    				return d.value;
		    	}
		    	low = mid+1;
    		} else 
    			high = mid -1;
    	}
    	return data.get(low).timestamp <= timestamp?data.get(low).value:"";
    }
} 

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */


//Time 0(N) Space 0(N) 
/*class TimeMap {

    *//** Initialize your data structure here. *//*
	List<Pair<Integer, Pair<String, String>>> list;
    public TimeMap() {
        list = new ArrayList<>();
    }
    
    public void set(String key, String value, int timestamp) {
    	list.add(new Pair<Integer, Pair<String,String>>(timestamp, new Pair<>(key, value)));
    }
    
    public String get(String key, int timestamp) {
    	for(int i = list.size()-1;i >= 0;i--) {
    		Pair<Integer, Pair<String, String>> pair = list.get(i);
    		if(pair.getValue().getKey().compareTo(key) == 0 && pair.getKey() <= timestamp)
    			return pair.getValue().getValue();
    	}
    	return "";
    }
}*/
	

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

public class Main {
	public static void main(String[] args) {
		TimeMap obj = new TimeMap();
		obj.set("ctondw","ztpearaw",1);
		obj.set("vrobykydll","hwliiq",2);
		obj.set("gszaw","ztpearaw",3);
		obj.set("ctondw","gszaw",4);
		
		System.out.println(obj.get("gszaw",5));
		System.out.println(obj.get("ctondw",6));
		System.out.println(obj.get("ctondw",7));
		System.out.println(obj.get("gszaw",8));
		System.out.println(obj.get("vrobykydll",9));
		System.out.println(obj.get("ctondw",10));
		
		obj.set("vrobykydll","kcvcjzzwx",11);
		
		System.out.println(obj.get("vrobykydll",12));
		System.out.println(obj.get("ctondw",13));
		System.out.println(obj.get("vrobykydll",14));
		
		obj.set("ztpearaw","zondoubtib",15);
		obj.set("kcvcjzzwx","hwliiq",16);
		obj.set("wtgbfvg","vrobykydll",17);
		obj.set("hwliiq","gzsiivks",18);
		
		System.out.println(obj.get("kcvcjzzwx",19));
		System.out.println(obj.get("ztpearaw",20));		
	}
}