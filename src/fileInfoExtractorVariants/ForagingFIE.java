/**
 * implements file reading and writing for foraging ant simulation
 * @author Andrew
 */
package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.AntCell;
import cellVariants.Cell;
import cellVariants.ForagingCell;

public class ForagingFIE implements FileInfoExtractor{
	/**
	 * No global variables exist for this simulation; if this is called, throws an exception
	 */
	@Override
	public Double getGlobalVar(XMLStreamReader xmlRead) throws XMLStreamException {
		throw new XMLStreamException("Invalid global variables in file");
	}
	/**
	 * No global variables exist for this simulation; does nothing if called
	 */
	@Override
	public void addDefaultGlobals(Map<String,Double> globals) {}
	
	//create ForagingCell and add ants if necessary
	/**
	 * Reads in a cell: 0 = empty, 1 = nest, 2 = food, 3 = obstacle
	 * Reads nonzero # of ants if attribute exists; assumed zero otherwise
	 */
	@SuppressWarnings("unused")
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 3) {
			throw new XMLStreamException("Invalid Foraging Ant cell type.");
		}
		ForagingCell c;
		if(xmlRead.getAttributeCount() > 1) {
			c = new ForagingCell(val + 4);
			int numAnts = Integer.parseInt(xmlRead.getAttributeValue(1));
			for(int x = 0; x < numAnts - 1; x++) {
				AntCell a = new AntCell(c);
			}
		}
		else{c = new ForagingCell(val);}
		return c;
	}
	/**
	 * Writes cell state to file and writes nonzero number of ants as attribute if necessary
	 */
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
		int numAnts = ((ForagingCell) cell).getAntsHere().size();
		if(numAnts > 0)
			myWriter.writeAttribute("numAnts", Integer.toString(((ForagingCell) cell).getAntsHere().size()));	
	}

}
