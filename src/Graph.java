import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap;

public class Graph {

	private List<List<Map.Entry<Integer, Integer>>> adjList;
	private int _nodesNumber;
	
	
	public Graph(int nodesNumber) {
		_nodesNumber = nodesNumber;
		adjList = new ArrayList<List<Map.Entry<Integer, Integer>>>(nodesNumber);
		
		int nodeNr;
		for (nodeNr = 1; nodeNr <= nodesNumber; nodeNr++)
		{
			List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>();			
			list.add(new AbstractMap.SimpleEntry<>(nodeNr, 0));
			adjList.add(list);
		}
	}
	
	public int getNodesNumber() {
		return _nodesNumber;
	}
	
	public void addEdge(Integer firstNode, Integer secondNode, Integer weight) {
		adjList.get(firstNode - 1).add(new AbstractMap.SimpleEntry<>(secondNode, weight));
		adjList.get(secondNode - 1).add(new AbstractMap.SimpleEntry<>(firstNode, weight));
	}
	
	public List<Map.Entry<Integer, Integer>> getNeightbours(Integer node) {
		return adjList.get(node - 1);
	}
}

