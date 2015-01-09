import java.util.Comparator;

class EdgeComparator implements Comparator<Edge> {

	public int compare(Edge e1, Edge e2)
	{
		return Integer.compare(e1.weight, e2.weight);
	}
}
