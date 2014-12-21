import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GraphParser {
	
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
		
			graph = new Graph(Integer.parseUnsignedInt(line));
			
			line = "";
			// omitted list of terminals
			while (input.hasNextLine() && line.isEmpty())
			{
				line = input.nextLine();
				pos_comment = line.indexOf(COMMENT_CHAR);
				line = line.substring(0, pos_comment).trim();
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
			
				graph.addEdge(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), Integer.parseInt(tab[2]));
			
			}
			input.close();
		
			return graph;
		}
		catch (FileNotFoundException e) {
			e.getMessage();
			return null;
		}
	}
	
	public void saveGraphToFile(Graph graph, String file) {
		// TODO
	}
	
	public List<Integer> getTerminals(String file) {
		// TODO
		
		return null;
	}

}
