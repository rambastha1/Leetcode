package main;
import java.util.ArrayList;
import java.util.List;

class Solution {
    class Node {
		int time, amount;
		int index;
		String city, name;
		public Node(String name, int time, int amount, String city, int index) {
			this.name = name;
			this.time = time;
			this.amount = amount;
			this.city = city;
			this.index = index;
		}
	}
	
	public List<String> invalidTransactions(String[] transactions) {
		List<String> res = new ArrayList<>();
		List<Node> list = new ArrayList<>();
		
		for(int i = 0;i < transactions.length;i++) {
			String []arr = transactions[i].split(",");
			int time = Integer.parseInt(arr[1]);
			int amount = Integer.parseInt(arr[2]);
			list.add(new Node(arr[0], time, amount, arr[3], i)); 
		}
		
		int n = list.size();
		// great technique of using boolean array
		boolean []add = new boolean[n];
		for(int i = 0;i < n;i++) {
			Node n1 = list.get(i);
			if(n1.amount > 1000)
				add[i] = true;
			for(int j = i+1;j < n;j++) {
				Node n2 = list.get(j);
				if(n1.name.compareTo(n2.name)==0 && Math.abs(n1.time-n2.time) <= 60 && n1.city.compareTo(n2.city) != 0) {
					add[i] = true;
					add[j] = true;
				}
			}
		}
		
		for(int i = 0;i < n;i++) {
			if(add[i]) {
				Node node = list.get(i);
				String str = node.name + "," + node.time + "," + node.amount + "," + node.city;
				res.add(str);
			}
		}
		return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String []transactions = {"alice,20,800,mtv","bob,50,1200,mtv"};
		System.out.println(new Solution().invalidTransactions(transactions));
	}
}
