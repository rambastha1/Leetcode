package main;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

class UndergroundSystem {
	
	// id -> {station, time}
	Map<Integer, Pair<String, Integer>> checkin;
	// station1-station2 -> {total time, count}
	Map<String, Pair<Integer, Integer>> checkout;
    public UndergroundSystem() {
        checkin = new HashMap<>();
        checkout = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        Pair<String, Integer> pair = new Pair<String, Integer>(stationName, t);
        checkin.put(id, pair);
    }
    
    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> pair = checkin.get(id);
        String route = pair.getKey() + "-" + stationName;
        int time = t - pair.getValue();
        Pair<Integer, Integer> temp = checkout.getOrDefault(route, new Pair<>(0,0));
        checkout.put(route, new Pair<>(temp.getKey() + time, temp.getValue() + 1));
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        Pair<Integer, Integer> pair = checkout.getOrDefault(route, new Pair<>(0,0));
        return (pair.getKey() * 1.0) / pair.getValue(); 
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */

public class Main {
	public static void main(String[] args) {

	}
}
