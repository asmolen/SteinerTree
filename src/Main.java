import java.util.Scanner;
import java.util.List;

/**
 * Główna klasa programu.
 * @author asmolen
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input;
		Graph inputGraph;
		Tree steinerTree;
		Controler controler;
		GraphParser parser;
		String inputFileName, outputFileName;
		
		System.out.println("Podaj nazw� pliku wej�ciowego\n");
		input = new Scanner(System.in);
		inputFileName = input.next();
		
		parser = new GraphParser();
		inputGraph = parser.getGraphFromFile(inputFileName);
		
		if (inputGraph == null) {
			input.close();
			return;
		}
		List<Integer> terminals = parser.getTerminals(inputFileName);
				
		controler = new Controler();
		
		
		long start=System.currentTimeMillis();		   
		   
		steinerTree = controler.getSteinerTree(inputGraph, terminals);
		   
		long stop=System.currentTimeMillis();
		
		System.out.println("Czas wykonania (w milisekundach): "+(stop-start));
		
		
		if (steinerTree == null) {
			input.close();
			return;
		}
		
		System.out.println("Podaj nazw� pliku wyj�ciowego\n");
		outputFileName = input.next();		
		parser.saveTreeToFile(steinerTree, outputFileName, terminals);
		
		input.close();
	}

}
