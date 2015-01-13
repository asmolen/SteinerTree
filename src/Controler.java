import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Main calculation module. It contains all necessary algorithms.
 * @author asmolen
 *
 */

public class Controler {
	private List<Integer> terminals = new ArrayList<Integer>();;
	
	public Controler() {
		
	}
	/**
	 * Solves Steiner tree problem
	 * @param graph input graph
	 * @param terminals set of terminals
	 * @return Steiner tree
	 */
	public Tree getSteinerTree(Graph graph, List<Integer> terminals) {
		
		if (!isConnected(graph)) {
			System.out.println("Graf nie jest spójny!");
			return null;
		}
		
		this.terminals = terminals;
		if (graph.getNodesNumber() == terminals.size())
		{
			return getMST(graph);
		}
		
		Graph gL = getMetricClosure(graph);
		
		Tree MST = getMST(gL);
		
		return createSteinerTree(graph, MST);
	}
	
	private boolean isConnected(Graph graph) {
		boolean [] visited = new boolean [graph.getNodesNumber()];
		DFS(0, graph, visited);
		for (boolean i : visited) {
			if (!i)
				return false;
		}
		return true;
	}
	
	private void DFS(int v, Graph graph, boolean[] visited) {
		visited[v] = true;
		for (Map.Entry<Integer, Integer> neighbour : graph.getNeightbours(v)) {
			if (!visited[neighbour.getKey()])
				DFS(neighbour.getKey(), graph, visited);
		}
	}
	
	private Tree getMST(Graph graph) {		
		Comparator<Edge> comparator;
		Queue<Edge> queue;
		Tree MST;
		boolean[] visited;
		int v;
		Edge e;
		
		visited = new boolean[graph.getNodesNumber()];
		System.out.println("getnode ty" + graph.getNodesNumber());
		
		v = getTerminals().get(0);
		visited[v] = true;
		
		MST = new Tree(graph.getNodesNumber());
		
		comparator = new EdgeComparator();
		queue = new PriorityQueue<Edge>(20, comparator);
		
		for (int i = 0; i < graph.getNodesNumber(); i++) {
			addEdgesToQueue(v, graph.getNeightbours(v), queue, visited);
		
			do {
				e = queue.poll();
			} while (visited[e.v2]);
			
			System.out.println("e.v1 " + e.v1+" e.v2 "+ e.v2 + " e.weight "+e.weight);
			MST.addEdge(e.v1, e.v2, e.weight);
			visited[e.v2] = true;
			v = e.v2;
			if(	MST.getEdgesNumber() == terminals.size() - 1)
				return MST;		
		}
		
		return MST;
	}
	
	private void addEdgesToQueue(int v, List<Map.Entry<Integer, Integer>> listNodes, Queue<Edge> queue, boolean[] visited) {
		for (Map.Entry<Integer, Integer> n : listNodes)
		{
			if (!visited[n.getKey()])
			{
				queue.offer(new Edge(v, n.getKey(), n.getValue()));
			}
		}
	}
	
	private Graph getMetricClosure(Graph graph) {
		Graph full = new Graph(graph.getNodesNumber());
		for (int i = 0; i < getTerminals().size(); i++) {
			int source = getTerminals().get(i);
			Dijkstra shortestPath = new Dijkstra(graph,
					source);
			// Wyswietla najkrotsze sciezki
			// i jeœli sa terminalami to dodaje do grafu
			for (int j = i + 1; j < getTerminals().size(); j++) {
				int target = getTerminals().get(j);
				if (shortestPath.hasPathTo(target)) {
					System.out.printf("get full %d do %d (%d)  ", source, target,
							shortestPath.getDistanceTo(target));
					full.addEdge(source, target, (int) shortestPath.getDistanceTo(target));
					if (shortestPath.hasPathTo(target)) {
						for (Edge edge : shortestPath.getPathTo(target)) {
							System.out.print(edge);
						}
						System.out.println();
					}
				}
			}
		}
		return full;
	}
	
	private Tree createSteinerTree(Graph graph, Tree tree) {
		Tree steiner = new Tree(graph.getNodesNumber());	
		boolean visited[] = new boolean[graph.getNodesNumber()];
		int nodesNumber = 0;
		
		for (Edge e : tree.getEdges()) {
			int source = e.v1;
			int target = e.v2;
			Dijkstra shortestPath = new Dijkstra(graph,	source);
			
			System.out.printf("get steiner %d do %d (%d)  ", source, target,
			shortestPath.getDistanceTo(target));
					
			if (shortestPath.hasPathTo(target)) {
				for (Edge edge : shortestPath.getPathTo(target)) {
					System.out.print(edge);
					steiner.addEdge(edge.getV1(), edge.getV2(), edge.getWeight());
					visited[edge.getV1()] = true;
					visited[edge.getV2()] = true;
				}
				System.out.println();
			}
		}
		
		for (boolean i: visited) {
			if (i) nodesNumber++;
		}
		
		steiner.setNodesNumber(nodesNumber);
		System.out.println("waga "+ steiner.getWeight());
		return steiner;
	}
	
	public List<Integer> getTerminals() {
		return terminals;
	}
}
