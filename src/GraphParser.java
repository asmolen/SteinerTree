import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GraphParser {
	
	private List<Integer> terminals = new ArrayList<Integer>();
	public GraphParser() {
		
	}
	
	public Graph getGraphFromFile(String file) {
		final int COMMENT_CHAR = '#';
		Scanner input;
		String line = "";
		int pos_comment;
		Graph graph;
		
		try {
			input = new Scanner(new File(file));
		
			// getting number of nodes
			while (input.hasNextLine() && line.isEmpty())
			{
				line = input.nextLine();
				pos_comment = line.indexOf(COMMENT_CHAR);
				line = line.substring(0, pos_comment).trim();
			}
		
			graph = new Graph(Integer.parseInt(line));
			
			line = "";
			// add list of terminals
			while (input.hasNextLine() && line.isEmpty())
			{
				line = input.nextLine();
				pos_comment = line.indexOf(COMMENT_CHAR);
				line = line.substring(0, pos_comment).trim();
				String[] tab = line.split(",");
				for (int i = 0; i < tab.length; i++) {
					terminals.add(Integer.parseInt(tab[i]) - 1);
				}
			}
		
			// getting list of edges
			while (input.hasNextLine())
			{
				line = "";
				while (input.hasNextLine() && line.isEmpty())
				{
					line = input.nextLine();
					pos_comment = line.indexOf(COMMENT_CHAR);
					if (pos_comment != -1)
					{
						line = line.substring(0, pos_comment).trim();
					}
				}
				
				if (line.isEmpty())
				{
					break;
				}
			
				String[] tab = line.split(" ");
			
				graph.addEdge(Integer.parseInt(tab[0]) - 1, Integer.parseInt(tab[1]) - 1, Integer.parseInt(tab[2]));
			
			}
			input.close();
		
			return graph;
		}
		catch (FileNotFoundException e) {
			e.getMessage();
			return null;
		}
	}
	
	public void saveGraphToFile(Graph graph, String file, List<Integer> terminals) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
		
		writer.println(graph.getWeight()+" #waga drzewa");
		writer.println(graph.getNodes().size()+" #liczba wierzcholkow");
		for (int i = 0; i < terminals.size(); i++) 
			writer.print(terminals.get(i)+1 + ", ");
		writer.println(" #terminale");
		for (int i = 0; i < graph.getNodesNumber(); i++) {
			for (int j = 0; j < graph.getNeightbours(i).size(); j++){
				writer.println(i+1 + " " + (graph.getNeightbours(i).get(j).getKey()+1) + " " + graph.getNeightbours(i).get(j).getValue());
			}
		}
		writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Integer> getTerminals(String file) {
		return terminals;
	}

}
