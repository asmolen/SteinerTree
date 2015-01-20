import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap;

/**
 * Reprezentuje graf w postaci listy sąsiedztwa.
 * @author asmolen
 *
 */
public class Graph {

	private List<List<Map.Entry<Integer, Integer>>> adjList;
	private int _nodesNumber;
	private int maxNodeId;

	/**
	 * Tworzy nowy graf z daną liczbą wierzchołków.
	 * @param nodesNumber liczba wierzchołków
	 */
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
	
	/**
	 * Pobiera maksymalny indeks wierzchołka w grafie.
	 * @return maksymalny indeks
	 */
	public int getMaxNodeId() {
		return maxNodeId;
	}
	
	/**
	 * Dodaje krawędź do grafu. Jeżeli dane są niepoprawne lub krawędź już jest w grafie, metoda zwróci false, w przeciwnym razie - true.
	 * @param firstNode pierwszy wierzchołek
	 * @param secondNode drugi wierzchołek
	 * @param weight waga
	 * @return false lub true
	 */
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
	
	/**
	 * Pobiera list� sąsiadów wybranego wierzchołka.
	 * @param node wierzchołek
	 * @return lista sąsiadów
	 */
	public List<Map.Entry<Integer, Integer>> getNeightbours(Integer node) {
		return adjList.get(node);
	}
}

