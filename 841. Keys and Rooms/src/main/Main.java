package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    	int count = 0;
    	boolean []visited = new boolean[rooms.size()];
    	visited[0] = true;
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(0);
    	count++;
    	
    	while(!q.isEmpty()) {
    		int room = q.poll();
    		for(int i = 0;i < rooms.get(room).size();i++) {
    			int temp = rooms.get(room).get(i);
    			if(!visited[temp]) {
    				visited[temp] = true;
    				q.offer(temp);
    				count++;
    			}
    		}
    	}
    	return (count>=rooms.size())?true:false;
    }
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> rooms = new ArrayList<>();
		List<Integer> l1 = Arrays.asList(1,3);
		List<Integer> l2 = Arrays.asList(3,0,1);
		List<Integer> l3 = Arrays.asList(2);
		List<Integer> l4 = Arrays.asList(0);
		rooms.add(l1);
		rooms.add(l2);
		rooms.add(l3);
		rooms.add(l4);
		System.out.println(new Solution().canVisitAllRooms(rooms));
	}
}