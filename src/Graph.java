import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Graph {

	private List<List<Integer>> adjList;
	private int _nodesNumber;
	
	
	public Graph(int nodesNumber) {
		_nodesNumber = nodesNumber;
		adjList = new ArrayList<List<Integer>>(nodesNumber);
		
		int nodeNr;
		for (nodeNr = 1; nodeNr <= nodesNumber; nodeNr++)
		{
			List<Integer> list = new LinkedList<Integer>();
			list.add(nodeNr);
			adjList.add(list);
		}
	}
	
	public int getNodesNumber() {
		return _nodesNumber;
	}
	
	public void addEdge(Integer firstNode, Integer secondNode) {
		adjList.get(firstNode - 1).add(secondNode);
		adjList.get(secondNode - 1).add(firstNode);
	}
	
	public List<Integer> getNeightbours(Integer node) {
		return adjList.get(node - 1);
	}
}

