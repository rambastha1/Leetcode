package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// https://www.geeksforgeeks.org/clone-an-undirected-graph/
// https://www.geeksforgeeks.org/clone-an-undirected-graph/
// https://www.geeksforgeeks.org/clone-an-undirected-graph-with-multiple-connected-components/

class Solution {
	class Node {
		public int val;
		public List<Node> neighbors;
		public Node() {}
		public Node(int _val,List<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	public Node cloneGraph(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		
		Map<Node, Node> map = new HashMap<>();
		map.put(node, new Node(node.val, new ArrayList<>()));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			Node clone = map.get(n);
			if(n.neighbors != null) {
				List<Node> neighbour = n.neighbors;
				for(Node neigh : neighbour) {
					Node clone_neigh = map.get(neigh);
					if(clone_neigh == null) {
						q.offer(neigh);
						clone_neigh = new Node(neigh.val, new ArrayList<>());
						map.put(neigh, clone_neigh);
					}
					clone.neighbors.add(clone_neigh);
				}
			}
		}
		return map.get(node);
	}
}

public class Main {
	public static void main(String[] args) {

	}
}
