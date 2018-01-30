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

	@Override
	public void readCells(XMLStreamReader parser) {
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
				  		newRow.add(new GameOfLifeCell(state)); break;
				  	
				  }
			  }
			  if(xmlEvent == XMLStreamConstants.END_ELEMENT)
			  {
				  switch(parser.getLocalName())
				  {
				  	case "row": newGrid.add(newRow); break;
				  	case "grid": 
				  		setGrid((Cell[][])(Stream.of(newGrid).map(i -> i.toArray(new Cell[0])).toArray())); return;
				  }
			  }
		}
		
	}
	

}
