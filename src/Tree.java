import java.util.List;
import java.util.ArrayList;

public class Tree extends Graph {

	private List<Edge> edgesList;
	private int weightTotal;
	
	public Tree(int nodesNumber) {
		super(nodesNumber);
		weightTotal = 0;
		edgesList = new ArrayList<Edge>();
	}
	
	public boolean addEdge(Integer firstNode, Integer secondNode, Integer weight) {
		if (!super.addEdge(firstNode, secondNode, weight)) 
			return false;
		weightTotal += weight;
		edgesList.add(new Edge(firstNode, secondNode, weight));
		return true;
	}
	
	public List<Edge> getEdges() {
		return edgesList;
	}
	
	public int getWeight() {
		return weightTotal;
	}
	
	public int getEdgesNumber() {
		return edgesList.size();
	}
}
