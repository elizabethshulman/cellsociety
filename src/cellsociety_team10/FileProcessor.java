/**
 * the FileProcessor is responsible for reading/writing files and saving general information about the file during the simulation.
 * @author Andrew Yeung
 */
package cellsociety_team10;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import cellVariants.Cell;
import fileInfoExtractorVariants.FileInfoExtractor;
import neighborCalculatorVariants.NeighborCalculator;

public class FileProcessor {
	
	private String myType;
	private String author;
	private String title;
	private Map<String,Double> globalVars;
	private XMLStreamReader myParser;
	private XMLStreamWriter myWriter;
	private FileInfoExtractor helper;
	private Map<Cell,List<Cell>> cellGrid;
	private int gridRowCount;
	private int gridColCount;
	private boolean isToroidal;
	private boolean isDiagonal;
	private String cellShape;
	private NeighborCalculator nCalc;
	/**
	 * Creates a new FileProcessor from the specified file
	 * @param file the input file
	 * @throws FileNotFoundException if file is invalid
	 * @throws XMLStreamException if there is an error with parsing the file
	 */
	public FileProcessor(File file) throws FileNotFoundException, XMLStreamException{
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		myParser = xmlif.createXMLStreamReader(new FileInputStream(file));
		readFile();
	}
	/**
	 * Returns the shape of Cells. Options are Hexagon, Square, and Triangle.
	 * @return the shape of Cells currently in use
	 */
	public String getCellShape() {
		return cellShape;
	}
	/**
	 * Sets the shape of the cells and resets the CellMap to reflect changes
	 * @param shape The shape of Cells. Options other than Hexagon, Square, and Triangle will throw an IllegalArgumentException
	 * passed from the method createNCalc().
	 * @see createNCalc()
	 * @see refreshMap()
	 */
	protected void setCellShape(String shape) {
		cellShape = shape;
		createNCalc();
		refreshMap();
	}
	/**
	 * Returns the type of simulation as a String
	 * @return the String corresponding to the simulation name used by the project to determine what simulation is run.
	 */
	public String getType() {
		return myType;
	}
	/**
	 * Sets the type of simulation corresponding to the given input and updates the FileInfoExtractor accordingly
	 * @param type String corresponding to correct simulation
	 * @throws IllegalArgumentException if String does not correspond to a valid simulation type
	 * @see FileInfoExtractor
	 */
	protected void setType(String type) {
		myType = type;
		try {
				String className = "fileInfoExtractorVariants." + myType + "FIE";
			helper = (FileInfoExtractor) Class.forName(className).getConstructor().newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Simulation type argument is invalid");
		}
	}
	/**
	 * Returns the simulation's author
	 * @return author as a String
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * Registers the input as the simulation's author
	 * @param s the user's preferred author name
	 */
	protected void setAuthor(String s) {
		author = s;
	}
	/**
	 * 
	 * @return the saved title of the simulation
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Returns a Map that lists all global variables present in the simulation
	 * @return a Map of relevant global variable names to their corresponding values
	 */
	public Map<String,Double> getGlobalVars() {
		return new HashMap<>(globalVars);
	}
	/**
	 * Returns a Map that lists all cells and their neighbors
	 * @return the Map of Cells to neighboring cells
	 */
	public Map<Cell,List<Cell>> getCellGrid() {
		return cellGrid;
	}
	/**
	 * Returns the number of rows present in the simulation
	 * @return rows in simulation
	 */
	public int getRowCount(){
		return gridRowCount;
	}
	/**
	 * Returns the number of columns present in the simulation
	 * @return columns in simulation
	 */
	public int getColCount() {
		return gridColCount;
	}
	/**
	 * Sets new dimensions for the simulation
	 * @param row New row dimension
	 * @param col New col dimension
	 */
	public void setRowsAndCols(int row, int col){
		gridRowCount = row;
		nCalc.setRowLength(row);
		gridColCount = col;
		nCalc.setColLength(col);
	}
	/**
	 * Sets toroidal borders in the simulation and updates the map to reflect changes
	 * @param b true if board is a torus; false otherwise
	 */
	protected void setBorders(boolean b) {
		isToroidal = b;
		refreshMap();
		}
	/**
	 * Sets neighbors to either consider orthogonal or adjacent neighbors. Has no functional effect on Hexagonal grids.
	 * @param b true if neighbors includes diagonal neighbors; false otherwise
	 */
	protected void setNeighbors(boolean b) {
		isDiagonal = b;
		refreshMap();
	}
	/**
	 * reads information from the File associated with this object
	 * @throws XMLStreamException if File is incorrectly formatted
	 */
	public void readFile() throws XMLStreamException {
		readHeader();
		readGlobalVars();
		readCells();
	}
	/**
	 * Reads the header for the document
	 * @throws XMLStreamException if document header is incorrectly formatted
	 */
	private void readHeader() throws XMLStreamException {
		int xmlEvent;
		do {
			 xmlEvent = myParser.next();
			 if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(myParser.getLocalName()) {
				  	case "simtype": myParser.next();
				  		setType(myParser.getText());
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
	/**
	 * Reads any relevant global variables 
	 * @throws XMLStreamException if global variables section is incorrectly formatted
	 */
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
		helper.addDefaultGlobals(globalVars);
	}
	/**
	 * Reads the cell grid and grid-related specifications from the file
	 * @throws XMLStreamException if cell grid is incorrectly formatted
	 */
	private void readCells() throws XMLStreamException {
		ArrayList<ArrayList<Cell>> newGrid = new ArrayList<>();
		ArrayList<Cell> newRow = new ArrayList<>();
		while(true) {
			 int xmlEvent = myParser.next();
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
	/**
	 * Converts a 2D array of Cells into a Map of Cells to their neighbors
	 * @param cellArray the 2D grid of cells
	 */
	public void createCellMap(Cell[][] cellArray)
	{
		gridRowCount = cellArray.length;
		gridColCount = cellArray[0].length;
		cellGrid = new HashMap<>();
		ArrayList<Cell> neighbors;
		createNCalc();
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
	/**
	 * Saves the corret
	 * @param cells
	 * @param file
	 * @throws FileNotFoundException if file is invalid
	 * @throws XMLStreamException if FileProcessor parameters are missing
	 * @throws TransformerException if attempts to format the XML file fail
	 */
	public void saveGridState(Set<Cell> cells, File file) throws FileNotFoundException, XMLStreamException {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		myWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(temp);
		myWriter.writeStartDocument();
		myWriter.writeStartElement("simulation");
		writeHeader();
		writeGlobalVars();
		writeGrid(createStateGrid(cells));
		myWriter.writeEndElement();
		myWriter.writeEndDocument();
		myWriter.close();
		try {
			OutputStream out = new FileOutputStream(file);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    transformer.transform(new StreamSource(new ByteArrayInputStream(temp.toByteArray())), new StreamResult(out));
		} catch (TransformerException e) {
			throw new FileNotFoundException("Unable to correctly format file.");
		}
	}
	/**
	 * Writes the document header to the output
	 * @throws XMLStreamException if header parameters are null
	 */
	private void writeHeader() throws XMLStreamException {
		myWriter.writeStartElement("header");
		myWriter.writeStartElement("simtype");
		myWriter.writeCharacters(myType);
		myWriter.writeEndElement();
		myWriter.writeStartElement("title");
		myWriter.writeCharacters(title);
		myWriter.writeEndElement();
		myWriter.writeStartElement("author");
		myWriter.writeCharacters(author);
		myWriter.writeEndElement();
		myWriter.writeEndElement();
	}
	/**
	 * Writes any and all relevant global variables to the output
	 * @throws XMLStreamException if global variable values are null
	 */
	private void writeGlobalVars() throws XMLStreamException {
		myWriter.writeStartElement("global_vars");
		for(String s: globalVars.keySet()) {
			myWriter.writeStartElement(s);
			myWriter.writeCharacters(Double.toString(globalVars.get(s)));
			myWriter.writeEndElement();
		}
		myWriter.writeEndElement();
	}
	/**
	 * Writes a 2D array of Cells to the output
	 * @param stateGrid 2D array corresponding to the current grid state
	 * @throws XMLStreamException if 2D array contains null elements
	 */
	private void writeGrid(Cell[][] stateGrid) throws XMLStreamException {
		myWriter.writeStartElement("grid");
		writeGridHeader();
		for(int a = 0; a < stateGrid.length; a++) {
			myWriter.writeStartElement("row");
			for(int b = 0; b < stateGrid[a].length; b++) {
				myWriter.writeEmptyElement("cell");
				helper.writeCell(myWriter,stateGrid[a][b]);
			}
			myWriter.writeEndElement();
		}
		myWriter.writeEndElement();
	}
	/**
	 * Writes the grid specifications associated with the simulation to the output
	 * @throws XMLStreamException if grid specs are null
	 */
	private void writeGridHeader() throws XMLStreamException {

		myWriter.writeStartElement("cellShape");
		myWriter.writeCharacters(cellShape);
		myWriter.writeEndElement();
		
		myWriter.writeStartElement("neighbors");
		if(isDiagonal)
			myWriter.writeCharacters("adjacent");
		else
			myWriter.writeCharacters("orthogonal");
		myWriter.writeEndElement();
		
		myWriter.writeStartElement("borders");
		if(isToroidal)
			myWriter.writeCharacters("torus");
		else
			myWriter.writeCharacters("finite");
		myWriter.writeEndElement();
	}
	/**
	 * Creates a 2D array from a Set of Cells
	 * @param cells a set of all the cells in the grid
	 * @return 2D array corresponding to state of simulation
	 */
	public Cell[][] createStateGrid(Set<Cell> cells) {
		Cell[][] arrangement = new Cell[gridRowCount][gridColCount];
		for(Cell c: cells) {
			arrangement[c.getRow()][c.getCol()] = c;
		}
		return arrangement;
	}
	/**
	 * Creates the neighborCalculator relevant to the shape of cells in the simulation
	 * @throws IllegalArgumentException if cell shape is invalid
	 */
	public void createNCalc() {
		try {
			String className = "neighborCalculatorVariants." + cellShape + "NeighborCalculator";
			nCalc = (NeighborCalculator) Class.forName(className).getConstructor(int.class,int.class,boolean.class,boolean.class).newInstance(gridRowCount,gridColCount,isDiagonal, isToroidal);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cell shape parameter is invalid.");
		}
	}
	/**
	 * refreshes the map to reflect any grid specification changes
	 */
	public void refreshMap() {
		Cell[][] current = createStateGrid(cellGrid.keySet());
		createCellMap(current);
	}
}