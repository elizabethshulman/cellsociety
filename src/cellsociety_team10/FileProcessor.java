package cellsociety_team10;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
public abstract class FileProcessor {
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
	public abstract void readCells(XMLStreamReader parser);
	public void readHeader(XMLStreamReader parser) throws XMLStreamException {
		int xmlEvent;
		do
		{
			 xmlEvent = parser.next();
			 
			  //Process start element.
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(parser.getLocalName())
				  {
				  	case "author": setAuthor(parser.getText()); break;
				  	case "title": setTitle(parser.getText()); break;
				  }
			  }
		}
		while(!parser.getLocalName().equals("header") || !(xmlEvent == XMLStreamConstants.END_DOCUMENT) );
		
	}

}
