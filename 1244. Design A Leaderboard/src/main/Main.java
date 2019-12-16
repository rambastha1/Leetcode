package main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. 
If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0. It is guaranteed that the player 
was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.

 

Example 1:

Input: 
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output: 
[null,null,null,null,null,null,73,null,null,null,141]

Explanation: 
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 

Constraints:

1 <= playerId, K <= 10000
It's guaranteed that K is less than or equal to the current number of players.
1 <= score <= 100
There will be at most 1000 function calls.
 * 
 * https://leetcode.com/problems/design-a-leaderboard/discuss/418833/Java-TreeMap-%2B-Map-Solution
 */

class Leaderboard {
	
	// player ID -> score
	Map<Integer, Integer> player;
	
	// score -> count
	TreeMap<Integer, Integer> score;
	
    public Leaderboard() {
        player = new HashMap<>();
        score = new TreeMap<>(Collections.reverseOrder());
    }
    
    public void addScore(int playerId, int score) {
    	if(!player.containsKey(playerId)) {
    		player.put(playerId, score);
    		this.score.put(score, this.score.getOrDefault(score, 0) + 1);
    	} else {
    		int oldscore = player.get(playerId);
    		this.score.put(oldscore, this.score.get(oldscore)-1);
    		if(this.score.get(oldscore) == 0)
    			this.score.remove(oldscore);
    		int newscore = oldscore + score;
    		player.put(playerId, newscore);
    		this.score.put(newscore, this.score.getOrDefault(newscore, 0) + 1);
    	}
    }
    
    public int top(int K) {
    	int sum = 0;
    	int i = 0;
    	for(int score : this.score.keySet()) {
    		int count = this.score.get(score);
    		for(int j = 0;j < count && i < K;j++,i++) {
    			sum += score;
    		}
    		if(i == K)
    			break;
    	}
    	return sum;
    }
    
    public void reset(int playerId) {
    	int score = player.get(playerId);
    	this.score.put(score, this.score.get(score)-1);
    	if(this.score.get(score) == 0)
    		this.score.remove(score);
    	player.remove(playerId);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */

public class Main {
	public static void main(String[] args) {
		Leaderboard obj = new Leaderboard();
		obj.addScore(1, 73);
		obj.addScore(2, 56);
		obj.addScore(3, 39);
		obj.addScore(4, 51);
		obj.addScore(5, 4);
		System.out.println(obj.top(1));
		obj.reset(1);
		obj.reset(2);
		obj.addScore(2, 51);
		System.out.println(obj.top(3));
	}
}
