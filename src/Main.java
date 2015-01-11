import java.util.Scanner;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input;
		Graph inputGraph;
		Tree steinerTree;
		Controler controler;
		GraphParser parser;
		String inputFileName, outputFileName;
		
		System.out.println("Podaj nazwê pliku wejœciowego\n");
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
		steinerTree = controler.getSteinerTree(inputGraph, terminals);
		
		if (steinerTree == null) {
			input.close();
			return;
		}
		
		System.out.println("Podaj nazwê pliku wyjœciowego\n");
		outputFileName = input.next();		
		parser.saveTreeToFile(steinerTree, outputFileName, terminals);
		
		input.close();
	}

}
