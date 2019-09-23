package main;

import java.util.HashMap;
import java.util.Map;

class TopVotedCandidate {

	int []leader, persons, times;
	Map<Integer, Integer> map;
    public TopVotedCandidate(int[] persons, int[] times) {
    	this.persons = persons;
    	this.times = times;
    	int n = persons.length;
    	leader = new int[n];
    	leader[0] = persons[0];
    	if(n == 1)
    		return;
        leader[1] = persons[1];
        if(n == 2)
        	return;
        
        map = new HashMap<>();
        map.put(persons[0], map.getOrDefault(persons[0], 0) + 1);
        map.put(persons[1], map.getOrDefault(persons[1], 0) + 1);
        for(int i = 2;i < n;i++) {
        	map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
        	leader[i] = (persons[i] == leader[i-1])?persons[i]:
        		(map.get(leader[i-1]) > map.get(persons[i]))?leader[i-1]:persons[i]; 
        }
        print();
    }
    
    void print() {
    	for(int i : leader)
    		System.out.print(i + " ");
    	System.out.println();
    }
    
    public int q(int t) {
    	if(leader.length == 1)
    		return persons[0];
    	
    	int l = -1, r = leader.length-1;
    	while(r-l>1) {
    		int m = (l+r)/2;
    		if(times[m] < t)
    			l = m;
    		else
    			r = m;
    	}
    	//System.out.println("r:" + r);
    	if(times[r] > t)
    		return leader[r-1];
    	return leader[r];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */

public class Main {
	public static void main(String[] args) {
		
		/*int []persons = {0,1,1,0,0,1,0}, times = {0,5,10,15,20,25,30};
		TopVotedCandidate obj = new TopVotedCandidate(persons, times);
		System.out.print(obj.q(3) + " ");
		System.out.print(obj.q(12) + " ");
		System.out.print(obj.q(25) + " ");
		System.out.print(obj.q(15) + " ");
		System.out.print(obj.q(324) + " ");
		System.out.print(obj.q(8) + " ");*/		
		
		
		int []persons = {0,0,0,0,1}, times = {0,6,39,52,75};
		TopVotedCandidate obj = new TopVotedCandidate(persons, times);
		System.out.print(obj.q(45) + " ");
		System.out.print(obj.q(49) + " ");
		System.out.print(obj.q(59) + " ");
		System.out.print(obj.q(68) + " ");
		System.out.print(obj.q(37) + " ");
		System.out.print(obj.q(99) + " ");
		System.out.print(obj.q(26) + " ");
		System.out.print(obj.q(78) + " ");
		System.out.print(obj.q(43) + " ");				
		
	}
}