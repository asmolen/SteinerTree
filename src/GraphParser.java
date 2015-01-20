import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Moduł do parsowania pliku z grafem wejściowym.
 * @author asmolen
 *
 */
public class GraphParser {
	
	private List<Integer> terminals = new ArrayList<Integer>();
	public GraphParser() {
		
	}
	/**
	 * Wczytuje graf z pliku do pamięci.
	 * @param file ścieżka do pliku
	 * @return graf w postaci listy sąsiedztwa
	 */
	public Graph getGraphFromFile(String file) {
		final int COMMENT_CHAR = '#';
		Scanner input;
		String line = "";
		int pos_comment;
		Graph graph;
		int line_nr = 1;
		
		try {
			input = new Scanner(new File(file));
		
			// getting number of nodes
			while (input.hasNextLine() && line.isEmpty())
			{
				line = input.nextLine().trim();
				pos_comment = line.indexOf(COMMENT_CHAR);
				if (pos_comment != -1)
				{
					line = line.substring(0, pos_comment).trim();
				}
				line_nr++;
			}
			line_nr--;
			graph = new Graph(Integer.parseInt(line));
			line_nr++;
			
			line = "";
			// add list of terminals
			while (input.hasNextLine() && line.isEmpty())
			{
				line = input.nextLine().trim();
				pos_comment = line.indexOf(COMMENT_CHAR);
				if (pos_comment != -1)
				{
					line = line.substring(0, pos_comment).trim();
				}
				String[] tab = line.split(",");
				for (int i = 0; i < tab.length; i++) {
					terminals.add(Integer.parseInt(tab[i].trim()) - 1);
				}
				line_nr++;
			}
		
			// getting list of edges
			while (input.hasNextLine())
			{
				line = "";
				while (input.hasNextLine() && line.isEmpty())
				{
					line = input.nextLine().trim();
					pos_comment = line.indexOf(COMMENT_CHAR);
					if (pos_comment != -1)
					{
						line = line.substring(0, pos_comment).trim();
					}
					line_nr++;
				}
				line_nr--;
				
				if (line.isEmpty())
				{
					break;
				}
			
				String[] tab = line.split(" ");
				
				if (tab.length != 3) {
					System.out.println("Parse error: " + "Nieprawidłowa liczba elementów w linii " + line_nr + ", " + tab.length);
					input.close();
					return null;
				}
			
				if (!graph.addEdge(Integer.parseInt(tab[0].trim()) - 1, Integer.parseInt(tab[1].trim()) - 1, Integer.parseInt(tab[2].trim()))) {
					System.out.println("Nieprawidłowe dane wejściowe" + " w linii " + line_nr);
					input.close();
					return null;
				}
				line_nr++;
			}
			input.close();
		
			return graph;
		}
		catch (FileNotFoundException e) {
			//e.getMessage();
			System.out.println(e.getMessage());
			return null;
		}
		catch (NumberFormatException e) {
			System.out.println("Parse error: " + e.getMessage() + " w linii " + line_nr);
			return null;
		}
	}
	
	/**
	 * Zapisuje drzewo steinera do pliku.
	 * @param tree drzewo steinera
	 * @param file ścieżka do pliku
	 * @param terminals lista terminali
	 */
	public void saveTreeToFile(Tree tree, String file, List<Integer> terminals) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
		
		writer.println(tree.getWeight()+" #waga drzewa");
		writer.println(tree.getNodesNumber() + " #liczba wierzcholkow");
		for (int i = 0; i < terminals.size(); i++) 
			writer.print(terminals.get(i)+1 + ", ");
		writer.println(" #terminale");
		for (int i = 0; i < tree.getMaxNodeId(); i++) {
			for (int j = 0; j < tree.getNeightbours(i).size(); j++){
				if (i < tree.getNeightbours(i).get(j).getKey()) {
					writer.println(i+1 + " " + (tree.getNeightbours(i).get(j).getKey()+1) + " " + tree.getNeightbours(i).get(j).getValue());
				}
			}
		}
		writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Pobiera listę terminali
	 * @param file ścieżka do pliku
	 * @return lista terminali
	 */
	public List<Integer> getTerminals(String file) {
		return terminals;
	}

}
