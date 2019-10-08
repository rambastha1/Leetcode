package main;

/*
 * https://www.geeksforgeeks.org/design-a-hit-counter/
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls 
 * are being made to the system in chronological order (ie, the timestamp is monotonically increasing). 
 * You may assume that the earliest timestamp starts at 1.
 * It is possible that several hits arrive roughly at the same time.
 * 
 * Example:
   HitCounter counter = new HitCounter();

   // hit at timestamp 1.
   counter.hit(1);

   // hit at timestamp 2.
   counter.hit(2);

	// hit at timestamp 3.
	counter.hit(3);

	// get hits at timestamp 4, should return 3.
	counter.getHits(4);
	
	// hit at timestamp 300.
	counter.hit(300);
	
	// get hits at timestamp 300, should return 4.
	counter.getHits(300);
	
	// get hits at timestamp 301, should return 3.
	counter.getHits(301); 
	Follow up:
	What if the number of hits per second could be very large? Does your design scale?
 */

//Time 0(300) = 0(1)
class HitCounter {
	int []timestamp, hit;
	
	public HitCounter() {
		this.timestamp = new int[300];
		this.hit = new int[300];
	}
	
	void hit(int timestamp) {
		int index = timestamp % 300;
		if(this.timestamp[index] != timestamp) {
			this.timestamp[index] = timestamp;
			hit[index] = 1;
		} else
			hit[index]++;
	}
	
	int getHits(int timestamp) {
		int count = 0;
		for(int i = 0;i < 300;i++) {
			if(timestamp - this.timestamp[i] < 300)
				count += hit[i];
		} 
		return count;
	}
}

public class Main {
	public static void main(String[] args) {

	}
}