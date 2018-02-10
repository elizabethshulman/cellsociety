package cellsociety_team10;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import fileInfoExtractorVariants.FileInfoExtractor;

public class FileProcessor {
	
	private String myType;
	private String filepath;
	private String author;
	private String title;
	private Map<String,Double> globalVars;
	private XMLStreamReader myParser;
	private FileInfoExtractor helper;
	private Map<Cell,List<Cell>> cellGrid;
	private int gridRowCount;
	private int gridColCount;
	private boolean isToroidal;
	private boolean isDiagonal;
	private String cellShape;
	private NeighborCalculator nCalc;
	
	public FileProcessor(File file) throws FileNotFoundException, XMLStreamException{
		filepath = file.getAbsolutePath();
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		myParser = xmlif.createXMLStreamReader(new FileInputStream(file));
		
		readFile();
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
	public Map<String,Double> getGlobalVars() {
		return globalVars;
	}
	public Map<Cell,List<Cell>> getCellGrid() {
		return cellGrid;
	}
	public int getRowCount(){
		return gridRowCount;
	}
	public int getColCount() {
		return gridColCount;
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
				  					author = myParser.getText();
				  					break;
				  	case "title": myParser.next();
				  					title = myParser.getText();
				  					break;
				  }
			  }
		}
		while(xmlEvent != XMLStreamConstants.END_ELEMENT || !myParser.getLocalName().equals("header"));
		
	}
	private void readGlobalVars() throws XMLStreamException {
		globalVars = new HashMap<>();
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
		ArrayList<ArrayList<Cell>> newGrid = new ArrayList<>();
		ArrayList<Cell> newRow = new ArrayList<>();
		while(true) {
			 int xmlEvent = myParser.next();
			  //Process start element.
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(myParser.getLocalName()) {
				  	case "row":	newRow = new ArrayList<Cell>();
				  				break;
				  	case "cell": newRow.add(helper.getCell(myParser));
				  				break;
				  	case "cellShape": myParser.next();
			  		cellShape = myParser.getText();
				  				break;
				  	case "borders": myParser.next();
				  		isToroidal = myParser.getText().equals("torus");
				  		break;
				  	case "neighbors": myParser.next();
				  		isDiagonal = myParser.getText().equals("adjacent");
				  	
				  }
			  }
			  if(xmlEvent == XMLStreamConstants.END_ELEMENT) {
				  switch(myParser.getLocalName()) {
				  	case "row": newGrid.add(newRow);
				  				break;
				  	case "grid":
				  		Cell[][] cellArray = newGrid.stream().map(i -> i.toArray(new Cell[0])).toArray(Cell[][]::new);
				  		createCellMap(cellArray);
				  		return;
				  }
			  }
		}
		
	}
	
	//convert Cell grid to hashmap
	private void createCellMap(Cell[][] cellArray)
	{
		gridRowCount = cellArray.length;
		gridColCount = cellArray[0].length;
		cellGrid = new HashMap<>();
		ArrayList<Cell> neighbors;
		try {
				String className = "cellsociety_team10." + cellShape + "NeighborCalculator";
			nCalc = (NeighborCalculator) Class.forName(className).getConstructor(int.class,int.class,boolean.class,boolean.class).newInstance(gridRowCount,gridColCount,isDiagonal, isToroidal);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cell shape is invalid");
		}
		for(int x = 0; x < cellArray.length; x++) {
			for(int y = 0; y < cellArray[x].length; y++) {
				neighbors = new ArrayList<>();
				Cell toAdd = cellArray[x][y];
				toAdd.setRow(x);
				toAdd.setCol(y);
				List<int[]> neighborIndices = nCalc.calcNeighborLocations(x,y);
				for(int a = 0; a < neighborIndices.size(); a++)
				{
					neighbors.add(cellArray[neighborIndices.get(a)[0]][neighborIndices.get(a)[1]]);
				}
				cellGrid.put(toAdd, neighbors);
			}
		}
	}
	public void saveGridState(Map<Cell,List<Cell>> currentState, File file) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		XMLStreamWriter myWriter = xmlof.createXMLStreamWriter(new FileOutputStream(file));
		myWriter.writeStartDocument();
		
		
		myWriter.writeEndElement();
		myWriter.flush();
		
		
	}
	public int[][] createStateGrid(Map<Cell,List<Cell>> cells) {
		int[][] arrangement = new int[gridRowCount][gridColCount];
		for(Cell c: cells.keySet())
			arrangement[c.getRow()][c.getCol()] = c.getState();
		return arrangement;
	}
}
