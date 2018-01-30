package cellsociety_team10;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class GameOfLifeFileProcessor extends FileProcessor {

	public GameOfLifeFileProcessor(String fpath) throws FileNotFoundException, XMLStreamException {
		super(fpath);
	}

	@Override
	public void readFile() throws XMLStreamException {
		XMLStreamReader parser = getStreamReader();
		ArrayList<ArrayList<Cell>> newGrid = new ArrayList<ArrayList<Cell>>();
		ArrayList<Cell> newRow = new ArrayList<Cell>();
		while(parser.hasNext())
		{
			 int xmlEvent = parser.next();
			 
			  //Process start element.
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
				  switch(parser.getLocalName())
				  {
				  	case "author": setAuthor(parser.getText()); break;
				  	case "title": setTitle(parser.getText()); break;
				  	case "row":	newRow = new ArrayList<Cell>(); break;
				  	case "cell": newRow.add(new GameOfLifeCell(Integer.parseInt(parser.getAttributeValue(0)))); break;
				  	
				  }
				System.out.println("Start Element: " + parser.getLocalName());
				int attributes = parser.getAttributeCount();
				for(int i=0; i<attributes; i++){
				String name = parser.getAttributeName(i).toString();
				String value = parser.getAttributeValue(i);
				 System.out.println("Attribute name: " + name);
				 System.out.println("Attribute value: " + value);
				}
			  }
			  if(xmlEvent == XMLStreamConstants.END_ELEMENT)
			  {
				  switch(parser.getLocalName())
				  {
				  	case "row": newGrid.add(newRow); break;
				  	case "grid": 
				  		setGrid((Cell[][])(Stream.of(newGrid).map(i -> i.toArray(new Cell[0])).toArray()));
				  }
			  }
		}
		

	}
	public static void main(String[] args)
	{
		try {
			FileProcessor fp = new GameOfLifeFileProcessor("/Users/andrew/Documents/workspace/cellsociety_team10/data/gameoflife1.xml");
			fp.readFile();
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
