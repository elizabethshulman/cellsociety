package cellsociety_team10;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
public class FileProcessor {
	
	private String myType;
	private String filepath;
	private String author;
	private String title;
	private Cell[][] grid;
	private HashMap<String,Double> globalVars;
	private XMLStreamReader myParser;
	private FileInfoExtractor helper;
	
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
	public Cell[][] getGrid() {
		return grid;
	}
	public HashMap<String,Double> getGlobalVars() {
		return globalVars;
	}
	// Reads in the file and sets instance variables based on file information
	public void readFile() throws XMLStreamException
	{
		readHeader();
		readGlobalVars();
		readCells();
	}
	// Reads the header section of the file
	protected void readHeader() throws XMLStreamException {
		int xmlEvent;
		do
		{
			 xmlEvent = myParser.next();
			 if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(myParser.getLocalName())
				  {
				  	case "simtype": myParser.next();
				  		String simName = myParser.getText();
			  			myType = simName;
			  			try {
			  				String className = "cellsociety_team10." + simName + "FIE";
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
	protected void readGlobalVars() throws XMLStreamException {
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
	protected void readCells() throws XMLStreamException {
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
				  		grid = newGrid.stream().map(i -> i.toArray(new Cell[0])).toArray(Cell[][]::new);
				  		return;
				  }
			  }
		}
		
	}
	//temporary tester method
	public static void main(String[] args)
	{
		try {
			FileProcessor fp = new FileProcessor("/Users/andrew/Documents/workspace/cellsociety_team10/data/fire1.xml");
			fp.readFile();
			Cell[][] g = fp.getGrid();
			for(int a = 0; a < g.length; a++)
			{
				for(int b = 0; b < g[a].length; b++)
					System.out.print(g[a][b].getState());
				System.out.println();
			}
			System.out.println(fp.getGlobalVars().get("probCatchFire"));
		} catch (Exception e) {
			throw new IllegalArgumentException("You're stupid");
		}
	}

	public int getSatisfactionThreshold() {
		return -1;
	}
}
