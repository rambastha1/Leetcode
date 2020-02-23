package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
	
	class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	};
	
	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null)
			return res;
		return bfs(res, root);
	}
	
	private List<List<Integer>> bfs(List<List<Integer>> res, Node root) {
		Queue<Node> q =  new LinkedList<>();
		q.offer(root);
		int level = 0;
		while(!q.isEmpty()) {
			List<Integer> list = new ArrayList<>();
			int size = q.size();
			for(int i = 0;i < size;i++) {
				Node node = q.poll();
				list.add(node.val);
				for(Node child : node.children)
					q.offer(child);
			}
			res.add(list);
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {

	}
}
