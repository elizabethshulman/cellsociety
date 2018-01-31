package cellsociety_team10;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
public class FileProcessor {
	public static final String[] simTypes = new String[]{"Game Of Life","Fire","Segregation","Predator Prey"};
	protected String myType;
	private String filepath;
	private String author;
	private String title;
	private Cell[][] grid;
	private XMLInputFactory xmlif;
	private XMLStreamReader myParser;
	public FileProcessor(String fpath) throws FileNotFoundException, XMLStreamException{
		filepath = fpath;
		xmlif = XMLInputFactory.newInstance();
		myParser = xmlif.createXMLStreamReader(new FileInputStream(fpath));
	}
	public String getFilepath() {
		return filepath;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String s) {
		author = s;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String s) {
		title = s;
	}
	public Cell[][] getGrid()
	{
		return grid;
	}
	public void setGrid(Cell[][] newGrid)
	{
		grid = newGrid;
	}
	public XMLStreamReader getStreamReader()
	{
		return myParser;
	}
	public void readFile() throws XMLStreamException
	{
		readHeader(myParser);
		readCells(myParser);
	}
	protected void readHeader(XMLStreamReader parser) throws XMLStreamException {
		int xmlEvent;
		do
		{
			 xmlEvent = parser.next();
			 
			  //Process start element.
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(parser.getLocalName())
				  {
				  	case "simtype": parser.next();
				  		String simName = parser.getText();
				  		if(Stream.of(simTypes).anyMatch(i -> i.equals(simName)))
				  			myType = simName;
				  		else throw new XMLStreamException("Invalid simulation type");
				  		break;
				  	case "author": parser.next(); 
				  		setAuthor(parser.getText()); break;
				  	case "title": parser.next();
				  		setTitle(parser.getText()); break;
				  }
			  }
		}
		while(xmlEvent != XMLStreamConstants.END_ELEMENT || !parser.getLocalName().equals("header"));
		
	}
	public void readCells(XMLStreamReader parser) throws XMLStreamException {
		ArrayList<ArrayList<Cell>> newGrid = new ArrayList<ArrayList<Cell>>();
		ArrayList<Cell> newRow = new ArrayList<Cell>();
		while(true)
		{
			 int xmlEvent = parser.next();
			 
			  //Process start element.
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(parser.getLocalName())
				  {
				  	case "row":	newRow = new ArrayList<Cell>(); break;
				  	case "cell": int state = Integer.parseInt(parser.getAttributeValue(0)); 
				  		newRow.add(new Cell(state)); break;
				  	
				  }
			  }
			  if(xmlEvent == XMLStreamConstants.END_ELEMENT)
			  {
				  switch(parser.getLocalName())
				  {
				  	case "row": newGrid.add(newRow); break;
				  	case "grid":
				  		setGrid(newGrid.stream().map(i -> i.toArray(new Cell[0])).toArray(Cell[][]::new));
				  		return;
				  }
			  }
		}
		
	}
	public static void main(String[] args)
	{
		try {
			FileProcessor fp = new FileProcessor("/Users/andrew/Documents/workspace/cellsociety_team10/data/gameoflife2.xml");
			fp.readFile();
			Cell[][] g = fp.getGrid();
			for(int a = 0; a < g.length; a++)
			{
				for(int b = 0; b < g[a].length; b++)
					System.out.print(g[a][b].getState());
				System.out.println();
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
