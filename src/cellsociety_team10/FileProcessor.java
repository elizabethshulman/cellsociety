package cellsociety_team10;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import cellVariants.*;
public class FileProcessor {
	
	private String myType;
	private String filepath;
	private String author;
	private String title;
	private HashMap<String,Double> globalVars;
	private XMLStreamReader myParser;
	private FileInfoExtractor helper;
	private HashMap<Cell,ArrayList<Cell>> cellGrid;
	
	public FileProcessor(String fpath) throws FileNotFoundException, XMLStreamException{
		filepath = fpath;
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		myParser = xmlif.createXMLStreamReader(new FileInputStream(fpath));
	}
	public String getType() {
		return myType;
	}
	public String getFilepath() {
		return filepath;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public HashMap<String,Double> getGlobalVars() {
		return globalVars;
	}
	public HashMap<Cell,ArrayList<Cell>> getCellGrid() {
		return cellGrid;
	}
	// Reads in the file and sets instance variables based on file information
	public void readFile() throws XMLStreamException
	{
		readHeader();
		readGlobalVars();
		readCells();
	}
	// Reads the header section of the file
	private void readHeader() throws XMLStreamException {
		int xmlEvent;
		do
		{
			 xmlEvent = myParser.next();
			 if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(myParser.getLocalName()) {
				  	case "simtype": myParser.next();
				  		String simName = myParser.getText();
			  			myType = simName;
			  			try {
			  				String className = "fileInfoExtractorVariants." + simName + "FIE";
							helper = (FileInfoExtractor) Class.forName(className).getConstructor().newInstance();
						} catch (Exception e) {
							throw new IllegalArgumentException("Simulation type argument is invalid");
						}
				  		break;
				  	case "author": myParser.next(); 
				  		author = myParser.getText(); break;
				  	case "title": myParser.next();
				  		title = myParser.getText(); break;
				  }
			  }
		}
		while(xmlEvent != XMLStreamConstants.END_ELEMENT || !myParser.getLocalName().equals("header"));
		
	}
	private void readGlobalVars() throws XMLStreamException {
		globalVars = new HashMap<String,Double>();
		int xml;
		myParser.next();
		do {
			xml = myParser.next();
			if(xml == XMLStreamConstants.START_ELEMENT && !myParser.getLocalName().equals("global_vars")) {
				globalVars.put(myParser.getLocalName(), helper.getGlobalVar(myParser));
			}
		}
		while(xml != XMLStreamConstants.END_ELEMENT || !myParser.getLocalName().equals("global_vars"));
	}
	
	//Creates 2D array based on information from file
	private void readCells() throws XMLStreamException {
		ArrayList<ArrayList<Cell>> newGrid = new ArrayList<ArrayList<Cell>>();
		ArrayList<Cell> newRow = new ArrayList<Cell>();
		while(true)
		{
			 int xmlEvent = myParser.next();
			  //Process start element.
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(myParser.getLocalName())
				  {
				  	case "row":	newRow = new ArrayList<Cell>(); break;
				  	case "cell": newRow.add(helper.getCell(myParser)); break;
				  	
				  }
			  }
			  if(xmlEvent == XMLStreamConstants.END_ELEMENT)
			  {
				  switch(myParser.getLocalName())
				  {
				  	case "row": newGrid.add(newRow); break;
				  	case "grid":
				  		Cell[][] cellArray = newGrid.stream().map(i -> i.toArray(new Cell[0])).toArray(Cell[][]::new);
				  		createCellMap(cellArray);
				  		return;
				  }
			  }
		}
		
	}
	//
	//convert Cell grid to hashmap
	private void createCellMap(Cell[][] cellArray)
	{
		cellGrid = new HashMap<Cell, ArrayList<Cell>>();
		ArrayList<Cell> neighbors;
		for(int x = 0; x < cellArray.length; x++)
			for(int y = 0; y < cellArray[x].length; y++)
			{
				neighbors = new ArrayList<Cell>();
				Cell toAdd = cellArray[x][y];
				toAdd.setRow(x);
				toAdd.setCol(y);
				ArrayList<int[]> neighborIndices = helper.calcNeighborLocations(x,y,cellArray.length,cellArray[x].length);
				for(int a = 0; a < neighborIndices.size(); a++)
				{
					neighbors.add(cellArray[neighborIndices.get(a)[0]][neighborIndices.get(a)[1]]);
				}
				cellGrid.put(toAdd, neighbors);
			}
	}
	// temporary tester method
	public static void main(String[] args)
	{
		try {
			FileProcessor fp = new FileProcessor("/Users/andrew/Documents/workspace/cellsociety_team10/data/simulations/segregation/segregation1.xml");
			fp.readFile();
			System.out.println(fp.getType());
			HashMap<String,Double> globalVar = fp.getGlobalVars();
			for(String s: globalVar.keySet())
				System.out.println(s + ": " + globalVar.get(s));
			for(Cell c : fp.getCellGrid().keySet())
			{
				System.out.print(c.getRow() + " " + c.getCol() + ": ");
				ArrayList<Cell> neighboring = fp.getCellGrid().get(c);
				for(int a = 0; a < neighboring.size(); a++)
					System.out.print(neighboring.get(a).getRow() + " " + neighboring.get(a).getCol() + ", ");
				System.out.println();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filepath");
		}
	}


}
