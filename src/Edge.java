
/**
 * Reprezentuje krawędź.
 * @author asmolen
 *
 */
public class Edge {
	
	private int v1;
	private int v2;
	private int weight;
	
	
	public Edge(int _v1, int _v2, int _weight)
	{
		v1 = _v1;
		v2 = _v2;
		weight = _weight;
	}	
	
	public int getV1() {
		return v1;
	}


	public int getV2() {
		return v2;
	}


	public int getWeight() {
		return weight;
	}
	@Override
	public String toString() {
		return String.format("%d->%d (%d) ", v1, v2, weight);
	}
}
