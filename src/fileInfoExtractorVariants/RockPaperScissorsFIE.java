/**
 * implements file reading and writing for RockPaperScissors simulation
 * @author Andrew
 */

package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.RockPaperScissorsCell;

public class RockPaperScissorsFIE implements FileInfoExtractor{
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
	public void addDefaultGlobals(Map<String, Double> globals) {
	}
	/**
	 * Reads state from file: 0 = empty, 1-3 = different colors
	 * Reads in a gradient for nonempty cells
	 */
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 3) {
			throw new XMLStreamException("Invalid Rock Paper Scissors cell type.");
		}
		if(val == 0)
			return new RockPaperScissorsCell(val, 0);
		int gradient = Integer.parseInt(xmlRead.getAttributeValue(1));
		return new RockPaperScissorsCell(val, gradient);
	}
	/**
	 * Writes state to file
	 * If cell is not empty, writes the gradient value as well
	 */
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
		if(cell.getState() != 0)
			myWriter.writeAttribute("gradient", Integer.toString(((RockPaperScissorsCell) cell).getHealth()));	
	}

}
