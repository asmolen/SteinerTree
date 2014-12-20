import java.util.List;
public class Controler {

	public Controler() {
		
	}
	
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
