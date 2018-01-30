package cellsociety_team10;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
public abstract class FileProcessor {
	protected String myClass;
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
		checkValidity(myParser);
		readHeader(myParser);
		readCells(myParser);
	}
	protected void checkValidity(XMLStreamReader parser) throws XMLStreamException{
		int xmlEvent;
		do
		{
			xmlEvent = parser.next();
		}
		while(xmlEvent != XMLStreamConstants.START_ELEMENT || !parser.getLocalName().equals("simtype"));
		parser.next();
		if(!parser.getText().equals(myClass))
			throw new XMLStreamException("Invalid file type");
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

}
