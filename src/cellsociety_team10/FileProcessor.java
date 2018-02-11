package cellsociety_team10;
import java.io.BufferedOutputStream;
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
	
	public FileProcessor(File file) throws FileNotFoundException, XMLStreamException{
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		myParser = xmlif.createXMLStreamReader(new FileInputStream(file));
		readFile();
	}
	public String getCellShape() {
		return cellShape;
	}
	protected void setCellShape(String shape) {
		cellShape = shape;
	}
	public String getType() {
		return myType;
	}
	protected void setType(String type) {
		myType = type;
		try {
				String className = "fileInfoExtractorVariants." + myType + "FIE";
			helper = (FileInfoExtractor) Class.forName(className).getConstructor().newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Simulation type argument is invalid");
		}
	}
	public String getAuthor() {
		return author;
	}
	protected void setAuthor(String s) {
		author = s;
	}
	public String getTitle() {
		return title;
	}
	public Map<String,Double> getGlobalVars() {
		return new HashMap<>(globalVars);
	}
	protected void setGlobalVars(Map<String,Double> map) {
		globalVars = map;
	}
	public Map<Cell,List<Cell>> getCellGrid() {
		return cellGrid;
	}
	public int getRowCount(){
		return gridRowCount;
	}
	protected void setRowCount(int row){
		gridRowCount = row;
	}
	public int getColCount() {
		return gridColCount;
	}
	protected void setColCount(int col){
		gridColCount = col;
	}
	protected void setBorders(boolean b) {
		isToroidal = b;
		nCalc.setBorders(b);
		}
	protected void setNeighbors(boolean b) {
		isDiagonal = b;
		nCalc.setNeighbors(b);
	}
	// Reads in the file and sets instance variables based on file information
	public void readFile() throws XMLStreamException {
		readHeader();
		readGlobalVars();
		readCells();
	}
	// Reads the header section of the file
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
	//Creates 2D array based on information from file
	private void readCells() throws XMLStreamException {
		ArrayList<ArrayList<Cell>> newGrid = new ArrayList<>();
		ArrayList<Cell> newRow = new ArrayList<>();
		while(true) {
			 int xmlEvent = myParser.next();
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(myParser.getLocalName()) {
				  	case "row":	newRow = new ArrayList<Cell>();
				  				break;
				  	case "cell": newRow.add(helper.getCell(myParser,cellShape));
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
				String className = "neighborCalculatorVariants." + cellShape + "NeighborCalculator";
			nCalc = (NeighborCalculator) Class.forName(className).getConstructor(int.class,int.class,boolean.class,boolean.class).newInstance(gridRowCount,gridColCount,isDiagonal, isToroidal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Cell shape parameter is invalid.");
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
	private void writeGlobalVars() throws XMLStreamException {
		myWriter.writeStartElement("global_vars");
		for(String s: globalVars.keySet()) {
			myWriter.writeStartElement(s);
			myWriter.writeCharacters(Double.toString(globalVars.get(s)));
			myWriter.writeEndElement();
		}
		myWriter.writeEndElement();
	}
	private void writeGrid(Cell[][] stateGrid) throws XMLStreamException {
		myWriter.writeStartElement("grid");
		writeGridHeader();
		for(int a = 0; a < gridRowCount; a++) {
			myWriter.writeStartElement("row");
			for(int b = 0; b < gridColCount; b++) {
				myWriter.writeEmptyElement("cell");
				helper.writeCell(myWriter,stateGrid[a][b]);
			}
			myWriter.writeEndElement();
		}
		myWriter.writeEndElement();
	}
	
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
	
	public Cell[][] createStateGrid(Set<Cell> cells) {
		Cell[][] arrangement = new Cell[gridRowCount][gridColCount];
		for(Cell c: cells) {
			arrangement[c.getRow()][c.getCol()] = c;
		}
		return arrangement;
	}
	
	public NeighborCalculator getNeighborCalc() {
		return nCalc;
	}
}