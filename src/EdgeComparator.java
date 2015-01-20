import java.util.Comparator;

/**
 * Klasa do porównywania dwóch krawędzi.
 * @author asmolen
 *
 */
class EdgeComparator implements Comparator<Edge> {

	public int compare(Edge e1, Edge e2)
	{
		return Integer.compare(e1.getWeight(), e2.getWeight());
	}
}
