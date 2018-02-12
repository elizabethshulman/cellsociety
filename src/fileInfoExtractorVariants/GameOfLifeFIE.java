/**
 * implements file reading and writing for Game of Life
 * @author Andrew
 */

package fileInfoExtractorVariants;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cellVariants.Cell;
import cellVariants.GameOfLifeCell;

public class GameOfLifeFIE implements FileInfoExtractor {
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
	
	/**
	 * Reads state from file: 0 = dead, 1 = alive
	 * Throws exception on invalid state
	 */
	@Override
	public Cell getCell(XMLStreamReader xmlRead) throws XMLStreamException {
		int val = Integer.parseInt(xmlRead.getAttributeValue(0));
		if(val < 0 || val > 1) {
			throw new XMLStreamException("Invalid Game Of Life cell type.");
		}
		return new GameOfLifeCell(val);
	}
	/**
	 * Writes cell state to file: alive = 1, dead = 0
	 */
	@Override
	public void writeCell(XMLStreamWriter myWriter, Cell cell) throws XMLStreamException {
		myWriter.writeAttribute("state", Integer.toString(cell.getState()));
	}



}
