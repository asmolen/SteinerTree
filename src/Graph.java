import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap;

public class Graph {

	private List<List<Map.Entry<Integer, Integer>>> adjList;
	private int _nodesNumber;
	private int maxNodeId;
		
	public Graph(int nodesNumber) {
		_nodesNumber = nodesNumber;
		maxNodeId = nodesNumber;
		adjList = new ArrayList<List<Map.Entry<Integer, Integer>>>(nodesNumber);
		int nodeNr;
		for (nodeNr = 0; nodeNr < nodesNumber; nodeNr++)
		{
			List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>();			
		//	list.add(new AbstractMap.SimpleEntry<>(nodeNr, 0));
			adjList.add(list);
		}
	}
	
	public int getNodesNumber() {
		return _nodesNumber;
	}
	
	public void setNodesNumber(int nodesNumber) {
		_nodesNumber = nodesNumber;
	}
	
	
	public int getMaxNodeId() {
		return maxNodeId;
	}
	
	public boolean addEdge(Integer firstNode, Integer secondNode, Integer weight) {
		if (firstNode < 0 || secondNode < 0 || weight < 0 || firstNode >= _nodesNumber || secondNode >= _nodesNumber) {
			return false;
		}
		
		if (adjList.get(firstNode).contains(new AbstractMap.SimpleEntry<>(secondNode, weight)))
			return false;		
		adjList.get(firstNode).add(new AbstractMap.SimpleEntry<>(secondNode, weight));
		adjList.get(secondNode).add(new AbstractMap.SimpleEntry<>(firstNode, weight));		
		return true;
	}
	
	public List<Map.Entry<Integer, Integer>> getNeightbours(Integer node) {
		return adjList.get(node);
	}
}

