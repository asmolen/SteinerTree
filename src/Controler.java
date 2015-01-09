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

	public Controler() {
		
	}
	/**
	 * Solves Steiner tree problem
	 * @param graph input graph
	 * @param terminals set of terminals
	 * @return Steiner tree
	 */
	public Graph getSteinerTree(Graph graph, List<Integer> terminals) {
		// TODO
		
		if (graph.getNodesNumber() == terminals.size())
		{
			return getMST(graph);
		}
		
		Graph gL = getMetricClosure(graph);
		
		Graph T = getMST(gL);
		
		return createSteinerTree(graph, T);
	}
	
	private Graph getMST(Graph graph) {
		
		Comparator<Edge> comparator;
		Queue<Edge> queue;
		Graph MST;
		boolean[] visited;
		int v;
		Edge e;
		
		visited = new boolean[graph.getNodesNumber()];
		for (boolean i : visited)
		{
			i = false;
		}
		
		v = 1;
		visited[v-1] = true;
		
		MST = new Graph(graph.getNodesNumber());
		
		comparator = new EdgeComparator();
		queue = new PriorityQueue<Edge>(20, comparator);
		
		for (int i = 1; i < graph.getNodesNumber(); i++)
		{
			addEdgesToQueue(v, graph.getNeightbours(v), queue, visited);
			
			do
			{
				e = queue.poll();
			} while (visited[e.v2-1]);
			
			MST.addEdge(e.v1, e.v2, e.weight);
			visited[e.v2-1] = true;
			v = e.v2;
		}
		
		return MST;
	}
	
	private void addEdgesToQueue(int v, List<Map.Entry<Integer, Integer>> listNodes, Queue<Edge> queue, boolean[] visited)
	{
		for (Map.Entry<Integer, Integer> n : listNodes)
		{
			if (!visited[n.getKey()-1])
			{
				queue.offer(new Edge(v, n.getKey(), n.getValue()));
			}
		}
	}
	
	private Graph getMetricClosure(Graph graph) {
		// TODO
		
		return null;
	}
	
	private Graph createSteinerTree(Graph graph, Graph tree) {
		// TODO
		
		return null;
	}
}
