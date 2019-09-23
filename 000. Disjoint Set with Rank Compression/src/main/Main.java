package main;

class Graph {
	
	class Edge {
		int src, dest;
	}
	
	class Subset {
		int rank, parent;
	}
	
	int V, E;
	Edge []edge;
	
	public Graph(int V, int E) {
		this.V = V;
		this.E = E;
		edge = new Edge[E];
		for(int i = 0;i < E;i++)
			edge[i] = new Edge();
	}
	
	int find(Subset []subset, int i) {
		if(subset[i].parent != i)
			subset[i].parent = find(subset, subset[i].parent);
		return subset[i].parent;
	}
	
	void union(Subset []subset, int x, int y) {
		int a = find(subset, x);
		int b = find(subset, y);
		if(subset[a].rank > subset[b].rank) {
			subset[b].parent = a;
		} else {
			subset[a].parent = b;
			subset[b].rank++;
		}
	}
	
	boolean iscycle(Graph graph) {
		Subset []subset = new Subset[V];
		
		for(int i = 0;i < subset.length;i++) {
			subset[i] = new Subset();
		}
		
		for(int i = 0;i < subset.length;i++) {
			subset[i].rank = 0;
			subset[i].parent = i;
		}
		
		for(int e = 0;e < E;e++) {
			int x = find(subset, graph.edge[e].src);
			int y = find(subset, graph.edge[e].dest);
			if(x == y)
				return true;
			union(subset, x, y);
		}
		return false;
	}
}


public class Main {
	public static void main(String[] args) {
		int V = 3, E = 3;
		Graph graph = new Graph(V, E);
		graph.edge[0].src = 0;
		graph.edge[0].dest = 0;
		
		graph.edge[1].src = 1;
		graph.edge[1].dest = 2;
		
		graph.edge[2].src = 0;
		graph.edge[2].dest = 2;
		
		System.out.println(graph.iscycle(graph));
	}
}