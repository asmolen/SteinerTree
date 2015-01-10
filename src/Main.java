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
		List<Integer> terminals = parser.getTerminals(inputFileName);
				
		controler = new Controler();
		steinerTree = controler.getSteinerTree(inputGraph, terminals);
		
		System.out.println("Podaj nazwê pliku wyjœciowego\n");
		outputFileName = input.next();
		
		input.close();
		
		parser.saveTreeToFile(steinerTree, outputFileName, terminals);
	}

}
