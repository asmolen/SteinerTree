import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap;

public class Graph {

	private List<List<Map.Entry<Integer, Integer>>> adjList;
	private int _nodesNumber;
	private int _edgeNumber;
	private int _weight;
	
	// listy sasiedztwa
	private List<Edge>[] neighborhoodLists;
	private List<Integer> _nodes = new ArrayList<Integer>();
	
	@SuppressWarnings("unchecked")
	public Graph(int nodesNumber) {
		_nodesNumber = nodesNumber;
		_edgeNumber = 0;
		_weight = 0;
		adjList = new ArrayList<List<Map.Entry<Integer, Integer>>>(nodesNumber);
		neighborhoodLists = (List<Edge>[]) new List[nodesNumber];
		for (int i = 0; i < nodesNumber; i++) {
			neighborhoodLists[i] = new ArrayList<Edge>();
		}
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
	
	public void addEdge(Integer firstNode, Integer secondNode, Integer weight) {
		adjList.get(firstNode).add(new AbstractMap.SimpleEntry<>(secondNode, weight));
		adjList.get(secondNode).add(new AbstractMap.SimpleEntry<>(firstNode, weight));
		neighborhoodLists[firstNode].add(new Edge(firstNode, secondNode, weight));
		neighborhoodLists[secondNode].add(new Edge(secondNode, firstNode, weight));
		_edgeNumber++;
		_weight += weight;
		if(!_nodes.contains(firstNode))
			_nodes.add(firstNode);
		if(!_nodes.contains(secondNode))
			_nodes.add(secondNode);
	}
	
	public void addEdgeTree(Integer firstNode, Integer secondNode, Integer weight) {
		adjList.get(firstNode).add(new AbstractMap.SimpleEntry<>(secondNode, weight));
		neighborhoodLists[firstNode].add(new Edge(firstNode, secondNode, weight));
		_edgeNumber++;
		_weight += weight;
		if(!_nodes.contains(firstNode))
			_nodes.add(firstNode);
		if(!_nodes.contains(secondNode))
			_nodes.add(secondNode);
	}
	
	public List<Integer> getNodes() {
		return _nodes;
	}

	public Iterable<Edge> getNeighborhoodList(int v) {
		return neighborhoodLists[v];
	}
	
	public List<Map.Entry<Integer, Integer>> getNeightbours(Integer node) {
		return adjList.get(node);
	}

	public int getVerticesNumber() {
		return _edgeNumber;
	}

	public List<Edge>[] getNeighborhoodLists() {
		return neighborhoodLists;
	}

	public int getWeight() {
		return _weight;
	}


}

