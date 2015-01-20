import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Główny moduł obliczeniowy, zawiera implementację algorytmów.
 * @author asmolen
 *
 */

public class Controler {
	private List<Integer> terminals = new ArrayList<Integer>();
	
	public Controler() {
		
	}
	/**
	 * Wyznacza drzewo Steinera.
	 * @param graph graf wejściowy
	 * @param terminals lista terminali
	 * @return drzewo Steinera
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
		
		v = terminals.get(0);
		visited[v] = true;
		
		MST = new Tree(graph.getNodesNumber());
		
		comparator = new EdgeComparator();
		queue = new PriorityQueue<Edge>(20, comparator);
		
		for (int i = 0; i < graph.getNodesNumber(); i++) {
			addEdgesToQueue(v, graph.getNeightbours(v), queue, visited);
		
			do {
				e = queue.poll();
			} while (visited[e.getV2()]);
			
			System.out.println("v1 " + e.getV1()+" v2 "+ e.getV2() + " e.weight "+e.getWeight());
			MST.addEdge(e.getV1(), e.getV2(), e.getWeight());
			visited[e.getV2()] = true;
			v = e.getV2();
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
		for (int i = 0; i < terminals.size(); i++) {
			int source = terminals.get(i);
			Dijkstra shortestPath = new Dijkstra(graph,
					source);
			// Wyswietla najkrotsze sciezki
			// i je�li sa terminalami to dodaje do grafu
			for (int j = i + 1; j < terminals.size(); j++) {
				int target = terminals.get(j);
				
				System.out.printf("get full %d do %d (%d)  ", source, target,
						shortestPath.getDistanceTo(target));
				full.addEdge(source, target, (int) shortestPath.getDistanceTo(target));
				for (Edge edge : shortestPath.getPathTo(target)) {
					System.out.print(edge);
				}
				System.out.println();
			}
		}
		return full;
	}
	
	private Tree createSteinerTree(Graph graph, Tree tree) {
		Tree steiner = new Tree(graph.getNodesNumber());	
		boolean visited[] = new boolean[graph.getNodesNumber()];
		int nodesNumber = 0;
		
		for (Edge e : tree.getEdges()) {
			int source = e.getV1();
			int target = e.getV2();
			Dijkstra shortestPath = new Dijkstra(graph,	source);
			
			System.out.printf("get steiner %d do %d (%d)  ", source, target,
			shortestPath.getDistanceTo(target));
					
			for (Edge edge : shortestPath.getPathTo(target)) {
				System.out.print(edge);
				steiner.addEdge(edge.getV1(), edge.getV2(), edge.getWeight());
				visited[edge.getV1()] = true;
				visited[edge.getV2()] = true;
			}
			System.out.println();
			
		}
		
		for (boolean i: visited) {
			if (i) nodesNumber++;
		}
		
		steiner.setNodesNumber(nodesNumber);
		System.out.println("waga "+ steiner.getWeight());
		return steiner;
	}
}
