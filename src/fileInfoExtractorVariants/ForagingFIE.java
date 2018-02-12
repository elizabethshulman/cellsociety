//implements file reading and writing for foraging ant simulation
//author: Andrew
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.AntCell;
import cellVariants.Cell;
import cellVariants.ForagingCell;

public class ForagingFIE implements FileInfoExtractor{
	//no global variables: throw exception if called
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}
	//no global variables: do nothing
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {}
	
	//create ForagingCell and add ants if necessary
	@SuppressWarnings("unused")
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 3) {
			throw new XMLStreamException("Invalid Foraging Ant cell type.");
		}
		ForagingCell c = new ForagingCell(val);
		if(xmlRead.getAttributeCount() > 1) {
			int numAnts = Integer.parseInt(xmlRead.getAttributeValue(1));
			for(int x = 0; x < numAnts; x++) {
				AntCell a = new AntCell(c);
			}
		}
		return c;
	}
	//write cell state to file and write number of ants if nonzero
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
		int numAnts = ((ForagingCell) cell).getAntsHere().size();
		if(numAnts > 0)
			myWriter.writeAttribute("numAnts", Integer.toString(((ForagingCell) cell).getAntsHere().size()));	
	}

}
