import java.util.List;

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
		// TODO
		
		return null;
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
