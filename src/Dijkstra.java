import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

	/**
	 * Reprezentuje dystans do danej krawedzi. Uzywane do przechowywania w
	 * kolejce priorytetowej do wyboru najkrotszej krawedzi.
	 * 
	 * @author mephisto
	 */
	class DistanceToEdge implements Comparable<DistanceToEdge> {
		// krawedz
		private final int edge;
		// odleglosc do tej krawedzi
		private long distance;

		public DistanceToEdge(int edge, long distance) {
			this.edge = edge;
			this.distance = distance;
		}

		public long getDistance() {
			return distance;
		}

		public void setDistance(long distance) {
			this.distance = distance;
		}

		public int getEdge() {
			return edge;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + (int) (distance ^ (distance >>> 32));
			result = prime * result + edge;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DistanceToEdge other = (DistanceToEdge) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (distance != other.distance)
				return false;
			if (edge != other.edge)
				return false;
			return true;
		}

		@Override
		public int compareTo(DistanceToEdge param) {
			int cmp = new Long(distance).compareTo(param.getDistance());

			if (cmp == 0) {
				return new Integer(edge).compareTo(param.getEdge());
			}
			return 0;
		}

		private Dijkstra getOuterType() {
			return Dijkstra.this;
		}
	}

	// ////////////////////////////////////////////////////////////////////
	// przechowuje krawedz z ktorej jest najblizej do biezacej okreslonej
	// indeksem tablicy
	private Edge[] edgeTo;
	// przechowuje najkrotsze znalezione odleglosci do danego wierzcholka
	private Long[] distanceTo;
	// kolejka przechowujaca biezace krawedzie uszeregowane od najkrotszej do
	// najdluzszej
	private Queue<DistanceToEdge> priorityQueue;

	/**
	 * Realizuje algorytm Dijkstry dla danego wierzchołka startowego.
	 * @param graph graf wejściowy
	 * @param source wierzchołek startowy
	 */
	public Dijkstra(Graph graph, int source) {
		// inicjacja wewnetrznych struktur
		edgeTo = new Edge[graph.getNodesNumber()];
		distanceTo = new Long[graph.getNodesNumber()];
		priorityQueue = new PriorityQueue<DistanceToEdge>(
				graph.getNodesNumber());

		// dla kazdego wierzcholka v ustawiamy najwieksza mozliwa wartosc jaka
		// moze przechowywac
		for (int v = 0; v < graph.getNodesNumber(); v++) {
			distanceTo[v] = Long.MAX_VALUE;
		}
		// odleglosc do wierzcholka zrodlowego to 0
		distanceTo[source] = 0L;

		// wstawiamy wierzholek zrodlowy do kolejki, od niego rozpoczynamy
		// poszukiwania
		priorityQueue.offer(new DistanceToEdge(source, 0L));

		// przeprowadzamy relaksacje grafu dopoki kolejka nie jest pusta
		while (!priorityQueue.isEmpty()) {
			relax(graph, priorityQueue.poll().getEdge());
		}

	}

	private void relax(Graph graph, int v) {
		// przegladamy listy sasiedztwa wszystkich wierzcholkow
		
		for (Map.Entry<Integer, Integer> neighbour : graph.getNeightbours(v)) {
			Edge edge = new Edge(v, neighbour.getKey(), neighbour.getValue());
			int w = edge.getV2();

			// sprawdzamy czy zmiana wierzcholka skroci dystans z wierzcholka
			// zrodlowego
			if (distanceTo[w] > distanceTo[v] + edge.getWeight()) {
				distanceTo[w] = distanceTo[v] + edge.getWeight();
				edgeTo[w] = edge;
				DistanceToEdge dte = new DistanceToEdge(w, distanceTo[w]);

				// jesli jest juz krawedz o tej wadze to ja usuwamy, bo
				// znalezlismy lepsza droge
				priorityQueue.remove(dte);
				// wstawiamy nowa krawedz z aktualna znaleziona odlegloscia
				priorityQueue.offer(dte);
			}
		}

	}

	// jesli zwrocona wartosc wynosi Long.MAX_VALUE to oznacza, ze dany
	// wierzcholek jest nieosiagalny ze zrodla
	/**
	 * Pobiera długość scieżki z wierzchołka startowego do wybranego.
	 * @param v wybrany wierzchołek
	 * @return długość ścieżki
	 */
	public long getDistanceTo(int v) {
		return distanceTo[v];
	}

	// jesli nie istnieje sciezka do danego wierzcholka zostanie zwrocona pusta
	// kolekcja
	/**
	 * Pobiera najkrótszą ścieżkę z wierzchołka startowego do wybranego.
	 * @param v wybrany wierzchołek
	 * @return lista krawędzi tworząca najkrótszą ścieżkę
	 */
	public Iterable<Edge> getPathTo(int v) {
		Deque<Edge> path = new ArrayDeque<Edge>();
		
		// dopoki istnieje krawedz dodawaj ja do stosu
		// krawedz zrodlowa jest oznaczona jako null
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.getV1()]) {
			path.push(e);
		}
		return path;
	}
}