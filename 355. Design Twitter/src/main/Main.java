package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// https://leetcode.com/problems/design-twitter/discuss/82935/Java-OOD-solution-with-detailed-explanation
class Twitter {

	class Tweet {
		int tweet_id, time;
		public Tweet(int tweet_id, int time) {
			this.tweet_id = tweet_id;
			this.time = time;
		}
	}
	// for all tweets
	private static int timestamp;
	private int maxfeed;
	Map<Integer, Set<Integer>> followees;
	Map<Integer, List<Tweet>> tweets;
	
	/** Initialize your data structure here. */
	public Twitter() {
		this.timestamp = 0;
		this.maxfeed = 10;
		followees = new HashMap<>();
		tweets = new HashMap<>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if(!tweets.containsKey(userId)) {
			tweets.put(userId, new ArrayList<>());
			// follow himself
			follow(userId, userId);
		}
		//add new tweet on the first place
		tweets.get(userId).add(0, new Tweet(tweetId, timestamp++));
	}

	/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
	public List<Integer> getNewsFeed(int userId) {
		PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
			@Override
			public int compare(Tweet o1, Tweet o2) {
				return o1.time - o2.time;
			}
		});
		
		// search for tweets in all followees, add if time is more(recent)
		Set<Integer> followees = this.followees.get(userId);
		if(followees != null) {
			for(int fid : followees) {
				List<Tweet> list = tweets.get(fid);
				if(list == null)
					continue;
				for(Tweet t : list) {
					// size < 10
					if(pq.size() < this.maxfeed)
						pq.offer(t);
					else {
						// time less than peek of pq, no need to add
						if(t.time < pq.peek().time)
							break;
						else {
							pq.offer(t);
							// removes oldest tweet
							pq.poll();
						}
					}
				}
			}
		}
		
		List<Integer> res = new ArrayList<>();
		while(!pq.isEmpty()) {
			res.add(0, pq.poll().tweet_id);
		}
		return res;
	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		if(!this.followees.containsKey(followerId))
			this.followees.put(followerId, new HashSet<>());
		this.followees.get(followerId).add(followeeId);
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		// cannot unfollow itself
		if(!this.followees.containsKey(followerId) || followerId == followeeId)
			return;
		this.followees.get(followerId).remove(followeeId);
	}
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

public class Main {
	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		twitter.postTweet(1, 5);
		System.out.println(twitter.getNewsFeed(1));
		twitter.follow(1, 2);
		twitter.postTweet(2, 6);
		System.out.println(twitter.getNewsFeed(1));
		twitter.unfollow(1, 2);
		twitter.getNewsFeed(1);
	}
}
